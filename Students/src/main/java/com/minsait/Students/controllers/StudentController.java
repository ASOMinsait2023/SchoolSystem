package com.minsait.Students.controllers;

import com.minsait.Students.models.entities.Student;
import com.minsait.Students.services.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping()
    public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student){
        studentService.save(student);
        Map<String, Object> response = new HashMap<>();
        response.put("Status", "OK");
        response.put("Message", "Student created");
        response.put("Student", student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.delete(id), HttpStatus.OK);
    }

    @PutMapping(value = "/add-to-course", params = {"studentId", "courseName"})
    public ResponseEntity<?> addCareerToStudent(@RequestParam Long studentId,
                                                @RequestParam String courseName){
        studentService.addCareerToStudent(studentId, courseName);
        String message = "Student with id: " + studentId + "added to career: " + courseName;
        Map<String, Object> response = new HashMap<>();
        response.put("Status", "OK");
        response.put("Message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
