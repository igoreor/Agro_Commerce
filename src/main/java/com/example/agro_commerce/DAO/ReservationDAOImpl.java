package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (buyer_id, seller_id, product_id, reservation_in, total_price) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, reservation.getBuyerId(), reservation.getSellerId(), reservation.getProductId(), reservation.getReservationIn(), reservation.getTotalPrice());
        return rowsAffected > 0;
    }

    @Override
    public List<Reservation> listAllReservations() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Reservation.class));
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        String sql = "UPDATE reservation SET buyer_id = ?, seller_id = ?, product_id = ?, reservation_in = ?, total_price = ? WHERE reservation_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, reservation.getBuyerId(), reservation.getSellerId(), reservation.getProductId(), reservation.getReservationIn(), reservation.getTotalPrice(), reservation.getReservationId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteReservation(Reservation reservation) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, reservation.getReservationId());
        return rowsAffected > 0;
    }

    @Override
    public Reservation getReservation(int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Reservation.class), reservationId);
    }
}
