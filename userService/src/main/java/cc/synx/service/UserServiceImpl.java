package cc.synx.service;
import cc.synx.SnowflakeUtil;
import cc.synx.entity.User;
import cc.synx.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {

    /**
     * 查询用户，支持id精确查询、name模糊查询
     */
    public List<User> queryUsers(String id, String name) {
        LambdaQueryChainWrapper<User> queryWrapper = new LambdaQueryChainWrapper<>(User.class);
        queryWrapper.eq(id != null && !id.isEmpty(), User::getId, id);
        queryWrapper.like(name != null && !name.isEmpty(), User::getName, name);
        return queryWrapper.list();
    }

    /**
     * 新增用户
     */
    public boolean addUsers(List<User> users) {
        SnowflakeUtil snowflakeUtil = new SnowflakeUtil(1L);
        for (User user : users) {
            user.setId(String.valueOf(snowflakeUtil.nextId()));
        }
        return saveBatch(users);
    }
}