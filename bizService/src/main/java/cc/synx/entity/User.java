package cc.synx.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户类
 * @version 1.0
 * @ClassName User
 **/
@Data
@TableName("tbl_user")
public class User {

    // 用户标识，采用雪花算法
    private String id;
    // 电话号码
    private String mobilePhone;
    // 用户姓名
    private String name;

    public User(String id, String mobilePhone, String name) {
        this.id = id;
        this.mobilePhone = mobilePhone;
        this.name = name;
    }
}
