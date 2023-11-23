package com.minsait.Clasroom.controllers;

import com.minsait.Clasroom.models.entities.Classroom;
import com.minsait.Clasroom.services.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {

    @Autowired
    private IClassroomService classroomService;

    @PostMapping("/save")
    public ResponseEntity<?> createClassroom(@RequestBody Classroom classroom){
        classroomService.save(classroom);
        Map<String, Object> response = new HashMap();
        response.put("status", "created");
        response.put("message", "Student created");
        response.put("body", classroom);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/add-student", params = {"classroomId", "studentId"})
    public ResponseEntity<?> addStudent(@RequestParam Long classroomId,
                                        @RequestParam Long studentId){
        classroomService.addStudent(classroomId, studentId);
        Map<String, Object> response = new HashMap();
        response.put("status", "ok");
        String message = "Student with id: " + studentId + " added successfully";
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-students/{id}")
    public ResponseEntity<?> getStudentsInformation(@PathVariable("id") Long classroomId){
        Classroom classroom = classroomService.findById(classroomId);
        List<Long> students = classroomService.getStudentsIds(classroom);
        Map<String, Object> response = new HashMap();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Students information");
        response.put("body", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
