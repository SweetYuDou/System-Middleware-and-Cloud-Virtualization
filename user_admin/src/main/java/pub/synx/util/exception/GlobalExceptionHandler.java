package pub.synx.util.exception;

import pub.synx.enums.ResultStatusEnum;
import pub.synx.pojo.vo.resp.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author SynX TA
 * @version 2024
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理器
     */
    @ExceptionHandler({Exception.class})
    public ResultMessage HandlerError(Exception ex) {
        log.error(ex.getMessage(), ex);

        ResultMessage resultMessage = new ResultMessage();
        //设置返回状态码
        resultMessage.setStatus(ResultStatusEnum.FAILURE.getCode());
        //由于唯一约束导致数据库操作失败
        resultMessage.setMessage((ex.getMessage()));
        if (ex instanceof DataIntegrityViolationException){
            resultMessage.setMessage("您输入的信息违反唯一约束，可能是数据库已存在或与正在输入的约束信息重复");
        } else if(ex instanceof SQLIntegrityConstraintViolationException){
            resultMessage.setMessage("实体关系未解除！");
        }

        return resultMessage;
    }
}
