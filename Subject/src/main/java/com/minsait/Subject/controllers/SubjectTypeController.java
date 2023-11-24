package com.minsait.Subject.controllers;

import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.services.SubjectTypeService;
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
@Slf4j
@RequestMapping("/api/v1/subject_types")
public class SubjectTypeController {
    @Autowired
    private SubjectTypeService subjectTypeService;
    @GetMapping
    @Operation(summary = "Get all subject types")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> getAllSubjectTypes() {
        List<SubjectType> subjectTypes = subjectTypeService.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subject types found");
        response.put("body", subjectTypes);
        response.put("count", subjectTypes.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all_pretty")
    @Operation(summary = "Get all subject types formatted")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> getAllSubjectTypesFormatted() {
        List<SubjectType> subjectTypes = subjectTypeService.findAll();
        List<Map<String, Object>> subjectTypesFormatted = subjectTypes.stream()
                .distinct()
                .map(subjectType -> {
            Map<String, Object> subjectTypeFormatted = new LinkedHashMap<>();
            subjectTypeFormatted.put("id", subjectType.getId());
            subjectTypeFormatted.put("subjectType", subjectType.getSubjectType());
            return subjectTypeFormatted;
        }).toList();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subject types formatted");
        response.put("body", subjectTypesFormatted);
        response.put("count", subjectTypesFormatted.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get subject type by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> getSubjectTypeById(@PathVariable Long id) {
        SubjectType subjectType = subjectTypeService.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Subject type found");
        response.put("body", subjectType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/create")
    @Operation(summary = "Create a new subject type")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<?> saveSubjectType(@RequestBody SubjectType subjectType) {
        subjectTypeService.save(subjectType);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Subject type created successfully");
        response.put("body", subjectType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
