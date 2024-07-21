package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssessmentDAOImpl implements AssessmentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertAssessment(Assessment assessment) {
        String sql = "INSERT INTO assessment (buyer_id, seller_id, assessment, assessment_date) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, assessment.getBuyerId(), assessment.getSellerId(), assessment.getAssessment(), assessment.getAssessmentDate());
        return rowsAffected > 0;
    }

    @Override
    public List<Assessment> listAllAssessments() {
        String sql = "SELECT * FROM assessment";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Assessment.class));
    }

    @Override
    public boolean updateAssessment(Assessment assessment) {
        String sql = "UPDATE assessment SET buyer_id = ?, seller_id = ?, assessment = ?, assessment_date = ? WHERE assessment_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, assessment.getBuyerId(), assessment.getSellerId(), assessment.getAssessment(), assessment.getAssessmentDate(), assessment.getAssessmentId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteAssessment(Assessment assessment) {
        String sql = "DELETE FROM assessment WHERE assessment_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, assessment.getAssessmentId());
        return rowsAffected > 0;
    }

    @Override
    public Assessment getAssessment(int assessmentId) {
        String sql = "SELECT * FROM assessment WHERE assessment_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Assessment.class), assessmentId);
    }
}

