package lab.lhss.hexagonal.application.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductInterface {

    String DISABLED = "Disabled";
    String ENABLED = "Enabled";

    boolean isValid();
    void enable();
    void disable();
    UUID getID();
    String getName();
    String getStatus();
    BigDecimal getPrice();
}
