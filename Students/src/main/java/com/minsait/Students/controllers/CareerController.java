package com.minsait.Students.controllers;

import com.minsait.Students.models.entities.Career;
import com.minsait.Students.services.ICareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/careers")
public class CareerController {

    @Autowired
    private ICareerService careerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCareer(@PathVariable("id") Long id){
        return new ResponseEntity<>(careerService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Career career){
        careerService.save(career);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "Created");
        response.put("message", "career created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
