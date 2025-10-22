package com.example.cohan.domain.mapper;

import com.example.cohan.domain.school.entity.PersonEntity;
import com.example.cohan.domain.school.entity.StudentEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;
import com.example.cohan.domain.school.model.Person;

public class EntityMapper {

    public static TeacherEntity toEntity(Teacher teacher) {
        var entity = new TeacherEntity();
        entity.setDni(teacher.getDni());
        entity.setName(teacher.getName());
        entity.setPhoneNumber(teacher.getPhoneNumber());
        entity.setEmail(teacher.getEmail());
        entity.setSalary(teacher.getSalary());
        return entity;
    }

    public static StudentEntity toEntity(Student student) {
        var entity = new StudentEntity();
        entity.setDni(student.getDni());
        entity.setName(student.getName());
        entity.setPhoneNumber(student.getPhoneNumber());
        entity.setEmail(student.getEmail());
        entity.setNumber(student.getNumber());
        entity.setAverageMark(student.getAverageMark());
        return entity;
    }

    public static Student toDomain(StudentEntity entity) {
        var student = Student.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .averageMark(entity.getAverageMark())
                .build();
        completePerson(student, entity);
        return student;
    }

    public static Teacher toDomain(TeacherEntity entity) {
        var teacher = Teacher.builder()
                .id(entity.getId())
                .salary(entity.getSalary())
                .build();
        completePerson(teacher, entity);
        return teacher;
    }

    private static void completePerson(Person person, PersonEntity entity) {
        person.setDni(entity.getDni());
        person.setName(entity.getName());
        person.setPhoneNumber(entity.getPhoneNumber());
        person.setEmail(entity.getEmail());
    }
}
