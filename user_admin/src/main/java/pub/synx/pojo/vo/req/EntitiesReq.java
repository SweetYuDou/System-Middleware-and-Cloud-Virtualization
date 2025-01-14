package pub.synx.pojo.vo.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import pub.synx.pojo.po.BaseEntity;
import pub.synx.pojo.po.Group;

import java.util.List;

/**
 * @author SynX TA
 * @version 2024
 **/
@Getter
@Setter
public class EntitiesReq<T extends BaseEntity> {

    @NotEmpty
    private List<T> entities;

}
