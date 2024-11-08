package lab.lhss.hexagonal.application.ports.db;

import lab.lhss.hexagonal.application.entity.ProductEntityInterface;

public interface ProductWriter {
    ProductEntityInterface save(ProductEntityInterface product);
}
