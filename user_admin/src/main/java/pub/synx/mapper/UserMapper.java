package pub.synx.mapper;

import org.apache.ibatis.annotations.Mapper;
import pub.synx.pojo.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author SynX TA
 * @version 2024
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
