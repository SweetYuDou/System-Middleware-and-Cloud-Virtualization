package pub.synx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pub.synx.pojo.po.UserAndGrp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author SynX TA
 * @version 2024
 **/
@Mapper
public interface UserAndGrpMapper extends BaseMapper<UserAndGrp> {

    @Insert({
            "<script>",
            "INSERT INTO tbl_user_and_group (user_id, group_id) ",
            "VALUES ",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item.userId}, #{item.groupId})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("list") List<UserAndGrp> userAndGrpList);
}
