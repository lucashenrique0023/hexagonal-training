package lab.lhss.hexagonal.application.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductPersistenceImplTest {

    @Test
    public void shouldEnableProduct_whenPriceIsHigherThanZero() {
        Product product = new Product("Hello", BigDecimal.TEN, ProductInterface.DISABLED);
        product.enable();
        assertEquals(ProductInterface.ENABLED, product.getStatus());
    }

    @Test
    public void shouldThrowIllegalStateException_whenEnablingProductWithPriceEqualsZero() {
        Product product = new Product("Hello", BigDecimal.ZERO, ProductInterface.DISABLED);
        var exception = assertThrows(IllegalStateException.class, product::enable);
        assertEquals("Price must be greater than zero to enable product", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalStateException_whenEnablingProductWithPriceLowerThanZero() {
        Product product = new Product("Hello", new BigDecimal("-123.45"), ProductInterface.DISABLED);
        var exception = assertThrows(IllegalStateException.class, product::enable);
        assertEquals("Price must be greater than zero to enable product", exception.getMessage());
    }

    @Test
    public void shouldDisableProduct_whenPriceIsEqualThanZero() {
        Product product = new Product("Hello", BigDecimal.ZERO, ProductInterface.ENABLED);
        product.disable();
        assertEquals(ProductInterface.DISABLED, product.getStatus());
    }

    @Test
    public void shouldThrowIllegalStateException_whenDisablingProductWithPriceDifferentFromZero() {
        Product product = new Product("Hello", BigDecimal.TEN, ProductInterface.ENABLED);
        var exception = assertThrows(IllegalStateException.class, product::disable);
        assertEquals("Price must be zero in order to disable the product", exception.getMessage());
    }

    @Test
    public void testValidProduct() {
        Product product = new Product("Hello", BigDecimal.TEN, ProductInterface.ENABLED);
        assertTrue(product.isValid());
    }

    @Test
    public void testInValidProduct_whenPriceIsLowerThanZero() {
        Product product = new Product("Hello", new BigDecimal("-1.00") , ProductInterface.ENABLED);
        assertFalse(product.isValid());
    }

    @Test
    public void testInValidProduct_whenStatusIsDifferentFromEnabledDisabled() {
        Product product = new Product("Hello", new BigDecimal("-1.00") , "ANOTHER_STATUS");
        assertFalse(product.isValid());
    }

    @Test
    public void testValidProduct_whenEmptyStatusGivenDuringProductCreation() {
        Product product = new Product("Hello", BigDecimal.TEN, "");
        assertTrue(product.isValid());
    }

}