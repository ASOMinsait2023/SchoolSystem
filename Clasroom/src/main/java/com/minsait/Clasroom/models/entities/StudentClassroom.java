package com.minsait.Clasroom.models.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class StudentClassroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "students_id", unique = true)
    private Long studentId;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, updatable = false)
    private Classroom classroom;

}
