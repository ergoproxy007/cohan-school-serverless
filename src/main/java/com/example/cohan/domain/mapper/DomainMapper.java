package com.example.cohan.domain.mapper;

import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.school.entity.PersonEntity;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;

public class DomainMapper {

    public static Teacher toTeacher(PersonRequest request) {
        var teacher = Teacher.builder()
                .salary(request.getSalary())
                .build();
        completePerson(teacher, request);
        return teacher;
    }

    public static Student toStudent(PersonRequest request) {
        var student = Student.builder()
                .number(request.getNumber())
                .averageMark(request.getAverageMark())
                .build();
        completePerson(student, request);
        return student;
    }

    private static void completePerson(Person person, PersonRequest request) {
        person.setDni(request.getDni());
        person.setName(request.getName());
        person.setPhoneNumber(request.getPhoneNumber());
        person.setEmail(request.getEmail());
    }
}
