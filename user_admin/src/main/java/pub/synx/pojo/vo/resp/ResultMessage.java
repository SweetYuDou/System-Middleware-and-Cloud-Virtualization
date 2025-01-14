package pub.synx.pojo.vo.resp;

import lombok.Data;
import pub.synx.enums.ResultStatusEnum;

/**
 * @author SynX TA
 * @version 2024
 **/
@Data
public class ResultMessage {

    // 默认返回代码
    private Integer status = ResultStatusEnum.SUCCESS.getCode();

    // 默认返回信息
    private String message;

    // 默认返回数据
    private Object Data;
}
