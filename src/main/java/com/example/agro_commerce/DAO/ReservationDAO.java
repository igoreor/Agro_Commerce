package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Reservation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO {

    boolean insertReservation(Reservation reservation) throws SQLException;

    List<Reservation> listAllReservations() throws SQLException;

    boolean deleteReservation(Reservation reservation) throws SQLException;

    boolean updateReservation(Reservation reservation) throws SQLException;

    Reservation getReservation(int reservationId) throws SQLException;
}
