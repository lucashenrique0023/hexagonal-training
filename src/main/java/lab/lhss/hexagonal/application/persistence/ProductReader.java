package lab.lhss.hexagonal.application.persistence;

import lab.lhss.hexagonal.application.entity.ProductInterface;

import java.util.Optional;
import java.util.UUID;

public interface ProductReader {
    Optional<ProductInterface> get(UUID id);
}
