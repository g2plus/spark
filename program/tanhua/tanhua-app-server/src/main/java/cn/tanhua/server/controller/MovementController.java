package cn.tanhua.server.controller;

import cn.tanhua.model.mongo.Movement;
import cn.tanhua.model.mongo.Visitors;
import cn.tanhua.model.vo.MovementVo;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.VisitorsVo;
import cn.tanhua.server.interceptor.UserHolder;
import cn.tanhua.server.service.CommentService;
import cn.tanhua.server.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @Autowired
    private CommentService commentService;

    /**
     *  发布动态
     */
    @PostMapping
    public ResponseEntity publishMoment(MultipartFile[] imageContent, Movement movement) throws IOException {
        movementService.publishMoment(imageContent,movement);
        return ResponseEntity.ok(null);
    }

    /**
     * 获取自己发的动态
     * @param page
     * @param pagesize
     * @param userId
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity getMyMovements(@RequestParam(name="page",defaultValue="1")int page,
                                         @RequestParam(name="pagesize",defaultValue = "10")int pagesize,
                                         Long userId){
        PageResult pageResult = movementService.getMyMovements(userId,page,pagesize);
        return ResponseEntity.ok(pageResult);
    }


    /**
     * 获取自已可见的好友动态
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping
    public ResponseEntity getFriendMoment(@RequestParam(defaultValue = "1")int page,
                                          @RequestParam(defaultValue = "10")int pagesize){
        PageResult pageResult = movementService.getFriendMoment(UserHolder.getUserId(), page, pagesize);
        return ResponseEntity.ok(pageResult);
    }


    /**
     * valuable 获取系统推荐的用户动态
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("/recommend")
    public ResponseEntity getRecommendedMovements(@RequestParam(name="page",defaultValue = "1")int page,
                                                  @RequestParam(name="pagesize",defaultValue = "10")int pagesize){
        PageResult pageResult = movementService.getRecommendedMovements(UserHolder.getUserId(),page,pagesize);
        return ResponseEntity.ok(pageResult);
    }



    /**
     * 根据动态id获取单条
     * @param movementId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity getOneMoment(@PathVariable("id")String movementId){
        MovementVo movementVo = movementService.findById(movementId);
        return ResponseEntity.ok(movementVo);
    }


    /**
     *动态点赞
     */
    @GetMapping("/{id}/like")
    public ResponseEntity getLike(@PathVariable("id")String movementId){
        Integer likeCount = commentService.likeThisMovement(movementId);
        return ResponseEntity.ok(likeCount);
    }


    /**
     * 取消动态点赞
     */
    @GetMapping("/{id}/dislike")
    public ResponseEntity disLike(@PathVariable("id")String movementId){
        Integer likeCount = commentService.dislikeThisMovement(movementId);
        return ResponseEntity.ok(likeCount);
    }

    /**
     * 查看访客列表
     */
    @GetMapping("/visitors")
    public ResponseEntity visitors(){
        List<VisitorsVo> visitorsList = movementService.visitors();
        return ResponseEntity.ok(visitorsList);
    }

}
