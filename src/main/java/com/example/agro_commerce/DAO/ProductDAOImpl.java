package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Product;
import com.example.agro_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = new RowMapper<>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            int productId = rs.getInt("product_id");
            String typeProduct = rs.getString("type_product");
            String nameProduct = rs.getString("name_product");
            BigDecimal valueProduct = rs.getBigDecimal("value_product");
            String descriptionProduct = rs.getString("description_product");
            String image = rs.getString("image");
            return new Product(productId, typeProduct, nameProduct, valueProduct, descriptionProduct, image);
        }
    };

    private final RowMapper<User> userRowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            int userId = rs.getInt("user_id");
            String userName = rs.getString("user_name");
            String email = rs.getString("user_email");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
            return new User(userId, userName, email, password, sex, birthDate);
        }
    };

    @Override
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO product (type_product, name_product, description_product, value_product, image) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, product.getType(), product.getName(), product.getDescription(), product.getValue(), product.getImage());
        return rowsAffected > 0;
    }

    @Override
    public List<Product> listAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public List<Product> listAllProductsByName(String name) {
        String sql = "SELECT * FROM product WHERE name_product = ?";
        return jdbcTemplate.query(sql, productRowMapper, name);
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET type_product = ?, name_product = ?, description_product = ?, value_product = ?, image = ? WHERE product_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getType(), product.getName(), product.getDescription(), product.getValue(), product.getImage(), product.getProductId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteProduct(Product product) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getProductId());
        return rowsAffected > 0;
    }

    @Override
    public Product getProduct(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper, productId);
    }
}
