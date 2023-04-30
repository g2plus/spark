package cn.tanhua.server.controller;

import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/loginReginfo")
    public ResponseEntity loginReginfo(@RequestHeader("Authorization") String token, @RequestBody UserInfo userInfo ){
       ///将userId设置到userInfo的id字段
        userInfo.setId(UserHolder.getUserId());
        userInfoService.save(userInfo);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/loginReginfo/head")
    public ResponseEntity loginReginfoHead(MultipartFile headPhoto, @RequestHeader("Authorization")String token){
        userInfoService.updateHeadPhoto(UserHolder.getUserId(),headPhoto);
        return ResponseEntity.ok(null);
    }
}
