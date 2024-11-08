package lab.lhss.hexagonal.application.ports.outbound.persistence;

import lab.lhss.hexagonal.application.entity.ProductEntityInterface;

public interface ProductWriter {
    ProductEntityInterface save(ProductEntityInterface product);
}
