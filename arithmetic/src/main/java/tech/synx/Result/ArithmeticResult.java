package tech.synx.Result;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArithmeticResult {
    private int status;
    private int result;

    // 构造函数、Getter和Setter方法
    public ArithmeticResult(int status, int result) {
        this.status = status;
        this.result = result;
    }

}