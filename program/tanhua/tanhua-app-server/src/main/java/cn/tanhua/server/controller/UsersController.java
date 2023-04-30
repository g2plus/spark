package cn.tanhua.server.controller;

import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.UserInfoVo;
import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.SettingsService;
import cn.tanhua.server.service.UserInfoService;
import cn.tanhua.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;


    @PostMapping("/header")
    public ResponseEntity header(MultipartFile headPhoto, @RequestHeader("Authorization")String token){
        Long userId = UserHolder.getUserId();
        userInfoService.updateHeadPhoto(userId,headPhoto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/phone/sendVerificationCode")
    public ResponseEntity sendVerificationCode(@RequestHeader("Authorization")String token){
        //1.发送信息
        userService.sendVerificationCode(UserHolder.getMobile());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/phone/checkVerificationCode")

    public ResponseEntity checkVerificationCode(@RequestHeader("Authorization")String token,@RequestBody Map in){

        boolean flag = userService.checkVerificationCode(UserHolder.getMobile(), (String)in.get("verificationCode"));
        //将数据以key=value形式，@ResponseBody将对象转换成一个json字符串
        Map map=new HashMap();
        map.put("verification",flag);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/phone")
    public ResponseEntity changPhoneNumber(@RequestHeader("Authorization")String token,@RequestBody Map map){
       userService.updatePhoneNumber((String)map.get("phone"));
       return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity updateUserInfo(@RequestHeader("Authorization")String token,@RequestBody UserInfo userInfo){
        Long userInfoId = UserHolder.getUserId();
        userInfo.setId(userInfoId);
        userInfoService.updateUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity getUserInfo(@RequestHeader("Authorization")String token,Long userID){
        //根据用户传入的id查询用户信息
        UserInfo userInfo = userInfoService.findByUserId(userID);
        //如果查无信息，查询用户本人信息
        if(null==userInfo){
            //如果传入的userID为null,用户本人信息，否则查询指定id信息
            userID = UserHolder.getUserId();
        }
        return ResponseEntity.ok(userInfoService.getUserInfoVo(userID));
    }
}

