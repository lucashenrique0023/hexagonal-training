package lab.lhss.hexagonal.application.service;

import lab.lhss.hexagonal.application.entity.Product;
import lab.lhss.hexagonal.application.entity.ProductInterface;
import lab.lhss.hexagonal.application.persistence.ProductPersistenceInterface;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductService implements ProductServiceInterface {

    ProductPersistenceInterface persistence;

    public ProductService(ProductPersistenceInterface persistence) {
        this.persistence = persistence;
    }

    @Override
    public ProductInterface get(UUID id) {
        return persistence.get(id).orElse(null);
    }

    @Override
    public ProductInterface create(String name, BigDecimal price) {
        var product = new Product(UUID.randomUUID(), name, price, ProductInterface.ENABLED);
        product.isValid();
        return persistence.save(product);
    }

    @Override
    public ProductInterface enable(ProductInterface product) {
        product.enable();
        return persistence.save(product);
    }

    @Override
    public ProductInterface disable(ProductInterface product) {
        product.disable();
        return persistence.save(product);
    }

}
