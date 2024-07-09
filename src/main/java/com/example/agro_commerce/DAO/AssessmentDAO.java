package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Assessment;

import java.sql.SQLException;
import java.util.List;

public interface AssessmentDAO {
    boolean insertAssessment(Assessment assessment) throws SQLException;
    List<Assessment> listAllAssessments() throws SQLException;
    boolean updateAssessment(Assessment assessment) throws SQLException;
    boolean deleteAssessment(Assessment assessment) throws SQLException;
    Assessment getAssessment(int assessmentId) throws SQLException;
}
