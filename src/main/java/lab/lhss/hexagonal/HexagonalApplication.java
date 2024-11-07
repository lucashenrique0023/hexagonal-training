package lab.lhss.hexagonal;

import lab.lhss.hexagonal.application.persistence.ProductPersistenceInterface;
import lab.lhss.hexagonal.application.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class HexagonalApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(HexagonalApplication.class, args);
		var persistence = context.getBean(ProductPersistenceInterface.class);
		var productService = new ProductService(persistence);
		var p1 = productService.create("Product 001", BigDecimal.TEN);
		var p2 = productService.get(p1.getID());

		System.out.println(p1.getID());
		System.out.println(p2.getID());
	}

}
