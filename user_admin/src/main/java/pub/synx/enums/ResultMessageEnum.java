package pub.synx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author SynX TA
 * @version 2024
 **/
@Getter
@AllArgsConstructor
@SuppressWarnings("unused")
public enum ResultMessageEnum {

    MAIL_NULL(0,"邮箱[mail]不能为空"),
    CODE_NULL(1,"验证码[code]不能为空"),
    NAME_NULL(2,"姓名[name]不能为空"),
    GENDER_NULL(3,"性别[gender]不能为空"),
    MAIL_EXISTS(4,"邮箱已注册"),
    ACCOUNT_OR_PASSWORD_NULL(5,"账号或密码不能为空"),
    ACCOUNT_OR_MAIL_EXISTS(6,"账号或邮箱已注册"),
    RESIGTER_SUCCESS(7,"注册成功！"),
    CODE_OVERDUE(8,"验证码已过期，请获取验证码"),
    CODE_ERROR(9,"验证码错误"),
    CODE_EXISTS(10,"您的验证码还未过期"),
    CODE_SENT(11,"验证码已发出，5分钟内有效，请确保邮箱地址格式正确！"),
    LOGIN_SUCCESS(12,"登录成功！"),
    ACCOUNT_NOTEXISTS(13,"账号不存在"),
    PWD_ERROR(14,"密码错误！"),
    UNAUTHENTICATE(15,"用户未认证，请先登录或添加用户token！"),
    UNAUTHORIZE(16,"您无权访问该操作！sorry~"),
    USER_NULL(17,"当前无登录用户!"),
    GET_SUCCESS(18,"获取成功！"),
    LOGOUT_SUCCESS(19,"登出成功！"),
    UPDATE_SUCCESS(20,"修改成功！"),
    MAIL_GET_CODE(21,"请确认您是否绑定邮箱，如绑定请获取验证码！"),
    UNBIND_SUCCESS(22,"解绑成功!"),
    BIND_SUCCESS(23,"绑定成功!"),
    NEWPWD_NULL(24,"新密码[newPwd]不能为空"),
    OLDPWD_NULL(25,"原密码[oldPwd]不能为空"),
    DELETE_SUCCESS(26,"删除成功!"),
    MAIL_CANNOTBIND(27,"您的邮箱非空，无法绑定！"),
    MAIL_CANNOTUNBIND(28,"您的邮箱为空，无法解绑！"),
    PWD_NULL(29,"密码[password]不能为空"),
    OLDPWD_ERROR(30,"原密码错误"),
    GROUPIDS_NULL(31,"分组Id列表[groupIds]不能为空"),
    GROUPIDS_ERROR(32,"请输入您所在分组Id列表"),
    ADMIN_DELETE_FAIL(33,"系统默认管理员admin无法删除"),
    USERLIST_NULL(34,"用户列表[userlist]不能为空"),
    ID_NULL(35,"用户Id[id]不能为空"),
    IDS_NULL(36,"用户Id列表[ids]不能为空"),
    ID_NOTEXIST(37,"Id不存在"),
    USERATTR_NULL(38,"请输入user字段"),
    GROUPLIST_NULL(39,"分组列表[grouplist]不能为空"),
    GROUPLIST_NOTEXISTS(40,"您输入的分组Id列表没有可操作Id"),
    USERLIST_NOTEXISTS(43,"您输入的用户Id列表没有可操作Id"),
    IDS_NOT_ALL_USABLE(44, "ids中包含不存在的ID"),
    GROUPID_NULL(44,"分组Id[id]不能为空"),
    USERID_NULL(45,"用户Id[id]不能为空"),
    MAIL_NOTEXISTS(46,"邮箱不存在"),
    PWD_NOTEXISTS(47,"用户密码未配置，无法通过该方式登录"),
    USER_NOTEXISTS(48,"用户不存在"),
    ALL_USELESS(49,"未输入任何有效属性,密码[password]、邮箱[mail]不能通过该操作修改"),
    CASCADE_NULL(50,"是否级联删除[cascade]不能为空，1表示级联解绑分组和权限，0表示不级联（会留下无效关联关系）"),
    SENDCODE_NULL(51,"用户填写的验证码[mail-code]不能为空"),
    REALCODE_NULL(52,"真实验证码[realCode]不能为空"),
    MESSAGE_HIDE(53,"XXXX-XXXX-XXXX"),
    MAILORPHONE_NOTEXISTS(54,"请保证每个用户至少有邮箱或手机号属性"),
    ATTRIBUTE_EXISTS(55,"单一手机号或邮箱只能绑定一个用户！");

    private final int code;

    private final String message;
}
