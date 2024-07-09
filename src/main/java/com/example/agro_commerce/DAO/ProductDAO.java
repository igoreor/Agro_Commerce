package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    boolean insertProduct(Product product) throws SQLException;

    List<Product> listAllProducts() throws SQLException;

    boolean deleteProduct(Product product) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    Product getProduct(int productId) throws SQLException;
}
