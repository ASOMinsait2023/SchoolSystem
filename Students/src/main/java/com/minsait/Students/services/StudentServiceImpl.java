package com.minsait.Students.services;

import com.minsait.Students.models.entities.Career;
import com.minsait.Students.models.entities.Student;
import com.minsait.Students.repositories.CareerRepository;
import com.minsait.Students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CareerRepository careerRepository;


    @Transactional
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if(studentRepository.findById(id).isPresent()){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void addStudentToCareer(Long studentId, String careerName) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Career> career = careerRepository.findByName(careerName);
        if(student.isPresent() && career.isPresent()){
            student.get().setCareer(career.get());
            studentRepository.save(student.get());
        }else{
            throw new NoSuchElementException("one or both elements not found");
        }

    }

    @Override
    public String checkStudentProgress(Long studentId, String careerName) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Career> career = careerRepository.findByName(careerName);
        if(student.isPresent() && career.isPresent()){
            int careerCredits = career.get().getTotalCredits();
            int studentCredits = student.get().getActualCredits();
            double progressCredits = (double) (studentCredits * 100) / careerCredits;
            String progress = "The progress is: " + progressCredits + "%";
            return progress;
        }else{
            throw new NoSuchElementException("one or both elements not found");
        }
    }

}
