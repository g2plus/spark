package cn.tanhua.server.controller;

import cn.tanhua.model.vo.PageResult;
import cn.tanhua.server.service.SmallVideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/smallVideos")
public class SmallVideosController {

    @Autowired
    private SmallVideosService smallVideosService;

    @PostMapping
    public ResponseEntity uploadVideo(MultipartFile videoThumbnail,MultipartFile videoFile) throws IOException {
        smallVideosService.uploadVideo(videoThumbnail,videoFile);
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity loadVideo(@RequestParam(defaultValue = "1",value="page") int page,
                                    @RequestParam(defaultValue = "1",value="pagesize") int pagesize){
       PageResult pageResult = smallVideosService.loadVideo(page,pagesize);
       return ResponseEntity.ok(pageResult);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity likeVideo(@PathVariable("id") String videoId){
        //TODO 视频点赞 与动态点赞类似 点赞总数
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity dislikeVideo(@PathVariable("id") String videoId){
        //TODO 取消视频点赞 点赞总数
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{uid}/userFocus")
    public ResponseEntity follow(@PathVariable("uid") Long userId){
        //TODO 关注用户 hashFocus
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{uid}/userUnfoucus")
    public ResponseEntity unfollow(@PathVariable("uid") Long userId){
        //TODO 取消关注用户 hasFocus
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getComments(@RequestParam(defaultValue = "1",value="page") int page,
                                      @RequestParam(defaultValue = "10",value = "pagesize") int pagesize){
        //TODO 获取视频评论列表pageResult;
        return ResponseEntity.ok(null);
    }

    @PostMapping("/comments/{id}/like")
    public ResponseEntity likeComment(@PathVariable("id") String commentId){
        //TODO 喜欢评论  点亮状态
        return ResponseEntity.ok(null);
    }

    @PostMapping("/comments/{id}/dislike")
    public ResponseEntity dislikeComment(@PathVariable("id") String commentId){
        // TODO 不喜欢评论
        return ResponseEntity.ok(null);
    }
}
