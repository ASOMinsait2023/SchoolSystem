package com.minsait.Subject.models.entities;

import jakarta.persistence.*;
import lombok.*;
import com.minsait.Subject.util.Types;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subject_types")
@Entity
public class SubjectType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "subject_type_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    private Types subjectType;
    @Column(name = "credits_number")
    private Integer creditsNumber;
}