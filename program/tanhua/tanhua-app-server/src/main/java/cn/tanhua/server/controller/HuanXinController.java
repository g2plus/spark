package cn.tanhua.server.controller;

import cn.tanhua.model.vo.HuanXinUserVo;
import cn.tanhua.server.service.HuanXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/huanxin")
public class HuanXinController {

    @Autowired
    private HuanXinService huanXinService;

    @GetMapping("/user")
    public ResponseEntity getHuanXinUser(){
        HuanXinUserVo huanXinUserVo=huanXinService.getHuanXinUser();
        return ResponseEntity.ok(huanXinUserVo);
    }

}
