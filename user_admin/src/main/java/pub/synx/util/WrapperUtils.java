package pub.synx.util;

import pub.synx.enums.ResultStatusEnum;
import pub.synx.pojo.vo.resp.ResultMessage;

/**
 * @author SynX TA
 * @version 2024
 **/
public class WrapperUtils {

    private WrapperUtils() {
    }

    public static ResultMessage success() {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage(ResultStatusEnum.SUCCESS.getMessage());
        return resultMessage;
    }

    public static ResultMessage success(Object o) {
        ResultMessage res = success();
        res.setData(o);
        return res;
    }

    public static ResultMessage success(String message, Object o) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage(message);
        resultMessage.setData(o);
        return resultMessage;
    }

    public static ResultMessage error(String message) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(ResultStatusEnum.FAILURE.getCode());
        resultMessage.setMessage(message);
        return resultMessage;
    }
}
