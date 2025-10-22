package com.example.cohan.domain.usecase;

import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonSucessResponse;
import com.example.cohan.domain.http.output.StudentResponse;
import com.example.cohan.domain.http.output.TeacherResponse;

public interface PersonCommandUseCase {
    PersonSucessResponse create(PersonRequest request);
    StudentResponse findStudent(String dni);
    TeacherResponse findTeacher(String dni);
}
