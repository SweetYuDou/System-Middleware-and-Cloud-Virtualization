package tech.synx.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.synx.Request.ArithmeticRequest;
import tech.synx.Result.ArithmeticResult;

@RestController
public class ArithmeticController {

    // 求和接口
    @PostMapping("/v1/arithmetic/operation/add")
    public ArithmeticResult add(@RequestBody ArithmeticRequest request) {
        int op1 = request.getOp1();
        int op2 = request.getOp2();
        int result = op1 + op2;
        return new ArithmeticResult(1, result);
    }

    // 求差接口
    @PostMapping("/v1/arithmetic/operation/subtract")
    public ArithmeticResult subtract(@RequestBody ArithmeticRequest request) {
        int op1 = request.getOp1();
        int op2 = request.getOp2();
        int result = op1 - op2;
        return new ArithmeticResult(1, result);
    }

    // 求积接口
    @PostMapping("/v1/arithmetic/operation/multiply")
    public ArithmeticResult multiply(@RequestBody ArithmeticRequest request) {
        int op1 = request.getOp1();
        int op2 = request.getOp2();
        int result = op1 * op2;
        return new ArithmeticResult(1, result);
    }

    // 求商接口
    @PostMapping("/v1/arithmetic/operation/divide")
    public ArithmeticResult divide(@RequestBody ArithmeticRequest request) {
        int op1 = request.getOp1();
        int op2 = request.getOp2();
        int status = (op2 == 0)? 0 : 1;
        int result = (op2 == 0)? 0 : op1 / op2;
        int remainder = (op2 == 0)? 0 : op1 % op2;
        return new ArithmeticResult(status, result);
    }
}
