package lab.lhss.hexagonal.adapters.web.controller;

import lab.lhss.hexagonal.application.entity.ProductEntityInterface;
import lab.lhss.hexagonal.application.service.ProductServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductServiceInterface productService;

    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductEntityInterface getProduct(@PathVariable("id") String id) {
        return productService.get(UUID.fromString(id));
    }

}
