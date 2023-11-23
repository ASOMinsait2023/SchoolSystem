package com.minsait.Teacher.controllers;

import com.minsait.Teacher.models.entities.Degree;
import com.minsait.Teacher.services.IDegreeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/degree")
public class DegreeController {
    @Autowired
    private IDegreeService degreeService;

    @GetMapping("/list")
    @Operation(summary = "Get all degrees")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> findAll(){
        List<Degree> degrees=degreeService.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Degrees found");
        response.put("body", degrees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get degree by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> findById(@PathVariable Long id){
        Degree degree=degreeService.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Degree found");
        response.put("body", degree);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new Degree")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<?> create(@RequestBody Degree degree){
        degreeService.create(degree);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Degree created successfully");
        response.put("body", degree);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
