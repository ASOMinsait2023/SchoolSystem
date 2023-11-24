package com.minsait.Students.repositories;

import com.minsait.Students.models.entities.Career;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CareerRepositoryTest {

    @Autowired
    private CareerRepository careerRepository;

    @Test
    void testFindByName(){
        //given
        String name = "Contaduria";
        //when
        Optional<Career> career = careerRepository.findByName(name);
        //then
        assertTrue(career.isPresent());
    }

}