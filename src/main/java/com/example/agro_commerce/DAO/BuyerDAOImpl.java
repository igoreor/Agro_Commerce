package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuyerDAOImpl implements BuyerDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public BuyerDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean insertBuyer(Buyer buyer) throws SQLException {
        String sql = "INSERT INTO buyer (user_id) VALUES (?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, buyer.getUserId());

            rowInserted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowInserted;
    }

    @Override
    public List<Buyer> listAllBuyers() throws SQLException {
        List<Buyer> listBuyer = new ArrayList<>();
        String sql = "SELECT * FROM buyer";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int buyerId = resultSet.getInt("buyer_id");
                int userId = resultSet.getInt("user_id");

                Buyer buyer = new Buyer(buyerId, userId);
                listBuyer.add(buyer);
            }
        } finally {
            disconnect();
        }

        return listBuyer;
    }

    @Override
    public boolean deleteBuyer(Buyer buyer) throws SQLException {
        String sql = "DELETE FROM buyer WHERE buyer_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, buyer.getBuyerId());

            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    @Override
    public boolean updateBuyer(Buyer buyer) throws SQLException {
        String sql = "UPDATE buyer SET user_id = ? WHERE buyer_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, buyer.getUserId());
            statement.setInt(2, buyer.getBuyerId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    @Override
    public Buyer getBuyer(int buyerId) throws SQLException {
        Buyer buyer = null;
        String sql = "SELECT * FROM buyer WHERE buyer_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, buyerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");

                    buyer = new Buyer(buyerId, userId);
                }
            }
        } finally {
            disconnect();
        }

        return buyer;
    }
}
