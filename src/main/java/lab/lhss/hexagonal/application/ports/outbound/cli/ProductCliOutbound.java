package lab.lhss.hexagonal.application.ports.outbound.cli;

import lab.lhss.hexagonal.application.entity.ProductEntityInterface;
import lab.lhss.hexagonal.application.service.ProductServiceInterface;

public interface ProductCliOutbound {
    String run(ProductServiceInterface service, String action, ProductEntityInterface product);
}
