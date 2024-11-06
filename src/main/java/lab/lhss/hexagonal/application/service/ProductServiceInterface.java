package lab.lhss.hexagonal.application.service;

import lab.lhss.hexagonal.application.entity.ProductInterface;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductServiceInterface {
    ProductInterface get(UUID id);
    ProductInterface create(String name, BigDecimal price);
    ProductInterface enable(ProductInterface product);
    ProductInterface disable(ProductInterface product);
}
