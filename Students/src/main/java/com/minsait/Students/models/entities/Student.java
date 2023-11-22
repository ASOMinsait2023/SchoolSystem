package com.minsait.Students.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private int age;
    @Email
    private String email;
    @ManyToOne
    @JoinColumn(name = "career_id")
    private Career career;
    @Column(name = "actual_credits")
    private int actualCredits = 0;
}
