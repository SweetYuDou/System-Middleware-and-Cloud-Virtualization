package pub.synx.service;


import com.baomidou.mybatisplus.extension.service.IService;
import pub.synx.pojo.po.Group;
import pub.synx.pojo.po.User;
import java.util.List;

/**
 * @author SynX TA
 * @version 2024
 **/
public interface UserService extends IService<User> {
    List<String> addUser(List<User> users);

    boolean updateUser(List<User> users);

    boolean deleteUser(List<String> ids);

    List<User> queryUser(User user);

    boolean bindGroups(String id, List<String> ids);

    boolean unbindGroups(String id, List<String> ids);

    List<Group> getGroups(String id);
}