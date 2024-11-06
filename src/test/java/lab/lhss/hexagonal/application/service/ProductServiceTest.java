package lab.lhss.hexagonal.application.service;

import lab.lhss.hexagonal.application.entity.Product;
import lab.lhss.hexagonal.application.entity.ProductInterface;
import lab.lhss.hexagonal.application.persistence.ProductPersistenceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductPersistenceInterface persistence;

    @InjectMocks
    ProductService service;

    @Test
    public void shouldReturnProduct_whenExistsInPersistence() {
        var product = new Product("Product 1", BigDecimal.TEN, ProductInterface.ENABLED);
        when(persistence.get(any())).thenReturn(Optional.of(product));

        var result = service.get(UUID.randomUUID());
        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    public void shouldCreateProduct() {
        when(persistence.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        var result = service.create("Product 1", BigDecimal.TEN);
        assertNotNull(result);
        assertEquals(BigDecimal.TEN, result.getPrice());
        assertEquals(ProductInterface.ENABLED, result.getStatus());
        assertEquals("Product 1", result.getName());
    }

    @Test
    public void shouldEnableProduct() {
        var product = new Product("Product 1", BigDecimal.TEN, ProductInterface.DISABLED);
        assertEquals(ProductInterface.DISABLED, product.getStatus());
        assertTrue(product.isValid());

        when(persistence.save(any())).thenReturn(product);

        var result = service.enable(product);
        assertEquals(ProductInterface.ENABLED, result.getStatus());
    }

    @Test
    public void shouldDisableProduct() {
        var product = new Product("Product 1", BigDecimal.ZERO, ProductInterface.ENABLED);
        assertEquals(ProductInterface.ENABLED, product.getStatus());
        assertTrue(product.isValid());

        when(persistence.save(any())).thenReturn(product);

        var result = service.disable(product);
        assertEquals(ProductInterface.DISABLED, result.getStatus());
    }

}