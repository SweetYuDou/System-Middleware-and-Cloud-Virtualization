package pub.synx.pojo.vo.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author SynX TA
 * @version 2024
 **/
@Getter
@Setter
public class IdReq {

    @NotBlank
    private String id;
}
