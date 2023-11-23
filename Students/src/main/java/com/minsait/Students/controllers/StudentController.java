package com.minsait.Students.controllers;

import com.minsait.Students.models.entities.Student;
import com.minsait.Students.services.ICareerService;
import com.minsait.Students.services.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student){
        careerService.getById(student.getCareer().getId());
        studentService.save(student);
        Map<String, Object> response = new HashMap<>();
        response.put("Status", "Created");
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
        return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Map<String, Object> response = new HashMap<>();
        if (studentService.delete(id)){
            response.put("Status", HttpStatus.OK);
            String message = "Student with id: " + id + " deleted";
            response.put("Message", message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("Status", HttpStatus.NOT_FOUND);
            response.put("Message", "Student not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/add-to-career", params = {"studentId", "careerName"})
    public ResponseEntity<?> addStudentToCareer(@RequestParam Long studentId,
                                                @RequestParam String careerName){
        studentService.addStudentToCareer(studentId, careerName);
        String message = "Student with id: " + studentId + "added to career: " + careerName;
        Map<String, Object> response = new HashMap<>();
        response.put("Status", "OK");
        response.put("Message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/check-progress", params = {"studentId", "careerName"})
    public ResponseEntity<?> checkStudentProgress(@RequestParam Long studentId,
                                                @RequestParam String careerName){
        String message = studentService.checkStudentProgress(studentId, careerName);
        Map<String, Object> response = new HashMap<>();
        response.put("Status", "OK");
        response.put("Message", message);
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
