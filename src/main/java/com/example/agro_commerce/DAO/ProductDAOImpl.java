package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO product (type_product, name_product, description_product) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, product.getType(), product.getName(), product.getDescription());
        return rowsAffected > 0;
    }

    @Override
    public List<Product> listAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET type_product = ?, name_product = ?, description_product = ? WHERE product_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getType(), product.getName(), product.getDescription(), product.getProductId());
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
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Product.class), productId);
    }
}
