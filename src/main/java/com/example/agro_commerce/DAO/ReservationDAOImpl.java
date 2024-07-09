package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Reservation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDAOImpl implements  ReservationDAO{
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public ReservationDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservation (buyer_id, seller_id, product_id, reservation_in, total_price) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getBuyerId());
            statement.setInt(2, reservation.getSellerId());
            statement.setInt(3, reservation.getProductId());
            statement.setDate(4, Date.valueOf(reservation.getReservationIn()));
            statement.setBigDecimal(5, reservation.getTotalPrice());

            rowInserted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowInserted;
    }

    public List<Reservation> listAllReservations() throws SQLException {
        List<Reservation> listReservation = new ArrayList<>();
        String sql = "SELECT * FROM reservation";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                int buyerId = resultSet.getInt("buyer_id");
                int sellerId = resultSet.getInt("seller_id");
                int productId = resultSet.getInt("product_id");
                Date reservationIn = resultSet.getDate("reservation_in");
                BigDecimal totalPrice = resultSet.getBigDecimal("total_price");

                Reservation reservation = new Reservation();
                listReservation.add(reservation);
            }
        }

        disconnect();

        return listReservation;
    }

    public boolean deleteReservation(Reservation reservation) throws SQLException {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getReservationId());

            rowDeleted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowDeleted;
    }

    public boolean updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservation SET buyer_id = ?, seller_id = ?, product_id = ?, reservation_in = ?, total_price = ? WHERE reservation_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getBuyerId());
            statement.setInt(2, reservation.getSellerId());
            statement.setInt(3, reservation.getProductId());
            statement.setDate(4, Date.valueOf(reservation.getReservationIn()));
            statement.setBigDecimal(5, reservation.getTotalPrice());
            statement.setInt(6, reservation.getReservationId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowUpdated;
    }

    public Reservation getReservation(int reservationId) throws SQLException {
        Reservation reservation = null;
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int buyerId = resultSet.getInt("buyer_id");
                    int sellerId = resultSet.getInt("seller_id");
                    int productId = resultSet.getInt("product_id");
                    Date reservationIn = resultSet.getDate("reservation_in");
                    BigDecimal totalPrice = resultSet.getBigDecimal("total_price");

                    reservation = new Reservation(reservationId, buyerId,sellerId,productId,totalPrice, reservationIn.toLocalDate());
                }
            }
        }

        disconnect();

        return reservation;
    }
}
