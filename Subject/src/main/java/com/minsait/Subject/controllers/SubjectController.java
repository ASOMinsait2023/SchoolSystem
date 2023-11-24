package com.minsait.Subject.controllers;

import com.minsait.Subject.models.entities.Subject;
import com.minsait.Subject.services.ISubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subjects")
@Slf4j
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;
    @GetMapping
    @Operation(summary = "Get all subjects")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> getAllSubjects() {
        List<Subject> subjects = subjectService.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subjects found");
        response.put("body", subjects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all_pretty")
    @Operation(summary = "Get all subjects formatted")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> getAllSubjectsFormatted() {
        List<Subject> subjects = subjectService.findAll();
        List<Map<String, Object>> subjectsFormatted = subjects.stream().map(subject -> {
            Map<String, Object> subjectFormatted = new LinkedHashMap<>();
            subjectFormatted.put("id", subject.getId());
            subjectFormatted.put("name", subject.getName());
            subjectFormatted.put("subjectType", subject.getSubjectType().getId());
            return subjectFormatted;
        }).toList();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subjects formatted");
        response.put("body", subjectsFormatted);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get subject by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subject found");
        response.put("body", subject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/create")
    @Operation(summary = "Create a new subject")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<?> saveSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Subject created successfully");
        response.put("body", subject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a subject")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        Subject subjectToUpdate = subjectService.findById(id);
        subjectToUpdate.setName(subject.getName());
        subjectToUpdate.setSubjectType(subject.getSubjectType());
        subjectService.update(subjectToUpdate);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Subject update successfully");
        response.put("body", subject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a subject")
    @ApiResponse(responseCode = "200", description = "Subject deleted successfully")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subject delete");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}