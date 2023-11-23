package com.minsait.Classroom.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-student", url = "localhost:8081/api/v1/students")
public interface StudentClient {
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id);
    @GetMapping("/get-required-students")
    public ResponseEntity<?> getRequiredStudents(@RequestParam List<Long> studentsIds);
}
