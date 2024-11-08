package lab.lhss.hexagonal.application.service;

import lab.lhss.hexagonal.application.entity.ProductEntityInterface;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductServiceInterface {
    ProductEntityInterface get(UUID id);
    ProductEntityInterface create(String name, BigDecimal price);
    ProductEntityInterface enable(ProductEntityInterface product);
    ProductEntityInterface disable(ProductEntityInterface product);
}
