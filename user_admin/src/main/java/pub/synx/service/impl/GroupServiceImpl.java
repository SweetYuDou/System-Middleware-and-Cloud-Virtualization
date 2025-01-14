package pub.synx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.synx.enums.ResultMessageEnum;
import pub.synx.util.IdGenerator;
import pub.synx.util.exception.DataProcessException;
import pub.synx.mapper.GroupMapper;
import pub.synx.mapper.UserAndGrpMapper;
import pub.synx.mapper.UserMapper;
import pub.synx.pojo.po.Group;
import pub.synx.pojo.po.User;
import pub.synx.pojo.po.UserAndGrp;
import pub.synx.service.GroupService;
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
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    private final UserMapper userMapper;

    private final UserAndGrpMapper userAndGrpMapper;

    public GroupServiceImpl(UserMapper userMapper, UserAndGrpMapper userAndGrpMapper) {
        this.userMapper = userMapper;
        this.userAndGrpMapper = userAndGrpMapper;
    }

    @Override
    public List<String> addGroup(List<Group> groups) {
        List<String> ids = IdGenerator.get(groups.size());
        groups.forEach(group -> group.setId(ids.get(groups.indexOf(group))));
        saveBatch(groups);
        return ids;
    }

    @Override
    public boolean updateGroup(List<Group> groups) {
        groups.forEach(group -> {
            if (Objects.isNull(group.getId())) {
                throw new DataProcessException(ResultMessageEnum.GROUPID_NULL.getMessage());
            }
        });

        List<String> groupIds = groups.stream().map(Group::getId).toList();

        Long count = this.lambdaQuery().in(Group::getId, groupIds).count();

        if (count != groups.size()) {
            throw new DataProcessException(ResultMessageEnum.ID_NOTEXIST.getMessage());
        }
        return updateBatchById(groups);
    }

    @Override
    public boolean deleteGroup(List<String> ids) {
        LambdaQueryWrapper<UserAndGrp> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserAndGrp::getGroupId, ids);
        userAndGrpMapper.delete(wrapper);
        return removeBatchByIds(ids);
    }

    @Override
    public List<Group> queryGroup(Group group) {
        LambdaQueryWrapper<Group> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(group.getId())) {
            wrapper.eq(Group::getId, group.getId());
        }
        if (Objects.nonNull(group.getName())) {
            wrapper.eq(Group::getName, group.getName());
        }
        return this.list(wrapper);
    }

    @Override
    public boolean bindUsers(String id, List<String> ids) {
        Group group = getById(id);
        if (Objects.isNull(group)) {
            throw new DataProcessException(ResultMessageEnum.ID_NOTEXIST.getMessage());
        }
        Long count = new LambdaQueryChainWrapper<>(userMapper)
                .in(User::getId, ids)
                .count();
        if (ids.size() != count) {
            throw new DataProcessException(ResultMessageEnum.IDS_NOT_ALL_USABLE.getMessage());
        }
        ArrayList<UserAndGrp> userAndGrps = new ArrayList<>();
        ids.forEach(userId -> userAndGrps.add(new UserAndGrp(userId, id)));
        userAndGrpMapper.batchInsert(userAndGrps);
        return true;
    }

    @Override
    public boolean unbindUsers(String id, List<String> ids) {
        LambdaQueryWrapper<UserAndGrp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAndGrp::getGroupId, id);
        wrapper.in(UserAndGrp::getUserId, ids);
        userAndGrpMapper.delete(wrapper);
        return true;
    }

    @Override
    public List<User> getUsers(String id) {
        List<String> userIds = new LambdaQueryChainWrapper<>(userAndGrpMapper)
                .eq(UserAndGrp::getGroupId, id)
                .select(UserAndGrp::getUserId)
                .list().stream()
                .map(UserAndGrp::getUserId).toList();

        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }

        return new LambdaQueryChainWrapper<>(userMapper)
                .in(User::getId, userIds)
                .list();
    }
}
