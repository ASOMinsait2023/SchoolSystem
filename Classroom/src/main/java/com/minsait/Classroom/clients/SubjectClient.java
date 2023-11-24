package com.minsait.Classroom.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-subject", url = "localhost:8082/api/v1/subjects")
public interface SubjectClient {
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id);
}
