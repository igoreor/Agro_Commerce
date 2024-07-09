package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
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
        String sql = "INSERT INTO userr (user_email, user_name, password, birthDate, sex) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, String.valueOf(user.getPassword())); // Use um método seguro para lidar com senhas
            statement.setDate(4, Date.valueOf(user.getBirthDate()));
            statement.setString(5, user.getSex());

            rowInserted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM userr";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userEmail = resultSet.getString("user_email");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                Date birthDate = resultSet.getDate("birthDate");
                String sex = resultSet.getString("sex");

                User user = new User(userId, userEmail, userName, password,  sex,birthDate.toLocalDate());
                listUser.add(user);
            }
        } finally {
            disconnect();
        }

        return listUser;
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM userr WHERE user_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserId());

            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE userr SET user_email = ?, user_name = ?, password = ?, birthDate = ?, sex = ? WHERE user_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, String.valueOf(user.getPassword()));
            statement.setDate(4, Date.valueOf(user.getBirthDate()));
            statement.setString(5, user.getSex());
            statement.setInt(6, user.getUserId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    public User getUser(int userId) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM userr WHERE user_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userEmail = resultSet.getString("user_email");
                    String userName = resultSet.getString("user_name");
                    String password = resultSet.getString("password");
                    Date birthDate = resultSet.getDate("birthDate");
                    String sex = resultSet.getString("sex");

                    new User(userId, userEmail, userName, password,  sex,birthDate.toLocalDate());
                }
            }
        } finally {
            disconnect();
        }

        return user;
    }
}
