package lab.lhss.hexagonal.application.ports.outbound.persistence;

import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;

import java.util.Optional;
import java.util.UUID;

public interface ProductReader {
    Optional<ProductEntityInterface> get(UUID id);
}