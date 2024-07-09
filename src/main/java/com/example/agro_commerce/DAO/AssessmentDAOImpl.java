package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Assessment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssessmentDAOImpl implements AssessmentDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public AssessmentDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    @Override
    public boolean insertAssessment(Assessment assessment) throws SQLException {
        String sql = "INSERT INTO assessment (user_id, seller_id, assessment, assessment_date) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, assessment.getBuyerId());
            statement.setInt(2, assessment.getSellerId());
            statement.setDouble(3, assessment.getAssessment());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(assessment.getAssessmentDate().atStartOfDay()));

            rowInserted = statement.executeUpdate() > 0;

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                assessment.setAssessmentId(generatedKeys.getInt(1));
            }
        } finally {
            disconnect();
        }

        return rowInserted;
    }

    @Override
    public List<Assessment> listAllAssessments() throws SQLException {
        List<Assessment> listAssessments = new ArrayList<>();
        String sql = "SELECT * FROM assessment";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int assessmentId = resultSet.getInt("assessment_id");
                int userId = resultSet.getInt("user_id");
                int sellerId = resultSet.getInt("seller_id");
                double assessmentValue = resultSet.getDouble("assessment");
                java.sql.Timestamp assessmentDate = resultSet.getTimestamp("assessment_date");

                Assessment assessment = new Assessment();
                assessment.setAssessmentId(assessmentId);
                assessment.setBuyerId(userId);
                assessment.setSellerId(sellerId);
                assessment.setAssessment(assessmentValue);
                assessment.setAssessmentDate(assessmentDate.toLocalDateTime().toLocalDate());

                listAssessments.add(assessment);
            }
        } finally {
            disconnect();
        }

        return listAssessments;
    }

    @Override
    public boolean updateAssessment(Assessment assessment) throws SQLException {
        String sql = "UPDATE assessment SET user_id = ?, seller_id = ?, assessment = ?, assessment_date = ? WHERE assessment_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, assessment.getBuyerId());
            statement.setInt(2, assessment.getSellerId());
            statement.setDouble(3, assessment.getAssessment());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(assessment.getAssessmentDate().atStartOfDay()));
            statement.setInt(5, assessment.getAssessmentId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    @Override
    public boolean deleteAssessment(Assessment assessment) throws SQLException {
        String sql = "DELETE FROM assessment WHERE assessment_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, assessment.getAssessmentId());

            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    @Override
    public Assessment getAssessment(int assessmentId) throws SQLException {
        Assessment assessment = null;
        String sql = "SELECT * FROM assessment WHERE assessment_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, assessmentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    int sellerId = resultSet.getInt("seller_id");
                    double assessmentValue = resultSet.getDouble("assessment");
                    java.sql.Timestamp assessmentDate = resultSet.getTimestamp("assessment_date");

                    assessment = new Assessment();
                    assessment.setAssessmentId(assessmentId);
                    assessment.setBuyerId(userId);
                    assessment.setSellerId(sellerId);
                    assessment.setAssessment(assessmentValue);
                    assessment.setAssessmentDate(assessmentDate.toLocalDateTime().toLocalDate());
                }
            }
        } finally {
            disconnect();
        }

        return assessment;
    }
}
