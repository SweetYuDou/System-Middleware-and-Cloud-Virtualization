package pub.synx.controller;

import pub.synx.pojo.vo.req.LoginReq;
import pub.synx.util.WrapperUtils;
import pub.synx.pojo.po.User;
import pub.synx.pojo.vo.resp.ResultMessage;
import pub.synx.service.CommonService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SynX TA
 * @version 2024
 **/
@Slf4j
@RestController
@RequestMapping
public class LoginController {

    private final CommonService commonService;

    public LoginController(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * 账号密码登录
     * @param req 账号密码
     */
    @PostMapping("/login/account")
    public ResultMessage login(@RequestBody LoginReq req) {
        return WrapperUtils.success(commonService.login(req.getAccount(), req.getPassword()));
    }

    /**
     * 账号密码注册
     *
     * @param user 用户信息
     */
    @PostMapping("/register/account")
    public ResultMessage register(@RequestBody User user) {
        return WrapperUtils.success(commonService.register(user));
    }
}
