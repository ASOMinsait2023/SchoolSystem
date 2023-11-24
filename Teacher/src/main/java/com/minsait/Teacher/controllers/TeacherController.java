package com.minsait.Teacher.controllers;

import com.minsait.Teacher.models.entities.Teacher;
import com.minsait.Teacher.services.ITeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/teacher")
@RestController
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    @Operation(summary = "Get all teachers")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> findAll(){
        List<Teacher> teachers=teacherService.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Teachers found");
        response.put("body", teachers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get teacher by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> findById(@PathVariable Long id){
        Teacher teacher=teacherService.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Teacher found");
        response.put("body", teacher);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    @Operation(summary = "Create a new teacher", description="Create a new teacher if the degree ID exists")
    public ResponseEntity<?> create(@RequestBody Teacher teacher){
        teacherService.create(teacher);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Teacher created successfully");
        response.put("body", teacher);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a teacher", description="Update a teacher; degree ID must exist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> update(@RequestBody Teacher teacher, @PathVariable Long id){
        Teacher teacherUpdate=teacherService.update(teacher, id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Teacher updated successfully");
        response.put("body", teacherUpdate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete teacher by Id")
    @ApiResponse(responseCode = "204", description = "NO_CONTENT")
    public ResponseEntity<?> delete(@PathVariable Long id){
        teacherService.deleteById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.NO_CONTENT.value());
        response.put("message", "Teacher deleted");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/countBySpeciality/{speciality}")
    @Operation(summary = "Count teachers by speciality",
            description="Enter the specialization of the degree for which you want to count the teachers")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> countBySpeciality(@PathVariable String speciality){
        Long countTeacher=teacherService.countBySpeciality(speciality);
        HashMap<String, Object> response= new LinkedHashMap<>();
        String message="The number of teachers in the " + speciality + " speciality is: " + countTeacher.toString();
        response.put("status", HttpStatus.OK.value());
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
