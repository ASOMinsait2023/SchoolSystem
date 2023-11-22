package com.minsait.Teacher.confi;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SwaggerConfigTest {
    @InjectMocks
    private SwaggerConfig swaggerConfig;
    @Test
    void testSwaggerConfig(){
        OpenAPI openAPI=swaggerConfig.SpringOpenAPI();

        assertEquals("Course API", openAPI.getInfo().getTitle());
    }
}