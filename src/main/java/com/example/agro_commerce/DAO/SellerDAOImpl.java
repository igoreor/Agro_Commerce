package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerDAOImpl implements SellerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertSeller(Seller seller) {
        String sql = "INSERT INTO seller (user_id) VALUES (?)";
        int rowsAffected = jdbcTemplate.update(sql, seller.getUserId());
        return rowsAffected > 0;
    }

    @Override
    public List<Seller> listAllSellers() {
        String sql = "SELECT * FROM seller";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Seller.class));
    }

    @Override
    public boolean updateSeller(Seller seller) {
        String sql = "UPDATE seller SET user_id = ? WHERE seller_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, seller.getUserId(), seller.getSellerId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteSeller(Seller seller) {
        String sql = "DELETE FROM seller WHERE seller_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, seller.getSellerId());
        return rowsAffected > 0;
    }

    @Override
    public Seller getSeller(int sellerId) {
        String sql = "SELECT * FROM seller WHERE seller_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Seller.class), sellerId);
    }
}
