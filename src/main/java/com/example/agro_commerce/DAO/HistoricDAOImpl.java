package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Historic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoricDAOImpl implements HistoricDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertHistoric(Historic historic) {
        String sql = "INSERT INTO historic (reservation_id, user_id, seller_id, bought_in) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, historic.getReservation(), historic.getUserId(), historic.getSellerId(), historic.getBoughtIn());
        return rowsAffected > 0;
    }

    @Override
    public List<Historic> listAllHistorics() {
        String sql = "SELECT * FROM historic";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Historic.class));
    }

    @Override
    public boolean updateHistoric(Historic historic) {
        String sql = "UPDATE historic SET reservation_id = ?, user_id = ?, seller_id = ?, bought_in = ? WHERE historic_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, historic.getReservation(), historic.getUserId(), historic.getSellerId(), historic.getBoughtIn(), historic.getHistoricId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteHistoric(Historic historic) {
        String sql = "DELETE FROM historic WHERE historic_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, historic.getHistoricId());
        return rowsAffected > 0;
    }

    @Override
    public Historic getHistoric(int historicId) {
        String sql = "SELECT * FROM historic WHERE historic_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Historic.class), historicId);
    }
}
