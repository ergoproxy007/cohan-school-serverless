package com.example.cohan.domain.mapper;

import com.example.cohan.domain.school.entity.StudentEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;

public class EntityMapper {

    public static TeacherEntity toEntity(Teacher teacher) {
        TeacherEntity entity = new TeacherEntity();
        entity.setDni(teacher.getDni());
        entity.setName(teacher.getName());
        entity.setPhoneNumber(teacher.getPhoneNumber());
        entity.setEmail(teacher.getEmail());
        entity.setSalary(teacher.getSalary());
        return entity;
    }

    public static StudentEntity toEntity(Student student) {
        StudentEntity entity = new StudentEntity();
        entity.setDni(student.getDni());
        entity.setName(student.getName());
        entity.setPhoneNumber(student.getPhoneNumber());
        entity.setEmail(student.getEmail());
        entity.setNumber(student.getNumber());
        entity.setAverageMark(student.getAverageMark());
        return entity;
    }
}
