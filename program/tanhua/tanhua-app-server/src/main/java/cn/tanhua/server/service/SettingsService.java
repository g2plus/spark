package cn.tanhua.server.service;

import cn.tanhua.dubbo.api.BlackListApi;
import cn.tanhua.dubbo.api.QuestionApi;
import cn.tanhua.dubbo.api.SettingsApi;
import cn.tanhua.dubbo.api.UserApi;
import cn.tanhua.model.pojo.Question;
import cn.tanhua.model.pojo.Settings;
import cn.tanhua.model.pojo.User;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.SettingsVo;
import cn.tanhua.server.interceptor.UserHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SettingsService {

    @DubboReference
    private SettingsApi settingsApi;

    @DubboReference
    private QuestionApi questionApi;

    @DubboReference
    private UserApi userApi;

    @DubboReference
    private BlackListApi blackListApi;

    public SettingsVo getSettings() {
        //1.创建对象settingsVo用于保存数据
        SettingsVo settingsVo = new SettingsVo();
        //2.根据用户id查询tab_settings数据表信息
        Settings settings = settingsApi.findByUserId(UserHolder.getUserId());
        //3.根据用户id查询tab_settings数据表信息
        Question question = questionApi.findByUserId(UserHolder.getUserId());
        //4.根据用用户id(保存到数据表tb_user确定id值并保存在token中的id)来查数据表信息
        User user = userApi.findByUserId(UserHolder.getUserId());
        //5.将用户的id与mobile下信息存入settingsVo
        settingsVo.setId(user.getId());
        settingsVo.setPhone(user.getMobile());
        //6.设置用户问题，初次登录系统数据表无此用户的问题设置，及公告等设置信息。使用假数据进行页面数据填充
        String txt = question == null ? "How are you doing?":question.getTxt();
        //7.将文本存入
        settingsVo.setStrangerQuestion(txt);
        if(settings!=null){
            //8.settings数据存在将数据存入。
            settingsVo.setLikeNotification(settings.getLikeNotification());
            settingsVo.setPinglunNotification(settings.getPinglunNotification());
            settingsVo.setGonggaoNotification(settings.getGonggaoNotification());
        }
        return settingsVo;
    }

    public void saveOrUpadateNotificationSetting(Long userId, Map map) {
        //1.根据用户id查询tb_settings
        Settings settings=settingsApi.findByUserId(userId);
        //2.判断数据是否存在
        if(null==settings){
            //2.1数据不存在，新建对象，设置用户id等信息。
            settings=new Settings();
            settings.setUserId(userId);
            settings.setLikeNotification((Boolean)map.get("likeNotification"));
            settings.setPinglunNotification((Boolean)map.get("pinglunNotification"));
            settings.setGonggaoNotification((Boolean)map.get("gonggaoNotification"));
            settingsApi.saveNotificationSetting(settings);
        }else{
            //2.数据更新
            settings.setLikeNotification((Boolean)map.get("likeNotification"));
            settings.setPinglunNotification((Boolean)map.get("pinglunNotification"));
            settings.setGonggaoNotification((Boolean)map.get("gonggaoNotification"));
            settingsApi.updateNotificationSetting(settings);
        }
    }

    public PageResult getBlackList(Long userId, int page, int pagesize) {
        //传入userId,当前页码及页面显示条数发,调用blackListApi获取Ipage。
        IPage<UserInfo> blackList = blackListApi.getBlackList(userId, page, pagesize);
        //创建自定义PageResult对象，并返回
        /*List<T> getRecords();*/
        return new PageResult(page,pagesize,blackList.getTotal(),blackList.getRecords());
    }

    public void removeFromBlackList(Long userId, Long uid) {
        blackListApi.reomoveFromBlackList(userId,uid);
    }
}
