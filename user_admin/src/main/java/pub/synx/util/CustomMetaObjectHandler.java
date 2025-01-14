package pub.synx.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author SynX TA
 * @version 2024
 **/
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = "admin";
        long time = new Date().getTime();
        this.setFieldValByName("createdTime", time, metaObject);
        this.setFieldValByName("creatorId", userId, metaObject);
        this.setFieldValByName("lastUpdateTime", time, metaObject);
        this.setFieldValByName("lastOperatorId", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = "admin";
        this.setFieldValByName("lastUpdateTime", new Date().getTime(), metaObject);
        this.setFieldValByName("lastOperatorId", userId, metaObject);
    }
}
