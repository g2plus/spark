package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.WemediaConstants;
import com.heima.common.exception.CustomException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.WmNewsAutoScanService;
import com.heima.wemedia.service.WmNewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    @Autowired
    private WmNewsAutoScanService wmNewsAutoScanService;

    /***
     * 查询文章
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findAll(WmNewsPageReqDto dto) {
        //参数校验
        if (dto == null) {
            //如果为null,返回无效参数
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //页面参数确定
        dto.checkParam();
        //从ThreadLocal中获取User信息进行查询
        WmUser user = WmThreadLocalUtil.getUser();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        //创建lambdaQueryWrapper对象,用于解决硬编码的问题
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();


        //根据状态进行查询
        if (dto.getStatus() != null) {
            lambdaQueryWrapper.eq(WmNews::getStatus, dto.getStatus());
        }

        //根据关键字进行模糊查询
        if (dto.getKeyword() != null) {
            lambdaQueryWrapper.like(WmNews::getTitle, dto.getKeyword());
        }

        //根据频道id进行查询
        if (dto.getChannelId() != null) {
            lambdaQueryWrapper.eq(WmNews::getChannelId, dto.getChannelId());
        }

        //根据发布时间的范围进行查询
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
            lambdaQueryWrapper.between(WmNews::getPublishTime, dto.getBeginPubDate(), dto.getEndPubDate());
        }

        //根据用户的id进行查询
        lambdaQueryWrapper.eq(WmNews::getUserId, user.getId());

        //对查询到的结果进行时间倒排处理显示
        lambdaQueryWrapper.orderByDesc(WmNews::getCreatedTime);

        //确定显示条数
        IPage page = new Page(dto.getPage(), dto.getSize());
        page = page(page, lambdaQueryWrapper);

        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());

        //数据结果放入data数组返回前端
        responseResult.setData(page.getRecords());

        //返回结果
        return responseResult;
    }

    @Override
    public ResponseResult submitNews(WmNewsDto dto) {
        //参数校验
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //使用Spring的BeanUtils工具进行属性copy
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(dto, wmNews);
        //属性拷贝属性名词和类型相同才能拷贝
        BeanUtils.copyProperties(dto, wmNews);
        //封面图片  list---> string
        if (dto.getImages() != null && dto.getImages().size() > 0) {
            //[1.jpg,2.jpg]-->   1.jpg,2.jpg
            String imageStr = StringUtils.join(dto.getImages(), ",");
            wmNews.setImages(imageStr);
        }
       //如果当前封面类型为自动 -1 根据文章内容是否有图片来进行值的设置
        if(dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)){
            wmNews.setType(null);
        }

        //保存或更新WmNews信息
        saveOrUpdateWmNews(wmNews);

        //获取保存状态,(草稿或是提交审核)
        /**
         * public 每个路径下可以访问,default 本包下可以使用(外部类)
         * private 私有, protected 子类可以使用 (仅用在成员上)
         */
        //为草稿时,直接返回响应体保存成功
        if(dto.getStatus().equals(WmNews.Status.NORMAL.getCode())){
            //直接返回,响应提交成功
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }


        //提交审核时,保存
        //3.不是草稿，保存文章内容图片与素材的关系
        //解析文章中的图片url链接
        List<String> materials =  ectractUrlInfo(dto.getContent());
        saveRelativeInfoForContent(materials,wmNews.getId());

        //4.不是草稿，保存文章封面图片与素材的关系，
        //如果未设置封面图片,提取内容的图片当作封面图片
        saveRelativeInfoForCover(dto,wmNews,materials);

        /**
         * 文章审核
         */
        wmNewsAutoScanService.autoScanWmNews(wmNews.getId());

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 第一个功能：如果当前封面类型为自动，则设置封面类型的数据
     * 匹配规则：
     * 1，如果内容图片大于等于1，小于3  单图  type 1
     * 2，如果内容图片大于等于3  多图  type 3
     * 3，如果内容没有图片，无图  type 0
     *
     * 第二个功能：保存封面图片与素材的关系
     * @param dto
     * @param wmNews
     * @param materials
     */
    private void saveRelativeInfoForCover(WmNewsDto dto, WmNews wmNews, List<String> materials) {

        List<String> images = dto.getImages();

        //如果当前封面类型为自动，则设置封面类型的数据
        //使用常用类来解决硬编码的问题
        if(dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)){
            //多图
            if(materials.size() >= 3){
                wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                images = materials.stream().limit(3).collect(Collectors.toList());
            }else if(materials.size() >= 1 && materials.size() < 3){
                //单图
                wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = materials.stream().limit(1).collect(Collectors.toList());
            }else {
                //无图
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE);
            }

            //修改文章
            if(images != null && images.size() > 0){
                //[1,2,3]->1,2,3
                wmNews.setImages(StringUtils.join(images,","));
            }
            //根据主键id进行记录的更新
            updateById(wmNews);
        }
        if(images != null && images.size() > 0){
            //调用方法保存封面图片素材之间的关系
            saveRelativeInfo(images,wmNews.getId(),WemediaConstants.WM_COVER_REFERENCE);
        }

    }


    /**
     * 处理文章内容里的图片与素材的关系
     * @param materials
     * @param newsId
     */
    private void saveRelativeInfoForContent(List<String> materials, Integer newsId) {
        saveRelativeInfo(materials,newsId,WemediaConstants.WM_CONTENT_REFERENCE);
    }

    @Autowired
    private WmMaterialMapper wmMaterialMapper;

    /**
     * 保存文章图片与素材的关系到数据库中
     * @param materials
     * @param newsId
     * @param type
     */
    private void saveRelativeInfo(List<String> materials, Integer newsId, Short type) {
        if(materials!=null && !materials.isEmpty()){
            //通过图片的url查询素材的id
            List<WmMaterial> dbMaterials = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery().in(WmMaterial::getUrl, materials));

            //判断素材是否有效
            if(dbMaterials==null || dbMaterials.size() == 0){
                //手动抛出异常   第一个功能：能够提示调用者素材失效了，第二个功能，进行数据的回滚
                throw new CustomException(AppHttpCodeEnum.MATERIASL_REFERENCE_FAIL);
            }
            //部分失效的素材情况进行排除
            if(materials.size() != dbMaterials.size()){
                //类的上方添加注解@Transactional,抛出异常时,事务进行回滚操作.
                throw new CustomException(AppHttpCodeEnum.MATERIASL_REFERENCE_FAIL);
            }

            List<Integer> idList = dbMaterials.stream().map(WmMaterial::getId).collect(Collectors.toList());

            //批量保存
            wmNewsMaterialMapper.saveRelations(idList,newsId,type);
        }

    }


    /**
     * 提取文章内容中的图片信息
     * @param content
     * @return
     */
    //进行内容解析对于使用[]包围的数据属于数组
    private List<String> ectractUrlInfo(String content) {
        List<String> materials = new ArrayList<>();
        //使用key-value的进行进行接收.
        List<Map> maps = JSON.parseArray(content, Map.class);
        for (Map map : maps) {
            if(map.get("type").equals("image")){
                String imgUrl = (String) map.get("value");
                materials.add(imgUrl);
            }
        }
        return materials;
    }

    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    private void saveOrUpdateWmNews(WmNews wmNews) {
        //补全属性
        wmNews.setUserId(WmThreadLocalUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        //默认上架
        wmNews.setEnable((short)1);
        if(wmNews.getId()!=null){
            //保存文章信息
            this.save(wmNews);
        }else{
            //更新文章信息
            //修改
            //删除文章图片与素材的关系(jdk代理实现)
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId,wmNews.getId()));
            updateById(wmNews);
        }
    }
}
