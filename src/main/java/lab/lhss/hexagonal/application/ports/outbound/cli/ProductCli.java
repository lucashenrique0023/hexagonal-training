package lab.lhss.hexagonal.application.ports.outbound.cli;

import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;
import lab.lhss.hexagonal.application.ports.inbound.ProductServiceInterface;

public interface ProductCli {
    String run(ProductServiceInterface service, String action, ProductEntityInterface product);
}
