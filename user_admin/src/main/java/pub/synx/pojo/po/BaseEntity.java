package pub.synx.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SynX TA
 * @version 2024
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

    /**
     * 生成时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected Long createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    protected Long lastUpdateTime;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    protected String creatorId;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    protected String lastOperatorId;

    /**
     * 描述字段
     */
    protected String description;

    /**
     * 拓展字段
     */
    protected String extension;

}

