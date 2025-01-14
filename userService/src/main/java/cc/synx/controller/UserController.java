package cc.synx.controller;
import cc.synx.entity.User;
import cc.synx.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) { // 构造器注入 userService
        this.userService = userService;
    }

    /**
     * 查询用户
     */
    @GetMapping("")
    public List<User> queryUsers(@RequestParam(required = false) String id, @RequestParam(required = false) String  name) {
        return userService.queryUsers(id, name);
    }

    /**
     * 新增用户
     */
    @PostMapping("")
    public boolean addUsers(@RequestBody List<User> users) {
        return userService.addUsers(users);
    }
}