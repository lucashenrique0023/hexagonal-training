package lab.lhss.hexagonal.application;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductInterface {

    public final String DISABLED = "Disabled";
    public final String ENABLED = "Enabled";

    boolean isValid();
    void enable();
    void disable();
    UUID getID();
    String getName();
    String getStatus();
    BigDecimal getPrice();
}
