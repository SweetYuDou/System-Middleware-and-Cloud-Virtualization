package pub.synx.pojo.vo.req;

import jakarta.validation.constraints.NotBlank;
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
public class BindReq {

    @NotBlank
    private String id;

    @NotEmpty
    private List<String> ids;
}
