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

import java.util.List;

@RestController
@RequestMapping("/api/v1/degree")
public class DegreeController {
    @Autowired
    private IDegreeService degreeService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all degrees")
    @ApiResponse(responseCode = "200", description = "OK")
    public List<Degree> findAll(){
        return degreeService.findAll();
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get degree by Id")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Degree> findById(@PathVariable Long id){
        Degree degree=degreeService.findById(id);
        return ResponseEntity.ok(degree);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new Degree")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Degree> create(@RequestBody Degree degree){
        degreeService.create(degree);
        return new ResponseEntity<>(degree, HttpStatus.CREATED);
    }
}
