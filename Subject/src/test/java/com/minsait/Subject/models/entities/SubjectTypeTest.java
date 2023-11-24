package com.minsait.Subject.models.entities;

import com.minsait.Subject.util.Types;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubjectTypeTest {
    @InjectMocks
    private SubjectType subjectType;

    @Test
    void testAllArgsConstructor() {
        SubjectType actualAllArgsConstructorResult = new SubjectType(123L, Types.SPECIALIZATION, 1);
        assertEquals(123L, actualAllArgsConstructorResult.getId().longValue());
        assertEquals(1, actualAllArgsConstructorResult.getCreditsNumber().intValue());
        assertEquals(Types.SPECIALIZATION, actualAllArgsConstructorResult.getSubjectType());
    }

    @Test
    void testBuilder() {
        SubjectType actualBuilderResult = SubjectType.builder().id(123L).subjectType(Types.SPECIALIZATION).creditsNumber(1).build();
        assertEquals(123L, actualBuilderResult.getId().longValue());
        assertEquals(1, actualBuilderResult.getCreditsNumber().intValue());
        assertEquals(Types.SPECIALIZATION, actualBuilderResult.getSubjectType());
        assertEquals(Types.SPECIALIZATION.getType(), actualBuilderResult.getSubjectType().getType());
    }
}