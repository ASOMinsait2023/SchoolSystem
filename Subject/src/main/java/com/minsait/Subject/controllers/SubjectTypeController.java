package com.minsait.Subject.controllers;

import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.services.SubjectTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/subject_types")
public class SubjectTypeController {
    @Autowired
    private SubjectTypeService subjectTypeService;
    @GetMapping
    public ResponseEntity<?> getAllSubjectTypes() {
        List<SubjectType> subjectTypes = subjectTypeService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("subjectTypes", subjectTypes);
        response.put("count", subjectTypes.size());
        response.put("status", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all_pretty")
    public ResponseEntity<?> getAllSubjectTypesFormatted() {
        List<SubjectType> subjectTypes = subjectTypeService.findAll();
        List<Map<String, Object>> subjectTypesFormatted = subjectTypes.stream()
                .distinct()
                .map(subjectType -> {
            Map<String, Object> subjectTypeFormatted = new HashMap<>();
            subjectTypeFormatted.put("id", subjectType.getId());
            subjectTypeFormatted.put("subjectType", subjectType.getSubjectType());
            return subjectTypeFormatted;
        }).toList();
        Map<String, Object> response = new HashMap<>();
        response.put("subjectTypes", subjectTypesFormatted);
        response.put("count", subjectTypesFormatted.size());
        response.put("status", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectTypeById(@PathVariable Long id) {
        SubjectType subjectType = subjectTypeService.findById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("subjectType", subjectType);
        response.put("status", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> saveSubjectType(@RequestBody SubjectType subjectType) {
        subjectTypeService.save(subjectType);
        Map<String, Object> response = new HashMap<>();
        response.put("subjectType", subjectType);
        response.put("status", 200);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
