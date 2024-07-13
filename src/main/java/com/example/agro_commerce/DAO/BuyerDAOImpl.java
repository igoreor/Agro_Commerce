package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuyerDAOImpl implements BuyerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertBuyer(Buyer buyer) {
        String sql = "INSERT INTO buyer (user_id) VALUES (?)";
        int rowsAffected = jdbcTemplate.update(sql, buyer.getUserId());
        return rowsAffected > 0;
    }

    @Override
    public List<Buyer> listAllBuyers() {
        String sql = "SELECT * FROM buyer";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Buyer.class));
    }

    @Override
    public boolean updateBuyer(Buyer buyer) {
        String sql = "UPDATE buyer SET user_id = ? WHERE buyer_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, buyer.getUserId(), buyer.getBuyerId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteBuyer(Buyer buyer) {
        String sql = "DELETE FROM buyer WHERE buyer_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, buyer.getBuyerId());
        return rowsAffected > 0;
    }

    @Override
    public Buyer getBuyer(int buyerId) {
        String sql = "SELECT * FROM buyer WHERE buyer_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Buyer.class), buyerId);
    }
}
