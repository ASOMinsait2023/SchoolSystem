package com.minsait.Classroom.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-teacher", url = "localhost:8083/api/v1/teacher")
public interface TeacherClient {
    @GetMapping("/list/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id);
}
