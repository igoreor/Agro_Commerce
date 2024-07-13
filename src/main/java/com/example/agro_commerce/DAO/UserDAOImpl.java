package com.example.agro_commerce.DAO;


import com.example.agro_commerce.expecption.DAOException;
import com.example.agro_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            int userId = rs.getInt("user_id");
            String userName = rs.getString("user_name");
            String email = rs.getString("user_email");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
            return new User(userId, userName, email, password, sex, birthDate);
        }
    };

    @Override
    public boolean insertUser(User user) {
        String sql = "INSERT INTO userr (user_name, user_email, password, sex, birthDate) VALUES (?, ?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql,
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getSex(),
                    Date.valueOf(user.getBirthDate()));
            return result > 0;
        } catch (DataAccessException e) {
            logger.error("Error inserting user: SQL [{}] Params [{}, {}, {}, {}, {}]",
                    sql,
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getSex(),
                    Date.valueOf(user.getBirthDate()));
            throw new DAOException("Error inserting user", e);
        }
    }

    @Override
    public List<User> listAllUsers() {
        String sql = "SELECT * FROM userr";
        try {
            return jdbcTemplate.query(sql, userRowMapper);
        } catch (DataAccessException e) {
            logger.error("Error listing all users: {}", e.getMessage());
            throw new DAOException("Error listing all users", e);
        }
    }

    @Override
    public boolean deleteUser(User user) {
        String sql = "DELETE FROM userr WHERE user_id = ?";
        try {
            int result = jdbcTemplate.update(sql, user.getUserId());
            return result > 0;
        } catch (DataAccessException e) {
            logger.error("Error deleting user: {}", e.getMessage());
            throw new DAOException("Error deleting user", e);
        }
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE userr SET user_name = ?, user_email = ?, password = ?, sex = ?, birthDate = ? WHERE user_id = ?";
        try {
            int result = jdbcTemplate.update(sql,
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getSex(),
                    Date.valueOf(user.getBirthDate()),
                    user.getUserId());
            return result > 0;
        } catch (DataAccessException e) {
            logger.error("Error updating user: {}", e.getMessage());
            throw new DAOException("Error updating user", e);
        }
    }

    @Override
    public User getUser(int userId) {
        String sql = "SELECT * FROM userr WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
        } catch (DataAccessException e) {
            logger.error("Error getting user: {}", e.getMessage());
            throw new DAOException("Error getting user", e);
        }
    }
}
