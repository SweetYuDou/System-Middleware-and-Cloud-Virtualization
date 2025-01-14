package pub.synx.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tbl_group")
public class Group extends BaseEntity {

    /**
     * Id
     */
    @TableId
    private String id;
    /*
     * 名称
     */
    private String name;

    public Group(String id, String name, String description, String extension, String creatorId,Long createdTime, Long lastUpdateTime, String lastOperatorId) {
        super(createdTime, lastUpdateTime, creatorId, lastOperatorId, description, extension);
        this.id = id;
        this.name = name;
    }
}
