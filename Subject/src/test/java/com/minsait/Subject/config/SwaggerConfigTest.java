package com.minsait.Subject.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SwaggerConfigTest {
    @InjectMocks
    private SwaggerConfig swaggerConfig;

    @Test
    void springOpenAPI() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();
        assertEquals("Subject API", openAPI.getInfo().getTitle());
    }
}