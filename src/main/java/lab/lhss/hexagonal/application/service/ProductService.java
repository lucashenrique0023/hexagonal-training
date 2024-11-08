package lab.lhss.hexagonal.application.service;

import lab.lhss.hexagonal.application.entity.ProductEntity;
import lab.lhss.hexagonal.application.entity.ProductEntityInterface;
import lab.lhss.hexagonal.application.ports.db.ProductPersistenceOutbound;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductService implements ProductServiceInterface {

    ProductPersistenceOutbound persistence;

    public ProductService(ProductPersistenceOutbound persistence) {
        this.persistence = persistence;
    }

    @Override
    public ProductEntityInterface get(UUID id) {
        return persistence.get(id).orElse(null);
    }

    @Override
    public ProductEntityInterface create(String name, BigDecimal price) {
        var product = new ProductEntity(UUID.randomUUID(), name, price, ProductEntityInterface.ENABLED);
        product.isValid();
        return persistence.save(product);
    }

    @Override
    public ProductEntityInterface enable(ProductEntityInterface product) {
        product.enable();
        return persistence.save(product);
    }

    @Override
    public ProductEntityInterface disable(ProductEntityInterface product) {
        product.disable();
        return persistence.save(product);
    }

}
