package com.minsait.Subject.services;

import com.minsait.Subject.models.entities.Subject;
import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.respositories.SubjectRepository;
import com.minsait.Subject.util.Types;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Test
    void testFindAll() {
        when(subjectRepository.findAll()).thenReturn(List.of(new Subject()));

        List<Subject> subjects = subjectService.findAll();

        assertEquals(1, subjects.size());
    }

    @Test
    void testFindById() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(new Subject()));

        Subject subject = subjectService.findById(1L);

        assertNotNull(subject);
    }

    @Test
    void testSave() {
        Subject subject = new Subject(1L, "John", new SubjectType(1L, Types.COMMON_CORE, 20));
        when(subjectRepository.save(subject)).thenReturn(subject);

        subjectService.save(subject);

        assertNotNull(subject);
    }

    @Test
    void testUpdate() {
        Subject subject = new Subject(1L, "John", new SubjectType(1L, Types.COMMON_CORE, 20));
        when(subjectRepository.save(subject)).thenReturn(subject);

        subjectService.update(subject);

        assertNotNull(subject);
    }

    @Test
    void testDelete() {
        Subject subject = new Subject(1L, "John", new SubjectType(1L, Types.COMMON_CORE, 20));
        doNothing().when(subjectRepository).deleteById(1L);

        subjectService.delete(1L);

        assertNotNull(subject);
    }
}