package lab.lhss.hexagonal.adapters.db;

import lab.lhss.hexagonal.application.entity.ProductEntity;
import lab.lhss.hexagonal.application.entity.ProductEntityInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductEntityPersistenceImplTest {

    @Autowired
    private ProductPersistenceOutboundImpl persistence;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldGetProduct_whenItExistsOnDatabase() {
        String sql = "INSERT INTO products (id, name, price, status) VALUES (?, ?, ?, ?)";
        var id = UUID.randomUUID();
        jdbcTemplate.update(sql, id.toString(), "Product1", BigDecimal.TEN, ProductEntityInterface.ENABLED);
        var result = persistence.get(id);
        assertTrue(result.isPresent());

        var product = result.get();
        assertNotNull(product);
        assertEquals(id, product.getID());
        assertEquals("Product1", product.getName());
        assertEquals(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP), product.getPrice());
        assertEquals(ProductEntityInterface.ENABLED, product.getStatus());
    }

    @Test
    void shouldCreateAndUpdateProduct() {
        var id = UUID.randomUUID();
        var product = new ProductEntity(id, "Product676", BigDecimal.ONE, ProductEntityInterface.ENABLED);
        persistence.save(product);

        var result = persistence.get(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getID());
        assertEquals(product.getName(), result.get().getName());
        assertEquals(product.getPrice().setScale(2, RoundingMode.HALF_UP), result.get().getPrice());
        assertEquals(product.getStatus(), result.get().getStatus());

        var product2 = new ProductEntity(product.getID(), "Product6789", BigDecimal.TEN, ProductEntityInterface.ENABLED);
        persistence.save(product2);

        var result2 = persistence.get(product2.getID());
        assertTrue(result2.isPresent());
        assertEquals(product2.getID(), result2.get().getID());
        assertEquals(product2.getName(), result2.get().getName());
        assertEquals(product2.getPrice().setScale(2, RoundingMode.HALF_UP), result2.get().getPrice());
        assertEquals(product2.getStatus(), result2.get().getStatus());
    }

}