package pub.synx.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import pub.synx.pojo.vo.req.BindReq;
import pub.synx.pojo.vo.req.DeleteReq;
import pub.synx.pojo.vo.req.EntitiesReq;
import pub.synx.pojo.vo.req.IdReq;
import pub.synx.util.CommonUtils;
import pub.synx.util.WrapperUtils;
import pub.synx.pojo.po.Group;
import pub.synx.pojo.vo.resp.ResultMessage;
import pub.synx.service.GroupService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 新增分组
     * @param req 分组
     * @return
     */
    @PostMapping
    public ResultMessage addGroups(@RequestBody EntitiesReq<Group> req) {
        return WrapperUtils.success(groupService.addGroup(req.getEntities()));
    }

    /**
     * 修改分组
     * @param req 分组
     */
    @PutMapping
    public ResultMessage updateGroups(@RequestBody EntitiesReq<Group> req) {
        return WrapperUtils.success(groupService.updateGroup(req.getEntities()));
    }

    /**
     * 删除分组
     */
    @DeleteMapping
    public ResultMessage deleteGroup(@RequestBody @Valid DeleteReq req) {
        return WrapperUtils.success(groupService.deleteGroup(req.getIds()));
    }

    /**
     * 查询分组
     */
    @GetMapping
    public ResultMessage queryGroup(@RequestBody Group group) {
        return WrapperUtils.success(CommonUtils.desensitize(groupService.queryGroup(group)));
    }

    /**
     * 绑定用户列表
     */
    @PutMapping("/users")
    public ResultMessage bindUsers(@RequestBody @Valid BindReq req) {
        return WrapperUtils.success(groupService.bindUsers(req.getId(), req.getIds()));
    }

    /**
     * 解绑用户列表
     */
    @DeleteMapping("/users")
    public ResultMessage unbindUsers(@RequestBody @Valid BindReq req) {
        return WrapperUtils.success(groupService.unbindUsers(req.getId(), req.getIds()));
    }

    /**
     * 获取分组的用户列表
     */
    @GetMapping("/users")
    public ResultMessage getUsers(@RequestBody @Valid IdReq req) {
        return WrapperUtils.success(CommonUtils.desensitize(groupService.getUsers(req.getId())));
    }
}
