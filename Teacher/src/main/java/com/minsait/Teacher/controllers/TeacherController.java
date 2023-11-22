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
import java.util.List;

@RequestMapping("/api/v1/teacher")
@RestController
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all teachers")
    @ApiResponse(responseCode = "200", description = "OK")
    public List<Teacher> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get teacher by Id")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Teacher> findById(@PathVariable Long id){
        Teacher teacher=teacherService.findById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    @Operation(summary = "Create a new teacher", description="Create a new teacher if the degree ID exists")
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher){
        teacherService.create(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a teacher", description="Update a teacher; degree ID must exist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher, @PathVariable Long id){
        Teacher teacherUpdate=teacherService.update(teacher, id);
        return new ResponseEntity<>(teacherUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete teacher by Id")
    @ApiResponse(responseCode = "204", description = "NO_CONTENT")
    public ResponseEntity<?> delete(@PathVariable Long id){
        teacherService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/countBySpeciality/{speciality}")
    @Operation(summary = "Count teachers by speciality",
            description="Enter the specialization of the degree for which you want to count the teachers")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<HashMap<String, String>> countBySpeciality(@PathVariable String speciality){
        Long countTeacher=teacherService.countBySpeciality(speciality);
        HashMap<String, String> response= new HashMap<>();
        String message="The number of teachers in the " + speciality + " speciality is: " + countTeacher.toString();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}
