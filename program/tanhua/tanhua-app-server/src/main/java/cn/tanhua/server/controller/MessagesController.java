package cn.tanhua.server.controller;

import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.UserInfoVo;
import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.MessagesService;
import cn.tanhua.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据环信IDd的规则查询用户信息,显示用户头像
     */
    @GetMapping("/userinfo")
    public ResponseEntity getUserInfo(String huanxinId) {
        UserInfoVo userInfoVo = messagesService.findByHuanXinId(huanxinId);
        return ResponseEntity.ok(userInfoVo);
    }

    /**
     * 添加好友
     */
    @PostMapping("/contacts")
    public ResponseEntity contacts(@RequestBody Map map) {
        Long userIdRequested = Long.valueOf(map.get("userId").toString());
        Long userIdResponsed = UserHolder.getUserId();
        messagesService.addFriends(userIdRequested, userIdResponsed);
        return ResponseEntity.ok(null);
    }


    /**
     * 分页查询联系人列表
     */
    @GetMapping("/contacts")
    public ResponseEntity contacts(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int pagesize,
                                   String keyword) {
        PageResult pageResult = messagesService.getContacts(UserHolder.getUserId(),page,pagesize,keyword);
        return ResponseEntity.ok(pageResult);
    }
}
