package com.minsait.Students.controllers;

import com.minsait.Students.models.entities.Student;
import com.minsait.Students.services.ICareerService;
import com.minsait.Students.services.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private ICareerService careerService;

    @PostMapping("/save")
    @Operation(summary = "Create a new student",
            description="Create a new student if the career ID exists and email is valid")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student){
        careerService.getById(student.getCareer().getId());
        studentService.save(student);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Student created");
        response.put("body", student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Map<String, Object>> getAll(){
        List<Student> students = studentService.getAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Students found");
        response.put("body", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Student student=studentService.getById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Student found");
        response.put("body", student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @GetMapping("/list/{id}")
    @Operation(summary = "Delete student by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Map<String, Object> response = new LinkedHashMap<>();
        if (studentService.delete(id)){
            response.put("status", HttpStatus.OK.value());
            String message = "Student with id: " + id + " deleted";
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Student not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/add-to-career", params = {"studentId", "careerName"})
    @GetMapping("/list/{id}")
    @Operation(summary = "Add the student to a career",
            description = "Add a student by their ID and the name of the career")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> addStudentToCareer(@RequestParam Long studentId,
                                                @RequestParam String careerName){
        studentService.addStudentToCareer(studentId, careerName);
        String message = "Student with id: " + studentId + "added to career: " + careerName;
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "OK");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/check-progress", params = {"studentId", "careerName"})
    @Operation(summary = "Check the student's progress",
            description = "Check the student's progress by their ID and the name of the career")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    public ResponseEntity<?> checkStudentProgress(@RequestParam Long studentId,
                                                @RequestParam String careerName){
        String message = studentService.checkStudentProgress(studentId, careerName);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-required-students", params = {"studentsIds"} )
    public ResponseEntity<?> getRequiredStudents(@RequestParam List<Long> studentsIds){
        List<Student> students = studentService.getStudentsInformation(studentsIds);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "The following list are students found");
        response.put("body", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
