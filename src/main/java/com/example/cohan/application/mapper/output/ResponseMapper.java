package com.example.cohan.application.mapper.output;

import com.example.cohan.domain.http.output.StudentResponse;
import com.example.cohan.domain.http.output.TeacherResponse;
import com.example.cohan.domain.http.output.PersonSucessResponse;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;

public class ResponseMapper {

    public static PersonSucessResponse toPersonResponse(
            Long id,
            String success,
            String message
    ) {
        return PersonSucessResponse.builder()
                .id(id)
                .state(success)
                .message(message)
                .build();
    }

    public static TeacherResponse toResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .dni(teacher.getDni())
                .name(teacher.getName())
                .phoneNumber(teacher.getPhoneNumber())
                .email(teacher.getEmail())
                .salary(teacher.getSalary())
                .build();
    }

    public static StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .dni(student.getDni())
                .name(student.getName())
                .phoneNumber(student.getPhoneNumber())
                .email(student.getEmail())
                .number(student.getNumber())
                .averageMark(student.getAverageMark())
                .build();
    }
}
