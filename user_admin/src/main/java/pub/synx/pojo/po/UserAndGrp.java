package pub.synx.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author SynX TA
 * @version 2024
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tbl_user_and_group")
public class UserAndGrp extends BaseEntity{

    //用户Id
    private String userId;

    //分组Id
    private String groupId;

}
