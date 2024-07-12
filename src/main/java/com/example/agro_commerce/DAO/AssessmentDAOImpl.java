package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Assessment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AssessmentDAOImpl implements AssessmentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AssessmentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Assessment> assessmentRowMapper = new RowMapper<>() {
        @Override
        public Assessment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assessment assessment = new Assessment();
            assessment.setAssessmentId(rs.getInt("assessment_id"));
            assessment.setBuyerId(rs.getInt("user_id"));
            assessment.setSellerId(rs.getInt("seller_id"));
            assessment.setAssessment(rs.getDouble("assessment"));
            assessment.setAssessmentDate(rs.getTimestamp("assessment_date").toLocalDateTime().toLocalDate());
            return assessment;
        }
    };

    @Override
    public boolean insertAssessment(Assessment assessment) {
        String sql = "INSERT INTO assessment (user_id, seller_id, assessment, assessment_date) VALUES (?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql,
                    assessment.getBuyerId(),
                    assessment.getSellerId(),
                    assessment.getAssessment(),
                    java.sql.Timestamp.valueOf(assessment.getAssessmentDate().atStartOfDay()));
            return result > 0;
        } catch (DataAccessException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Assessment> listAllAssessments() {
        String sql = "SELECT * FROM assessment";
        try {
            return jdbcTemplate.query(sql, assessmentRowMapper);
        } catch (DataAccessException e) {

            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public boolean updateAssessment(Assessment assessment) {
        String sql = "UPDATE assessment SET user_id = ?, seller_id = ?, assessment = ?, assessment_date = ? WHERE assessment_id = ?";
        try {
            int result = jdbcTemplate.update(sql,
                    assessment.getBuyerId(),
                    assessment.getSellerId(),
                    assessment.getAssessment(),
                    java.sql.Timestamp.valueOf(assessment.getAssessmentDate().atStartOfDay()),
                    assessment.getAssessmentId());
            return result > 0;
        } catch (DataAccessException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAssessment(Assessment assessment) {
        String sql = "DELETE FROM assessment WHERE assessment_id = ?";
        try {
            int result = jdbcTemplate.update(sql, assessment.getAssessmentId());
            return result > 0;
        } catch (DataAccessException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Assessment getAssessment(int assessmentId) {
        String sql = "SELECT * FROM assessment WHERE assessment_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, assessmentRowMapper, assessmentId);
        } catch (DataAccessException e) {

            e.printStackTrace();
            return null;
        }
    }
}
