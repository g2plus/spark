package cn.tanhua.server.service;


import cn.tanhua.autoconfig.template.AipFaceTemplate;
import cn.tanhua.autoconfig.template.OssTemplate;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.model.vo.UserInfoVo;
import cn.tanhua.server.exception.BusinessException;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Service
public class UserInfoService {

    @DubboReference
    private UserInfoApi userInfoApi;

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private AipFaceTemplate aipFaceTemplate;

    public void save(UserInfo userInfo) {
        userInfoApi.save(userInfo);
    }

    public void updateHeadPhoto(Long userId, MultipartFile headPhoto) {
        InputStream inputStream=null;
        try {
            inputStream = headPhoto.getInputStream();
        } catch (IOException e) {
            throw new BusinessException(ErrorResult.error());
        }
        String URL = ossTemplate.upLoad(inputStream, headPhoto.getOriginalFilename());

        if(!aipFaceTemplate.aipFace(URL)){
            throw new BusinessException(ErrorResult.faceError());
        }else{
            //根据token中的id信息查找到用户对应的UserInfo，并进行头像的设置
            UserInfo userInfo = userInfoApi.findById(userId);
            userInfo.setAvatar(URL);
            userInfoApi.update(userInfo);
        }
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoApi.update(userInfo);
    }

    public UserInfoVo getUserInfoVo(Long userId) {
        //将UserInfo进行拷贝到userInfoVo
        UserInfo userInfo = userInfoApi.findById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();

        //同名同类型的字段进行拷贝
        BeanUtils.copyProperties(userInfo,userInfoVo);

        if(userInfo.getAge()!=null){
            userInfoVo.setAge(userInfo.getAge().toString());
        }
        return userInfoVo;
    }

    public UserInfo findByUserId(Long userID) {
        return userInfoApi.findById(userID);
    }
}
