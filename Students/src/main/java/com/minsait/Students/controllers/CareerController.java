package com.minsait.Students.controllers;

import com.minsait.Students.models.entities.Career;
import com.minsait.Students.services.ICareerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/careers")
public class CareerController {

    @Autowired
    private ICareerService careerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get career by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> getCareer(@PathVariable("id") Long id){
        Career career = careerService.getById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Career found");
        response.put("body", career);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    @Operation(summary = "Create a new career",
            description="Create a new Career, the name must be unique")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "500", description = "ERROR")
    })
    public ResponseEntity<?> create(@RequestBody Career career){
        careerService.save(career);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Career created");
        response.put("body", career);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
