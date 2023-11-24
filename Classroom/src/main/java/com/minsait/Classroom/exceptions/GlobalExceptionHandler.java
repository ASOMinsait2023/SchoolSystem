package com.minsait.Classroom.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> sqlExceptionHandler(SQLException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", exception.getErrorCode());
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> feignExceptionHandler(FeignException exception){
        String regex = "\\{.+\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exception.getMessage());
        String message = "";
        if(matcher.find()){
            message = matcher.group();
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(message);
        }catch (Exception e){
            node = null;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", exception.status());
        assert node != null;
        response.put("message", node.get("message"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
