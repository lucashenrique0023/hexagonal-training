package lab.lhss.hexagonal.application.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductEntityInterface {

    String DISABLED = "disabled";
    String ENABLED = "enabled";

    boolean isValid();
    void enable();
    void disable();
    UUID getID();
    String getName();
    String getStatus();
    BigDecimal getPrice();
}
