package lab.lhss.hexagonal.application.ports.db;

import lab.lhss.hexagonal.application.entity.ProductEntityInterface;

import java.util.Optional;
import java.util.UUID;

public interface ProductReader {
    Optional<ProductEntityInterface> get(UUID id);
}
