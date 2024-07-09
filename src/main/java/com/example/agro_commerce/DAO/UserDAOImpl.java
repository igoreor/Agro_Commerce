package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository

public class UserDAOImpl {

    @Value("${jdbc.url}")
    private String jdbcURL;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    private Connection jdbcConnection;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        connect();
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
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

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_name, email, password, sex, birth_date) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSex());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));

            rowInserted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String sex = resultSet.getString("sex");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();

                User user = new User(userId, userName, email, password, sex, birthDate);
                listUser.add(user);
            }
        }

        disconnect();

        return listUser;
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserId());

            rowDeleted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET user_name = ?, email = ?, password = ?, sex = ?, birth_date = ? WHERE user_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSex());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setInt(6, user.getUserId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowUpdated;
    }

    public User getUser(int userId) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userName = resultSet.getString("user_name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String sex = resultSet.getString("sex");
                    LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();

                    user = new User(userId, userName, email, password, sex, birthDate);
                }
            }
        }

        disconnect();

        return user;
    }
}
