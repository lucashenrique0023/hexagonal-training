package lab.lhss.hexagonal.application.persistence;

import lab.lhss.hexagonal.application.entity.ProductInterface;

public interface ProductWriter {
    ProductInterface save(ProductInterface product);
}
