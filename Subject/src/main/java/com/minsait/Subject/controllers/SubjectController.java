package com.minsait.Subject.controllers;

import com.minsait.Subject.models.entities.Subject;
import com.minsait.Subject.respositories.SubjectRepository;
import com.minsait.Subject.services.ISubjectService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subjects")
@Slf4j
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;
    @GetMapping
    public ResponseEntity<?> getAllSubjects() {
        List<Subject> subjects = subjectService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("subjects", subjects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all_pretty")
    public ResponseEntity<?> getAllSubjectsFormatted() {
        List<Subject> subjects = subjectService.findAll();
        List<Map<String, Object>> subjectsFormatted = subjects.stream().map(subject -> {
            Map<String, Object> subjectFormatted = new HashMap<>();
            subjectFormatted.put("id", subject.getId());
            subjectFormatted.put("name", subject.getName());
            subjectFormatted.put("subjectType", subject.getSubjectType().getId());
            return subjectFormatted;
        }).toList();
        Map<String, Object> response = new HashMap<>();
        response.put("subjects", subjectsFormatted);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.findById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("subject", subject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> saveSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Subject created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        Subject subjectToUpdate = subjectService.findById(id);
        subjectToUpdate.setName(subject.getName());
        subjectToUpdate.setSubjectType(subject.getSubjectType());
        subjectService.update(subjectToUpdate);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @ApiResponse(responseCode = "200", description = "Subject deleted successfully", useReturnTypeSchema = true)
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}