package lab.lhss.hexagonal.adapters.outbound.cli;

import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;
import lab.lhss.hexagonal.application.ports.outbound.cli.ProductCli;
import lab.lhss.hexagonal.application.ports.inbound.ProductServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class ProductCliImpl implements ProductCli {

    @Override
    public String run(ProductServiceInterface service, String action, ProductEntityInterface product) {
        var result = "";
        ProductEntityInterface dbProduct;

        try {
            switch (action) {
                case "create":
                    dbProduct = service.create(product.getName(), product.getPrice());
                    result = String.format("Product ID: %s with\n name: %s\n price: %s\n status: %s.\n Created successfully",
                            dbProduct.getID(), dbProduct.getName(), dbProduct.getPrice(), dbProduct.getStatus());
                    break;
                case "enable":
                    dbProduct = service.get(product.getID());
                    service.enable(dbProduct);
                    result = String.format("Product ID: %s has been enabled successfully", dbProduct.getID());
                    break;
                case "disable":
                    dbProduct = service.get(product.getID());
                    service.disable(dbProduct);
                    result = String.format("Product ID: %s has been disabled successfully", dbProduct.getID());
                    break;
                default:
                    dbProduct = service.get(product.getID());
                    result = String.format("Product ID: %s with\n name: %s\n price: %s\n status: %s.\n",
                            dbProduct.getID(), dbProduct.getName(), dbProduct.getPrice(), dbProduct.getStatus());
            }
        } catch (Exception ignored) {}

        return result;
    }
}
