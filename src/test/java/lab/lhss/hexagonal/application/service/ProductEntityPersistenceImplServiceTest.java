package lab.lhss.hexagonal.application.service;

import lab.lhss.hexagonal.application.entity.ProductEntity;
import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;
import lab.lhss.hexagonal.application.ports.outbound.persistence.ProductPersistence;
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
class ProductEntityPersistenceImplServiceTest {

    @Mock
    ProductPersistence persistence;

    @InjectMocks
    ProductService service;

    @Test
    public void shouldReturnProduct_whenExistsInPersistence() {
        var product = new ProductEntity("Product 1", BigDecimal.TEN, ProductEntityInterface.ENABLED);
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
        assertEquals(ProductEntityInterface.ENABLED, result.getStatus());
        assertEquals("Product 1", result.getName());
    }

    @Test
    public void shouldEnableProduct() {
        var product = new ProductEntity("Product 1", BigDecimal.TEN, ProductEntityInterface.DISABLED);
        assertEquals(ProductEntityInterface.DISABLED, product.getStatus());
        assertTrue(product.isValid());

        when(persistence.save(any())).thenReturn(product);

        var result = service.enable(product);
        assertEquals(ProductEntityInterface.ENABLED, result.getStatus());
    }

    @Test
    public void shouldDisableProduct() {
        var product = new ProductEntity("Product 1", BigDecimal.ZERO, ProductEntityInterface.ENABLED);
        assertEquals(ProductEntityInterface.ENABLED, product.getStatus());
        assertTrue(product.isValid());

        when(persistence.save(any())).thenReturn(product);

        var result = service.disable(product);
        assertEquals(ProductEntityInterface.DISABLED, result.getStatus());
    }

}