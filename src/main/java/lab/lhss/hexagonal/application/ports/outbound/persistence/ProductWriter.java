package lab.lhss.hexagonal.application.ports.outbound.persistence;

import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;

public interface ProductWriter {
    ProductEntityInterface save(ProductEntityInterface product);
}
