package cn.tanhua.server.controller;

import cn.hutool.json.JSONUtil;
import cn.tanhua.autoconfig.template.HuanXinTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.QuestionApi;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.dubbo.api.VisitorsApi;
import cn.tanhua.model.dto.RecommendUserDto;
import cn.tanhua.model.pojo.Question;
import cn.tanhua.model.vo.NearUserVo;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.TodayBest;
import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.TanhuaService;
import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.apache.dubbo.config.annotation.DubboReference;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tanhua")
public class TanhuaController {

    @Autowired
    private TanhuaService tanhuaService;
    /**
     * 注入HuanXinTemplate
     */
    @Autowired
    private HuanXinTemplate huanXinTemplate;

    @DubboReference
    private UserInfoApi userInfoApi;

    @DubboReference
    private VisitorsApi visitorsApi;

    @GetMapping("/todayBest")
    public ResponseEntity getTodayBest(@RequestHeader("Authorization") String token) {
        TodayBest todayBest = tanhuaService.getTodayBest(UserHolder.getUserId());
        return ResponseEntity.ok(todayBest);
    }

    @GetMapping("/recommendation")
    public ResponseEntity getRecommendation(RecommendUserDto recommendUserDto) {
        PageResult pageResult = tanhuaService.getRecommendation(recommendUserDto);
        return ResponseEntity.ok(pageResult);
    }


    /**
     * 查看陌生人问题
     */
    @GetMapping("/strangerQuestions")
    public ResponseEntity getQuestion(Long userId) {
        String questionContent = tanhuaService.getQuestion(userId);
        return ResponseEntity.ok(questionContent);
    }

    /**
     * 查看佳人详情
     */
    @GetMapping("/{id}/personalInfo")
    public ResponseEntity showPersonalInfo(@PathVariable("id") Long userIdViewed) {
        //根据userIdViewed查找用户userInfo,获取recommenUser的缘分值,封装对象并返回
        TodayBest todayBest = tanhuaService.showPersonalInfo(userIdViewed);
        visitorsApi.addView(UserHolder.getUserId(),userIdViewed);
        return ResponseEntity.ok(todayBest);
    }


    /**
     * 回复陌生人问题
     */
    @PostMapping("/strangerQuestions")
    public ResponseEntity replyStrangerQuestions(@RequestBody Map map) {
        //java.lang.Integer cannot be cast to java.lang.String
        Long userId = Long.valueOf(map.get("userId").toString());
        String reply = map.get("reply").toString();
        tanhuaService.replyQuestions(userId, reply);
        return ResponseEntity.ok(null);
    }


    /**
     * 探花 - 左滑右滑
     */
    @GetMapping("/cards")
    public ResponseEntity card() {
        List<TodayBest> list = tanhuaService.card(UserHolder.getUserId());
        return ResponseEntity.ok(list);
    }

    /**
     * 不喜欢
     */
    @GetMapping("/{id}/unlove")
    public ResponseEntity unlove(@PathVariable("id") Long likeUserId) {
        tanhuaService.unlove(UserHolder.getUserId(),likeUserId);
        return ResponseEntity.ok(null);
    }


    /**
     * 喜欢
     */
    @GetMapping("/{id}/love")
    public ResponseEntity love(@PathVariable("id") Long likeUserId) {
        tanhuaService.love(UserHolder.getUserId(),likeUserId);
        return ResponseEntity.ok(null);
    }

    /**
     * 搜附近
     */
    @GetMapping("/search")
    public ResponseEntity getUserNear(String gender,Long distance){
        List<NearUserVo> list = tanhuaService.getUserNear(gender,distance,UserHolder.getUserId());
        return ResponseEntity.ok(list);
    }


}