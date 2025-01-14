package cc.synx;

import cc.synx.api.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class BizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizServiceApplication.class, args);
	}
	@Bean
	UserClient initWebClient() {
		WebClient client = WebClient.builder().baseUrl("http://localhost:8080/").build();
		HttpServiceProxyFactory factory =
				HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();
		return factory.createClient(UserClient.class);
	}

}
