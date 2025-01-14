package pub.synx.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author SynX TA
 * @version 2024
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("tbl_user")
public class User extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private String id;
    /*
     * 名称
     */
    private String name;

    // 账号
    private String account;

    //密码
    private String password;

    //性别， 0表示男，1表示女
    private Integer gender;

    //用户邮箱
    private String mail;

    //用户状态
    private Integer status;

    //盐
    private String salt;

    public User(String id, String account, String name, Integer gender, String description,
                String mail, Integer status, String extension, String creatorId, Long createdTime,
                Long lastUpdateTime, String lastOperatorId){
        this.id = id;
        this.name = name;
        this.account = account;
        this.gender =  gender;
        this.description = description;
        this.mail = mail;
        this.extension = extension;
        this.createdTime = createdTime;
        this.creatorId = creatorId;
        this.status = status;
        this.lastOperatorId = lastOperatorId;
        this.lastUpdateTime = lastUpdateTime;
    }

}
