package pub.synx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pub.synx.pojo.po.Group;
import pub.synx.pojo.po.User;

import java.util.List;

/**
 * @author SynX TA
 * @version 2024
 **/
public interface GroupService extends IService<Group> {

    List<String> addGroup(List<Group> groups);

    boolean updateGroup(List<Group> groups);

    boolean deleteGroup(List<String> ids);

    List<Group> queryGroup(Group group);

    boolean bindUsers(String id, List<String> ids);

    boolean unbindUsers(String id, List<String> ids);

    List<User> getUsers(String id);
}
