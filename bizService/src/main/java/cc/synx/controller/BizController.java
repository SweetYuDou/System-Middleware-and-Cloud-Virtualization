package cc.synx.controller;

import cc.synx.api.UserClient;
import cc.synx.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/biz")
public class BizController {
    private final UserClient userClient;

    public BizController(UserClient userClient){
        this.userClient = userClient;
    }

    /**
     * 新增用户并返回新增后的用户列表
     * @param users
     * @return
     */
    @PostMapping
    public List<User> addAndGetUsers(@RequestBody List<User> users){
        boolean b = userClient.addUsers(users);
        if(b){
            return userClient.queryUsers(null, null);
        }
        return null;
    }
}
