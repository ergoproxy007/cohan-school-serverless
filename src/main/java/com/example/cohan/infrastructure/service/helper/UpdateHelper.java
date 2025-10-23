package com.example.cohan.infrastructure.service.helper;

import com.example.cohan.domain.school.entity.StudentEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;

import java.util.Optional;

public class UpdateHelper {

    public static Optional<TeacherEntity> modifyTeacherData(Optional<TeacherEntity> entity,
                                         String dni, String phone, String email, Double salary) {
        if (entity.isPresent()) {
            var entityToUpdate = entity.get();
            entityToUpdate.setDni(dni != null ? dni : entityToUpdate.getDni());
            entityToUpdate.setPhoneNumber(phone != null ? phone : entityToUpdate.getPhoneNumber());
            entityToUpdate.setEmail(email != null ? email : entityToUpdate.getEmail());
            entityToUpdate.setSalary(salary != null ? salary : entityToUpdate.getSalary());
            return Optional.of(entityToUpdate);
        }
        return Optional.empty();
    }

    public static Optional<StudentEntity> modifyStudentData(Optional<StudentEntity> entity,
                                         String dni, String phone, String email,
                                         Long number, Double averageMark) {
        if (entity.isPresent()) {
            var entityToUpdate = entity.get();
            entityToUpdate.setDni(dni != null ? dni : entityToUpdate.getDni());
            entityToUpdate.setPhoneNumber(phone != null ? phone : entityToUpdate.getPhoneNumber());
            entityToUpdate.setEmail(email != null ? email : entityToUpdate.getEmail());
            entityToUpdate.setNumber(number != null ? number : entityToUpdate.getNumber());
            entityToUpdate.setAverageMark(averageMark != null ? averageMark : entityToUpdate.getAverageMark());
            return Optional.of(entityToUpdate);
        }
        return Optional.empty();
    }
}
