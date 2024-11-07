package lab.lhss.hexagonal.adapters.db;

import lab.lhss.hexagonal.application.entity.Product;
import lab.lhss.hexagonal.application.entity.ProductInterface;
import lab.lhss.hexagonal.application.persistence.ProductPersistenceInterface;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductPersistenceImpl implements ProductPersistenceInterface {

    JdbcTemplate jdbcTemplate;

    public ProductPersistenceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ProductInterface> get(UUID id) {
        String sql = "select * from products where id = ?";
        var product = jdbcTemplate.queryForObject(sql, new RowMapper<ProductInterface>() {
            @Override
            public ProductInterface mapRow(ResultSet rs, int rowNum) throws SQLException {
                var id = UUID.fromString(rs.getString("id"));
                return new Product(
                        id,
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getString("status")
                );
            };
        }, id.toString());

        return Optional.ofNullable(product);
    }

    @Override
    public ProductInterface save(ProductInterface product) {
        String sql = "select id from products where id = ?";
        List<String> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("id"), product.getID().toString());
        if (result.isEmpty()) {
            return create(product);
        } else {
            return update(product);
        }
    }

    private ProductInterface create(ProductInterface product) {
        String sql = "INSERT INTO products (id, name, price, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getID().toString(), product.getName(), product.getPrice(), product.getStatus());
        return product;
    }

    private ProductInterface update(ProductInterface product) {
        String sql = "UPDATE products set name = ?, price = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStatus(), product.getID().toString());
        return product;
    }



}
