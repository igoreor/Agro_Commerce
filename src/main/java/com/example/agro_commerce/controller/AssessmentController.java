package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.AssessmentDAO;
import com.example.agro_commerce.model.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    private final AssessmentDAO assessmentDAO;

    @Autowired
    public AssessmentController(AssessmentDAO assessmentDAO) {
        this.assessmentDAO = assessmentDAO;
    }

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment) throws SQLException {
        if (assessmentDAO.insertAssessment(assessment)) {
            return ResponseEntity.ok(assessment);
        }
        return ResponseEntity.status(500).build();
    }

    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() throws SQLException {
        List<Assessment> assessments = assessmentDAO.listAllAssessments();
        if (assessments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getAssessmentById(@PathVariable int id) throws SQLException {
        Assessment assessment = assessmentDAO.getAssessment(id);
        if (assessment != null) {
            return ResponseEntity.ok(assessment);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assessment> updateAssessment(@PathVariable int id, @RequestBody Assessment assessment) throws SQLException {
        assessment.setAssessmentId(id);
        if (assessmentDAO.updateAssessment(assessment)) {
            return ResponseEntity.ok(assessment);
        }
        return ResponseEntity.status(500).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable int id) throws SQLException {
        Assessment assessment = new Assessment();
        assessment.setAssessmentId(id);
        if (assessmentDAO.deleteAssessment(assessment)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
