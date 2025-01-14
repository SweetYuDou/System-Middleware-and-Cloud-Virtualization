package pub.synx.pojo.vo.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author SynX TA
 * @version 2024
 **/
@Setter
@Getter
public class DeleteReq {

    @NotEmpty(message = "待删除实体标识不能为空")
    private List<String> ids;
}
