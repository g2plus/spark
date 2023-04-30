package cn.tanhua.server.controller;


import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.BaiduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/baidu")
public class BaiduController {

    @Autowired
    private BaiduService baiduService;

    /**
     * longitude:经度
     * latitude:纬度
     */
    @PostMapping("/location")
    public ResponseEntity updateLocation(@RequestBody Map map){
        Double longitude = Double.valueOf(map.get("longitude").toString());
        Double latitude = Double.valueOf(map.get("latitude").toString());
        String address = map.get("addrStr").toString();
        baiduService.updateLocation(UserHolder.getUserId(),longitude,latitude,address);
        return ResponseEntity.ok(null);
    }
}
