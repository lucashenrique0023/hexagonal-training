package lab.lhss.hexagonal.adapters.cli;

import lab.lhss.hexagonal.adapters.outbound.cli.ProductCliImpl;
import lab.lhss.hexagonal.application.ports.inbound.ProductEntityInterface;
import lab.lhss.hexagonal.application.ports.inbound.ProductServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductEntityCliImplTest {

    ProductCliImpl adapter = new ProductCliImpl();

    @Test
    public void shouldCreateProduct_whenActionCreateIsPassedToCLI() {
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        ProductEntityInterface product = Mockito.mock(ProductEntityInterface.class);
        var id = UUID.randomUUID();
        when(product.getID()).thenReturn(id);
        when(product.getName()).thenReturn("Product Name");
        when(product.getStatus()).thenReturn("Product Status");
        when(product.getPrice()).thenReturn(BigDecimal.TEN);

        when(service.create(Mockito.any(), Mockito.any())).thenReturn(product);

        String result = adapter.run(service, "create", product);
        assertEquals(String.format("Product ID: %s with\n name: %s\n price: %s\n status: %s.\n Created successfully",
                product.getID(), product.getName(), product.getPrice(), product.getStatus()), result);
    }

    @Test
    public void shouldEnableProduct_whenActionEnableIsPassedToCLI() {
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        ProductEntityInterface product = Mockito.mock(ProductEntityInterface.class);
        var id = UUID.randomUUID();
        when(product.getID()).thenReturn(id);
        when(service.get(Mockito.any())).thenReturn(product);

        String result = adapter.run(service, "enable", product);
        assertEquals(String.format("Product ID: %s has been enabled successfully", product.getID()), result);
    }

    @Test
    public void shouldDisableProduct_whenActionEnableIsPassedToCLI() {
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        ProductEntityInterface product = Mockito.mock(ProductEntityInterface.class);
        var id = UUID.randomUUID();
        when(product.getID()).thenReturn(id);
        when(service.get(Mockito.any())).thenReturn(product);

        String result = adapter.run(service, "disable", product);
        assertEquals(String.format("Product ID: %s has been disabled successfully", product.getID()), result);
    }

    @Test
    public void shouldGetProductByDefault_whenNoActionIsIdentifiedByCLI() {
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        ProductEntityInterface product = Mockito.mock(ProductEntityInterface.class);
        var id = UUID.randomUUID();
        when(product.getID()).thenReturn(id);
        when(service.get(Mockito.any())).thenReturn(product);

        String result = adapter.run(service, "notexists", product);
        assertEquals(String.format("Product ID: %s with\n name: %s\n price: %s\n status: %s.\n",
                product.getID(), product.getName(), product.getPrice(), product.getStatus()), result);
    }

    @Test
    public void shouldReturnEmptyResult_whenSomeErrorOccurs() {
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        ProductEntityInterface product = Mockito.mock(ProductEntityInterface.class);
        var id = UUID.randomUUID();
        when(product.getID()).thenReturn(id);
        when(service.get(Mockito.any())).thenThrow(new IllegalStateException());

        String result = adapter.run(service, "enable", product);
        assertEquals("", result);
    }




}