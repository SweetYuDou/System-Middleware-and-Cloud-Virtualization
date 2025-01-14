package pub.synx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author SynX TA
 * @version 2024
 **/

@Getter
@AllArgsConstructor
public enum ResultStatusEnum {

    /**
     * 成功
     */
    SUCCESS(1,"操作执行成功"),

    /**
     * 失败
     */
    FAILURE(0,"操作执行失败");

    private final int code;

    private final String message;
}
