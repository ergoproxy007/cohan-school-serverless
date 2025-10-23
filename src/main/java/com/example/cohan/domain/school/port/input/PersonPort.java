package com.example.cohan.domain.school.port.input;

import com.example.cohan.domain.school.entity.PersonEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;

import java.util.Optional;

public interface PersonPort {
    Boolean existsByDni(String dni, PersonType type);
    Optional<Student> getStudent(String dni);
    Optional<Teacher> getTeacher(String dni);
    Optional<Long> save(Person person);
    void updateTeacher(Long id, String dni, String phone, String email, Double salary);
    void updateStudent(Long id, String dni, String phone, String email, Long number, Double averageMark);
}
