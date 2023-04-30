package cn.tanhua.server.controller;

import cn.tanhua.model.vo.PageResult;
import cn.tanhua.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论-提交
     */
    @PostMapping
    public ResponseEntity commitComment(@RequestBody Map map){
        commentService.commit((String)map.get("movementId"),(String)map.get("comment"));
        return ResponseEntity.ok(null);
    }



    /**
     * 评论-点赞
     */
    @GetMapping("/{id}/like")
    public ResponseEntity likeThisComment(@PathVariable("id") String commentId){
        Integer likeCount = commentService.likeThisComment(commentId);
        return ResponseEntity.ok(likeCount);
    }


    /**
     * 评论-取消点赞
     * @param commentId
     * @return
     */
    @GetMapping("/{id}/dislike")
    public ResponseEntity dislikeThisComment(@PathVariable("id") String commentId){
        Integer likeCount = commentService.dislikeThisComment(commentId);
        return ResponseEntity.ok(likeCount);
    }

    /**
     * 评论-列表
     */
    @GetMapping
    public ResponseEntity getCommentList(String movementId,
                                         @RequestParam("page") int page,
                                         @RequestParam("pagesize") int pagesize){
        PageResult pageResult = commentService.getCommentList(movementId,page,pagesize);
        return ResponseEntity.ok(pageResult);
    }

}
