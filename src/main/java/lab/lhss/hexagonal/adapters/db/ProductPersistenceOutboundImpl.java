package lab.lhss.hexagonal.adapters.db;

import lab.lhss.hexagonal.application.entity.ProductEntity;
import lab.lhss.hexagonal.application.entity.ProductEntityInterface;
import lab.lhss.hexagonal.application.ports.db.ProductPersistenceOutbound;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductPersistenceOutboundImpl implements ProductPersistenceOutbound {

    JdbcTemplate jdbcTemplate;

    public ProductPersistenceOutboundImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ProductEntityInterface> get(UUID id) {
        String sql = "select * from products where id = ?";
        var product = jdbcTemplate.queryForObject(sql, new RowMapper<ProductEntityInterface>() {
            @Override
            public ProductEntityInterface mapRow(ResultSet rs, int rowNum) throws SQLException {
                var id = UUID.fromString(rs.getString("id"));
                return new ProductEntity(
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
    public ProductEntityInterface save(ProductEntityInterface product) {
        String sql = "select id from products where id = ?";
        List<String> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("id"), product.getID().toString());
        if (result.isEmpty()) {
            return create(product);
        } else {
            return update(product);
        }
    }

    private ProductEntityInterface create(ProductEntityInterface product) {
        String sql = "INSERT INTO products (id, name, price, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getID().toString(), product.getName(), product.getPrice(), product.getStatus());
        return product;
    }

    private ProductEntityInterface update(ProductEntityInterface product) {
        String sql = "UPDATE products set name = ?, price = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStatus(), product.getID().toString());
        return product;
    }



}
