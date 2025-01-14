package pub.synx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import pub.synx.enums.ResultMessageEnum;
import pub.synx.util.IdGenerator;
import pub.synx.util.exception.DataProcessException;
import pub.synx.mapper.UserMapper;
import pub.synx.pojo.po.User;
import pub.synx.service.CommonService;
import pub.synx.util.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author SynX TA
 * @version 2024
 **/
@Slf4j
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    private final UserMapper userMapper;

    public CommonServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String account, String password) {
        User user = new LambdaQueryChainWrapper<>(userMapper)
                .eq(User::getAccount, account)
                .one();

        if (Objects.isNull(user)) {
            return false;
        }

        return PasswordHelper.encryptPassword(password, user.getSalt()).equals(user.getPassword());
    }


    @Override
    public String register(User user) {
        if (Objects.isNull(user.getAccount()) || Objects.isNull(user.getPassword())) {
            throw new DataProcessException(ResultMessageEnum.ACCOUNT_OR_PASSWORD_NULL.getMessage());
        }

        List<User> users = new LambdaQueryChainWrapper<>(userMapper)
                .eq(User::getAccount, user.getAccount())
                .or()
                .eq(User::getMail, user.getMail())
                .list();

        if (!users.isEmpty()) {
            throw new DataProcessException(ResultMessageEnum.ACCOUNT_OR_MAIL_EXISTS.getMessage());
        }

        // 创建用户
        String id = IdGenerator.get();
        user.setId(id);
        user.setStatus(1);
        PasswordHelper.encryptPassword(user);
        userMapper.insert(user);

        return id;
    }
}
