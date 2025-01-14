package pub.synx.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import pub.synx.pojo.vo.req.BindReq;
import pub.synx.pojo.vo.req.DeleteReq;
import pub.synx.pojo.vo.req.EntitiesReq;
import pub.synx.pojo.vo.req.IdReq;
import pub.synx.util.CommonUtils;
import pub.synx.util.WrapperUtils;
import pub.synx.pojo.po.User;
import pub.synx.pojo.vo.resp.ResultMessage;
import pub.synx.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SynX TA
 * @version 2024
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加用户
     */
    @PostMapping
    public ResultMessage addUser(@RequestBody EntitiesReq<User> req) {
        return WrapperUtils.success(userService.addUser(req.getEntities()));
    }

    /**
     * 修改用户
     */
    @PutMapping
    public ResultMessage updateUser(@RequestBody EntitiesReq<User> req) {
        return WrapperUtils.success(userService.updateUser(req.getEntities()));
    }

    /**
     * 删除用户
     */
    @DeleteMapping
    public ResultMessage deleteUser(@RequestBody @Valid DeleteReq req) {
        return WrapperUtils.success(userService.deleteUser(req.getIds()));
    }

    /**
     * 查询用户基本信息
     */
    @GetMapping
    public ResultMessage queryUser(@RequestBody User user) {
        return WrapperUtils.success(CommonUtils.desensitize(userService.queryUser(user)));
    }

    /**
     * 绑定分组
     */
    @PutMapping("/groups")
    public ResultMessage bindGroups(@RequestBody @Valid BindReq req) {
        return WrapperUtils.success(userService.bindGroups(req.getId(), req.getIds()));
    }

    /**
     * 解绑分组
     */
    @DeleteMapping("/groups")
    public ResultMessage unbindGroups(@RequestBody @Valid BindReq req) {
        return WrapperUtils.success(userService.unbindGroups(req.getId(), req.getIds()));
    }

    /**
     * 获取用户的分组列表
     */
    @GetMapping("/groups")
    public ResultMessage getGroups(@RequestBody @Valid IdReq req) {
        return WrapperUtils.success(CommonUtils.desensitize(userService.getGroups(req.getId())));
    }
}
