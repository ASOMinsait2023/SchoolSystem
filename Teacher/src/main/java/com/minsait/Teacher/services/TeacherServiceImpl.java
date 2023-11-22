package com.minsait.Teacher.services;

import com.minsait.Teacher.models.entities.Degree;
import com.minsait.Teacher.models.entities.Teacher;
import com.minsait.Teacher.repositories.DegreeRepository;
import com.minsait.Teacher.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void create(Teacher teacher) {
        degreeRepository.findById(teacher.getDegree().getId()).orElseThrow();
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public Teacher update(Teacher teacher, Long id){
        Teacher teacherToBeUpdated=teacherRepository.findById(id).orElseThrow();
        Degree degree=degreeRepository.findById(teacher.getDegree().getId()).orElseThrow();
        teacherToBeUpdated.setFirstName(teacher.getFirstName());
        teacherToBeUpdated.setLastName(teacher.getLastName());
        teacherToBeUpdated.setAge(teacher.getAge());
        teacherToBeUpdated.setEmail(teacher.getEmail());
        teacherToBeUpdated.setDegree(degree);
        return teacherRepository.save(teacherToBeUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBySpeciality(String speciality) {
        List<Teacher> teachers=teacherRepository.findAll();
        return teachers.stream().filter(teacher -> teacher.getDegree() != null
                && speciality.equals(teacher.getDegree().getSpeciality())).count();
    }
}
