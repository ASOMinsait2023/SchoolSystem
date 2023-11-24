package com.minsait.Subject.services;

import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.respositories.SubjectTypeRepository;
import com.minsait.Subject.util.Types;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectTypeImplTest {
    @Mock
    private SubjectTypeRepository subjectTypeRepository;
    @InjectMocks
    private SubjectTypeImpl subjectTypeImpl;

    @Test
    void testFindAll() {
        when(subjectTypeRepository.findAll()).thenReturn(List.of(new SubjectType()));

        List<SubjectType> subjectTypes = subjectTypeImpl.findAll();

        assertEquals(1, subjectTypes.size());
    }

    @Test
    void testFindById() {
        when(subjectTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(new SubjectType()));

        SubjectType subjectType = subjectTypeImpl.findById(1L);

        assertNotNull(subjectType);
    }

    @Test
    void testSave() {
        SubjectType subjectType = new SubjectType(1L, Types.ELECTIVE, 1);
        when(subjectTypeRepository.save(subjectType)).thenReturn(subjectType);

        subjectTypeImpl.save(subjectType);

        assertNotNull(subjectType);
    }

}