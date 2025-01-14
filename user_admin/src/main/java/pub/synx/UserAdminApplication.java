package pub.synx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author SynX TA
 * @version 2024
 **/
@SpringBootApplication(scanBasePackages = {"pub.synx"})
@MapperScan("pub.synx.mapper")
@EnableTransactionManagement
public class UserAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAdminApplication.class, args);
    }
}
