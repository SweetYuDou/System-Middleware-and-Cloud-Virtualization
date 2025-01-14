package pub.synx.util;

import pub.synx.pojo.po.BaseEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SynX TA
 * @version 2024
 **/
public class CommonUtils {

    /**
     * 数据脱敏
     */
    public static <T extends BaseEntity> List<Map<String, Object>> desensitize(List<T> entities) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (T entity : entities) {
            Map<String, Object> map = new HashMap<>();
            addFieldsToMap(entity.getClass().getSuperclass(), entity, map);
            addFieldsToMap(entity.getClass(), entity, map);

            map.remove("password");
            map.remove("salt");
            map.remove("serialVersionUID");
            maps.add(map);
        }
        return maps;
    }

    private static void addFieldsToMap(Class<?> clazz, Object entity, Map<String, Object> map) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
