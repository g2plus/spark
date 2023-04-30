package cn.tanhua.server.controller;


import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.QuestionService;
import cn.tanhua.server.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private QuestionService questionService;



    @GetMapping("/settings")
    public ResponseEntity getSettings(@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(settingsService.getSettings());
    }


    @PostMapping("/questions")
    public ResponseEntity saveQuestions(@RequestHeader("Authorization")String token,@RequestBody Map map){
        questionService.saveOrUpdateQuestions(UserHolder.getUserId(),(String)map.get("content"));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/notifications/setting")
    public ResponseEntity saveNotificationSetting(@RequestHeader("Authorization")String token,@RequestBody Map map){
        settingsService.saveOrUpadateNotificationSetting(UserHolder.getUserId(),map);
        return ResponseEntity.ok(null);
    }


    @GetMapping("blacklist")
    public ResponseEntity getBlacklist(@RequestHeader("Authorization")String token,
                                       @RequestParam(defaultValue = "1")int page,
                                       @RequestParam(defaultValue ="10")int pagesize){
        return ResponseEntity.ok(settingsService.getBlackList(UserHolder.getUserId(), page, pagesize));
    }


    @DeleteMapping("/blacklist/{uid}")
    public ResponseEntity removeBlockedUid(@RequestHeader("Authorization")String token,@PathVariable("uid")Long uid){
        settingsService.removeFromBlackList(UserHolder.getUserId(),uid);
        return ResponseEntity.ok(null);
    }




}
