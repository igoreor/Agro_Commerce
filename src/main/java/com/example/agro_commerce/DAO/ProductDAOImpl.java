package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public ProductDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("DRIVE_CLASS_NAME");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    @Override
    public boolean insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (type_product, name_product, value_product, description_product) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, product.getType());
            statement.setString(2, product.getName());
            statement.setBigDecimal(3, product.getValue());
            statement.setString(4, product.getDescription());

            rowInserted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowInserted;
    }

    @Override
    public List<Product> listAllProducts() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        String sql = "SELECT * FROM product";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String typeProduct = resultSet.getString("type_product");
                String nameProduct = resultSet.getString("name_product");
                BigDecimal valueProduct = resultSet.getBigDecimal("value_product");
                String descriptionProduct = resultSet.getString("description_product");

                Product product = new Product(productId, typeProduct, nameProduct, valueProduct, descriptionProduct);
                listProduct.add(product);
            }
        } finally {
            disconnect();
        }

        return listProduct;
    }

    @Override
    public boolean deleteProduct(Product product) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());

            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET type_product = ?, name_product = ?, value_product = ?, description_product = ? WHERE product_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, product.getType());
            statement.setString(2, product.getName());
            statement.setBigDecimal(3, product.getValue());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getProductId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    @Override
    public Product getProduct(int productId) throws SQLException {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String typeProduct = resultSet.getString("type_product");
                    String nameProduct = resultSet.getString("name_product");
                    BigDecimal valueProduct = resultSet.getBigDecimal("value_product");
                    String descriptionProduct = resultSet.getString("description_product");

                    product = new Product(productId, typeProduct, nameProduct, valueProduct, descriptionProduct);
                }
            }
        } finally {
            disconnect();
        }

        return product;
    }
}
