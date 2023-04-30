package top.arhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.arhi.entity.AjaxResult;
import top.arhi.service.PlaceService;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    //TODO 实现分页查询效果,自定义PageDomain(模仿bilibili)
    @GetMapping("/load")
    public AjaxResult load(Integer pid){
        return AjaxResult.success("converted successfully",placeService.load(pid));
    }


}
