package com.example.cohan.infrastructure.service.school;

import static java.util.Optional.of;
import static java.util.Optional.empty;

import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.mapper.EntityMapper;
import com.example.cohan.domain.school.entity.StudentEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;
import com.example.cohan.domain.school.enums.CodeErrorEnum;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;
import com.example.cohan.domain.school.port.input.PersonPort;
import com.example.cohan.infrastructure.repository.StudentJpaRepository;
import com.example.cohan.infrastructure.repository.TeacherJpaRepository;
import java.util.Optional;

import com.example.cohan.infrastructure.service.helper.UpdateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonPort {

    private final TeacherJpaRepository teacherRepository;
    private final StudentJpaRepository studentRepository;

    @Autowired
    public PersonService(
            TeacherJpaRepository teacherRepository,
            StudentJpaRepository studentRepository
    ) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Boolean existsByDni(String dni, PersonType type) {
        return switch (type) {
            case STUDENT -> studentRepository.existsByDni(dni);
            case TEACHER -> teacherRepository.existsByDni(dni);
        };
    }

    @Override
    public Optional<Teacher> getTeacher(String dni) {
        var entity = teacherRepository.findByDni(dni);
        return entity != null ? of(EntityMapper.toDomain(entity)) : empty();
    }

    @Override
    public Optional<Student> getStudent(String dni) {
        var entity = studentRepository.findByDni(dni);
        return entity != null ? of(EntityMapper.toDomain(entity)) : empty();
    }

    @Override
    public Optional<Long> save(Person person) {
        if (person instanceof Teacher teacher) {
            return saveTeacher(teacher).map(TeacherEntity::getId);
        }
        if (person instanceof Student student) {
            return saveStudent(student).map(StudentEntity::getId);
        }
        return empty();
    }

    @Override
    public void updateTeacher(Long id, String dni, String phone, String email, Double salary) {
        Optional<TeacherEntity> entity = teacherRepository.findById(id);
        var teacher = UpdateHelper.modifyTeacherData(entity, dni, phone, email, salary);
        if (teacher.isPresent()) {
            teacherRepository.save(teacher.get());
        }
    }

    @Override
    public void updateStudent(Long id, String dni, String phone, String email, Double averageMark) {
        Optional<StudentEntity> entity = studentRepository.findById(id);
        var student = UpdateHelper.modifyStudentData(entity, dni, phone, email, averageMark);
        if (student.isPresent()) {
            studentRepository.save(student.get());
        }
    }

    private Optional<TeacherEntity> saveTeacher(Teacher teacher) {
        if (teacher.getSalary() == null) {
            throw new BusinessException.BadRequest(
                    CodeErrorEnum.PERSON_RULE_BAD_REQUEST,
                    "[PersonService] Salary is required"
            );
        }
        return of(teacherRepository.save(EntityMapper.toEntity(teacher)));
    }

    private Optional<StudentEntity> saveStudent(Student student) {
        return of(studentRepository.save(EntityMapper.toEntity(student)));
    }
}
