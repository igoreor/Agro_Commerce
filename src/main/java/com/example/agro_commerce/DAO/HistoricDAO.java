package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Historic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoricDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public HistoricDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertHistoric(Historic historic) throws SQLException {
        String sql = "INSERT INTO historic (reservation_id, user_id, seller_id, bought_in) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, historic.getHistoricId());
            statement.setInt(2, historic.getUserId());
            statement.setInt(3, historic.getSellerId());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(String.valueOf(historic.getBoughtIn())));

            rowInserted = statement.executeUpdate() > 0;

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                historic.setHistoricId(generatedKeys.getInt(1));
            }
        }

        disconnect();

        return rowInserted;
    }

    public List<Historic> listAllHistorics() throws SQLException {
        List<Historic> listHistorics = new ArrayList<>();
        String sql = "SELECT * FROM historic";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int historicId = resultSet.getInt("historic_id");
                int reservationId = resultSet.getInt("reservation_id");
                int userId = resultSet.getInt("user_id");
                int sellerId = resultSet.getInt("seller_id");
                java.sql.Timestamp boughtIn = resultSet.getTimestamp("bought_in");
                Historic historic = new Historic();
                listHistorics.add(historic);
            }
        }

        disconnect();

        return listHistorics;
    }

    public boolean updateHistoric(Historic historic) throws SQLException {
        String sql = "UPDATE historic SET reservation_id = ?, user_id = ?, seller_id = ?, bought_in = ? WHERE historic_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, historic.getReservation());
            statement.setInt(2, historic.getUserId());
            statement.setInt(3, historic.getSellerId());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(historic.getBoughtIn().atStartOfDay()));
            statement.setInt(5, historic.getHistoricId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowUpdated;
    }

    public boolean deleteHistoric(Historic historic) throws SQLException {
        String sql = "DELETE FROM historic WHERE historic_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, historic.getHistoricId());

            rowDeleted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowDeleted;
    }

    public Historic getHistoric(int historicId) throws SQLException {
        Historic historic = null;
        String sql = "SELECT * FROM historic WHERE historic_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, historicId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int reservationId = resultSet.getInt("reservation_id");
                    int userId = resultSet.getInt("user_id");
                    int sellerId = resultSet.getInt("seller_id");
                    java.sql.Timestamp boughtIn = resultSet.getTimestamp("bought_in");

                    historic = new Historic();
                }
            }
        }

        disconnect();

        return historic;
    }
}
