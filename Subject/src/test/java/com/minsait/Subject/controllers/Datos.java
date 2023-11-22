package com.minsait.Subject.controllers;

import com.minsait.Subject.models.entities.Subject;
import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.util.Types;

import java.util.List;
import java.util.Optional;

public class Datos {
    public static Optional<Subject> crearSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Matematicas");
        subject.setSubjectType(new SubjectType(1L, Types.COMMON_CORE, 10));
        return Optional.of(subject);
    }

    public static Subject crearSubject2() {
        Subject subject = new Subject();
        subject.setId(2L);
        subject.setName("Matematicas");
        subject.setSubjectType(new SubjectType(1L, Types.COMMON_CORE, 10));
        return subject;
    }

    public static Optional<SubjectType> crearSubjectType() {
        SubjectType subjectType = new SubjectType();
        subjectType.setId(1L);
        subjectType.setSubjectType(Types.COMMON_CORE);
        subjectType.setCreditsNumber(10);
        return Optional.of(subjectType);
    }
}
