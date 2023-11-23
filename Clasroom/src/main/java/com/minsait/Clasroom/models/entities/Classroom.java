package com.minsait.Clasroom.models.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "teacher_id")
    private Long teacherId;
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private Set<StudentClassroom> students = new HashSet<>();

}
