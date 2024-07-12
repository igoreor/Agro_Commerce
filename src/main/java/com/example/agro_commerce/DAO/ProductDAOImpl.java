package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO product (type_product, name_product, value_product, description_product) VALUES (?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, product.getType(), product.getName(), product.getValue(), product.getDescription());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> listAllProducts() {
        String sql = "SELECT * FROM product";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET type_product = ?, name_product = ?, value_product = ?, description_product = ? WHERE product_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, product.getType(), product.getName(), product.getValue(), product.getDescription(), product.getProductId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, product.getProductId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProduct(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Product.class), productId);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
