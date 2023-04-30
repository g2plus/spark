package cn.fiesacyum.controller;

import cn.fiesacyum.pojo.User;
import cn.fiesacyum.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @PostMapping
    public boolean add(@RequestBody User user) {
        Boolean add = userService.add(user);
        return add;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        Boolean delete = userService.delete(id);
        return delete;
    }

    @PutMapping
    public boolean update(@RequestBody User user) {
        Boolean update = userService.update(user);
        return update;
    }

    @GetMapping("{page}/{size}")
    public PageInfo<User> findAll(@PathVariable Integer page,
                                  @PathVariable Integer size) {
        Page<User> all = userService.findAll(page, size);
        PageInfo<User> users = new PageInfo<User>(all);
        return users;
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Integer id) {
        User byId = userService.findById(id);
        return byId;
    }
}

