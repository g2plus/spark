package cn.fiesacyum.controller;

import cn.fiesacyum.domain.User;
import cn.fiesacyum.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
    private UserService userService;

    @PostMapping
    public void save(@RequestBody User user){
        System.out.println(user);
        userService.save(user);
    }

    @PutMapping
    public void update(@RequestBody User user){
        System.out.println(user);
        userService.update(user);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        userService.delete(uuid);
    }

    @GetMapping("/{uuid}")
    public User get(@PathVariable String uuid){
        System.out.println(userService.get(uuid));
        return userService.get(uuid);
    }

    @GetMapping("/{page}/{size}")
    public PageInfo<User> findAll(@PathVariable Integer page, @PathVariable Integer size){
        return userService.findAll(page,size);
    }

    @PostMapping("/login")
    public User login(String userName,String password){
        return userService.login(userName,password);
    }
}
