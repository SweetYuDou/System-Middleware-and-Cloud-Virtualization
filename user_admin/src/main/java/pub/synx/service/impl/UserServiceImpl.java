package pub.synx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.synx.enums.ResultMessageEnum;
import pub.synx.util.IdGenerator;
import pub.synx.util.PasswordHelper;
import pub.synx.util.exception.DataProcessException;
import pub.synx.mapper.GroupMapper;
import pub.synx.mapper.UserAndGrpMapper;
import pub.synx.mapper.UserMapper;
import pub.synx.pojo.po.Group;
import pub.synx.pojo.po.User;
import pub.synx.pojo.po.UserAndGrp;
import pub.synx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author SynX TA
 * @version 2024
 **/
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final GroupMapper groupMapper;

    private final UserAndGrpMapper userAndGrpMapper;

    public UserServiceImpl(GroupMapper groupMapper, UserAndGrpMapper userAndGrpMapper) {
        this.groupMapper = groupMapper;
        this.userAndGrpMapper = userAndGrpMapper;
    }

    @Override
    public List<String> addUser(List<User> users) {
        List<String> ids = IdGenerator.get(users.size());
        users.forEach(user -> {
            user.setId(ids.get(users.indexOf(user)));
            user.setStatus(1);
            PasswordHelper.encryptPassword(user);
        });
        saveBatch(users);
        return ids;
    }

    @Override
    public boolean updateUser(List<User> users) {
        users.forEach(user -> {
            if (Objects.isNull(user.getId())) {
                throw new DataProcessException(ResultMessageEnum.USERID_NULL.getMessage());
            }
        });

        List<String> userIds = users.stream().map(User::getId).toList();
        Long count = this.lambdaQuery().in(User::getId, userIds).count();
        if (count != users.size()) {
            throw new DataProcessException(ResultMessageEnum.ID_NOTEXIST.getMessage());
        }
        return updateBatchById(users);
    }

    @Override
    public boolean deleteUser(List<String> ids) {
        LambdaQueryWrapper<UserAndGrp> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserAndGrp::getUserId, ids);
        userAndGrpMapper.delete(wrapper);
        return removeBatchByIds(ids);
    }

    /**
     * 查询用户
     */
    @Override
    public List<User> queryUser(User user){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(user.getId())) {
            wrapper.eq(User::getId, user.getId());
        }
        if (Objects.nonNull(user.getName())) {
            wrapper.eq(User::getName, user.getName());
        }
        return this.list(wrapper);
    }

    @Override
    public boolean bindGroups(String id, List<String> ids) {
        User user = getById(id);
        if (Objects.isNull(user)) {
            throw new DataProcessException(ResultMessageEnum.ID_NOTEXIST.getMessage());
        }
        Long count = new LambdaQueryChainWrapper<>(groupMapper)
                .in(Group::getId, ids)
                .count();
        if (ids.size() != count) {
            throw new DataProcessException(ResultMessageEnum.IDS_NOT_ALL_USABLE.getMessage());
        }
        ArrayList<UserAndGrp> userAndGrps = new ArrayList<>();
        ids.forEach(groupId -> userAndGrps.add(new UserAndGrp(id, groupId)));
        userAndGrpMapper.batchInsert(userAndGrps);
        return true;
    }

    @Override
    public boolean unbindGroups(String id, List<String> ids) {
        LambdaQueryWrapper<UserAndGrp> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserAndGrp::getGroupId, ids);
        wrapper.eq(UserAndGrp::getUserId, id);
        userAndGrpMapper.delete(wrapper);
        return true;
    }

    @Override
    public List<Group> getGroups(String id) {
        List<String> groupIds = new LambdaQueryChainWrapper<>(userAndGrpMapper)
                .eq(UserAndGrp::getUserId, id)
                .select(UserAndGrp::getGroupId)
                .list().stream()
                .map(UserAndGrp::getGroupId).toList();

        if (groupIds.isEmpty()) {
            return new ArrayList<>();
        }

        return new LambdaQueryChainWrapper<>(groupMapper)
                .in(Group::getId, groupIds)
                .list();
    }
}
