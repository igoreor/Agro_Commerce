package com.example.agro_commerce.DAO;


import com.example.agro_commerce.model.Seller;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SellerDAOImpl implements SellerDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public SellerDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean insertSeller(Seller seller) throws SQLException {
        String sql = "INSERT INTO seller (user_id) VALUES (?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, seller.getUserId());

            rowInserted = statement.executeUpdate() > 0;

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                seller.setSellerId(generatedKeys.getInt(1));
            }
        } finally {
            disconnect();
        }

        return rowInserted;
    }

    @Override
    public List<Seller> listAllSellers() throws SQLException {
        List<Seller> listSellers = new ArrayList<>();
        String sql = "SELECT * FROM seller";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int sellerId = resultSet.getInt("seller_id");
                int userId = resultSet.getInt("user_id");

                Seller seller = new Seller(sellerId, userId);
                listSellers.add(seller);
            }
        } finally {
            disconnect();
        }

        return listSellers;
    }

    @Override
    public boolean updateSeller(Seller seller) throws SQLException {
        String sql = "UPDATE seller SET user_id = ? WHERE seller_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, seller.getUserId());
            statement.setInt(2, seller.getSellerId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    @Override
    public boolean deleteSeller(Seller seller) throws SQLException {
        String sql = "DELETE FROM seller WHERE seller_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, seller.getSellerId());

            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    @Override
    public Seller getSeller(int sellerId) throws SQLException {
        Seller seller = null;
        String sql = "SELECT * FROM seller WHERE seller_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, sellerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");

                    seller = new Seller(sellerId, userId);
                }
            }
        } finally {
            disconnect();
        }

        return seller;
    }
}
