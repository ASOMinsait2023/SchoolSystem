package com.minsait.Students.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Students.Datos;
import com.minsait.Students.services.ICareerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CareerController.class)
class CareerControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ICareerService careerService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveCareer() throws Exception {
        String careerMapper = objectMapper.writeValueAsString(Datos.getCareer());
        mvc.perform(post("/api/v1/careers/save")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(careerMapper))
             .andExpect(jsonPath("$.status").value("Created"))
             .andExpect(jsonPath("$.message").value("Career created"))
             .andExpect(status().isCreated());
    }

    @Test
    void testFindById()  throws Exception {
        when(careerService.getById(1L)).thenReturn(Datos.getCareer());
        mvc.perform(get("/api/v1/careers/1")
                       .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value(Datos.getCareer().getName()))
            .andExpect(status().isOk());
    }
}