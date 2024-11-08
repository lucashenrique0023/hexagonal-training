package lab.lhss.hexagonal;

import lab.lhss.hexagonal.application.ports.db.ProductPersistenceOutbound;
import lab.lhss.hexagonal.application.service.ProductService;
import lab.lhss.hexagonal.application.service.ProductServiceInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class HexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonalApplication.class, args);
	}

	@Bean
	public ProductServiceInterface productService(GenericApplicationContext context) {
		var persistence = context.getBean(ProductPersistenceOutbound.class);
		return new ProductService(persistence);
	}

}
