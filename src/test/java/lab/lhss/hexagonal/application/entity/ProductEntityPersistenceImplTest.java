package lab.lhss.hexagonal.application.entity;

import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductEntityPersistenceImplTest {

    @Test
    public void shouldEnableProduct_whenPriceIsHigherThanZero() {
        ProductEntity productEntity = new ProductEntity("Hello", BigDecimal.TEN, ProductEntityInterface.DISABLED);
        productEntity.enable();
        assertEquals(ProductEntityInterface.ENABLED, productEntity.getStatus());
    }

    @Test
    public void shouldThrowIllegalStateException_whenEnablingProductWithPriceEqualsZero() {
        ProductEntity productEntity = new ProductEntity("Hello", BigDecimal.ZERO, ProductEntityInterface.DISABLED);
        var exception = assertThrows(IllegalStateException.class, productEntity::enable);
        assertEquals("Price must be greater than zero to enable product", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalStateException_whenEnablingProductWithPriceLowerThanZero() {
        ProductEntity productEntity = new ProductEntity("Hello", new BigDecimal("-123.45"), ProductEntityInterface.DISABLED);
        var exception = assertThrows(IllegalStateException.class, productEntity::enable);
        assertEquals("Price must be greater than zero to enable product", exception.getMessage());
    }

    @Test
    public void shouldDisableProduct_whenPriceIsEqualThanZero() {
        ProductEntity productEntity = new ProductEntity("Hello", BigDecimal.ZERO, ProductEntityInterface.ENABLED);
        productEntity.disable();
        assertEquals(ProductEntityInterface.DISABLED, productEntity.getStatus());
    }

    @Test
    public void shouldThrowIllegalStateException_whenDisablingProductWithPriceDifferentFromZero() {
        ProductEntity productEntity = new ProductEntity("Hello", BigDecimal.TEN, ProductEntityInterface.ENABLED);
        var exception = assertThrows(IllegalStateException.class, productEntity::disable);
        assertEquals("Price must be zero in order to disable the product", exception.getMessage());
    }

    @Test
    public void testValidProduct() {
        ProductEntity productEntity = new ProductEntity("Hello", BigDecimal.TEN, ProductEntityInterface.ENABLED);
        assertTrue(productEntity.isValid());
    }

    @Test
    public void testInValidProduct_whenPriceIsLowerThanZero() {
        ProductEntity productEntity = new ProductEntity("Hello", new BigDecimal("-1.00") , ProductEntityInterface.ENABLED);
        assertFalse(productEntity.isValid());
    }

    @Test
    public void testInValidProduct_whenStatusIsDifferentFromEnabledDisabled() {
        ProductEntity productEntity = new ProductEntity("Hello", new BigDecimal("-1.00") , "ANOTHER_STATUS");
        assertFalse(productEntity.isValid());
    }

    @Test
    public void testValidProduct_whenEmptyStatusGivenDuringProductCreation() {
        ProductEntity productEntity = new ProductEntity("Hello", BigDecimal.TEN, "");
        assertTrue(productEntity.isValid());
    }

}