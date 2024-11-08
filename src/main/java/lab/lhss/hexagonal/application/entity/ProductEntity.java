package lab.lhss.hexagonal.application.entity;

import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductEntity implements ProductEntityInterface {

    private final UUID id;
    private String name;
    private BigDecimal price;
    private String status;

    public ProductEntity(String name, BigDecimal price, String status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public ProductEntity(UUID id, String name, BigDecimal price, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    @Override
    public boolean isValid() {
        if (this.status.isEmpty()) {
            this.status = DISABLED;
        }

        if (!this.status.equals(ENABLED) && !this.status.equals(DISABLED)) {
            System.err.println("Status must be " + ENABLED + " or " + DISABLED);
            return false;
        }

        if (this.price.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("The price must be greater than zero");
            return false;
        }

        return true;
    }

    @Override
    public void enable() {
        if (this.price.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalStateException("Price must be greater than zero to enable product");
        }
        this.status = ENABLED;
    }

    @Override
    public void disable() {
        if (this.price.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("Price must be zero in order to disable the product");
        }
        this.status = DISABLED;
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
