package cn.tanhua.server.controller;

import cn.tanhua.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map map){
        userService.sendVerificationCode((String)map.get("phone"));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/loginVerification")
    public ResponseEntity loginVerification(@RequestBody Map map){
        return ResponseEntity.ok(userService.login((String)map.get("phone"),(String)map.get("verificationCode")));
    }
}
