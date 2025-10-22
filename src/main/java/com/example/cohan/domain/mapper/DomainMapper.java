package com.example.cohan.domain.mapper;

import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;

public class DomainMapper {

    public static Teacher toTeacher(PersonRequest request) {
        Teacher teacher = Teacher.builder()
                .salary(request.getSalary())
                .build();
        teacher.setDni(request.getDni());
        teacher.setName(request.getName());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setEmail(request.getEmail());
        return teacher;
    }

    public static Student toStudent(PersonRequest request) {
        Student student = Student.builder()
                .number(request.getNumber())
                .averageMark(request.getAverageMark())
                .build();
        student.setDni(request.getDni());
        student.setName(request.getName());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setEmail(request.getEmail());
        return student;
    }
}
