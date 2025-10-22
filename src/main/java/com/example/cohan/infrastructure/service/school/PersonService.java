package com.example.cohan.infrastructure.service.school;

import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.mapper.DomainMapper;
import com.example.cohan.domain.mapper.EntityMapper;
import com.example.cohan.domain.school.entity.PersonEntity;
import com.example.cohan.domain.school.entity.StudentEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;
import com.example.cohan.domain.school.port.input.PersonPort;
import com.example.cohan.infrastructure.repository.StudentJpaRepository;
import com.example.cohan.infrastructure.repository.TeacherJpaRepository;
import java.util.Optional;
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
    public Optional<Long> save(Person person) {
        if (person instanceof Teacher teacher) {
            return saveTeacher(teacher).map(TeacherEntity::getId);
        }
        if (person instanceof Student student) {
            return saveStudent(student).map(StudentEntity::getId);
        }
        return Optional.empty();
    }

    @Override
    public Boolean existsByDni(String dni, PersonType type) {
        return switch (type) {
            case STUDENT -> studentRepository.existsByDni(dni);
            case TEACHER -> teacherRepository.existsByDni(dni);
        };
    }

    private Optional<TeacherEntity> saveTeacher(Teacher teacher) {
        if (teacher.getSalary() == null) {
            throw new BusinessException.BadRequest(
                    "PERSON_RULE_001",
                    "TEACHER_SAVE_PERSON_SERVICE",
                    "[PersonService] Salary is required"
            );
        }
        return Optional.of(teacherRepository.save(EntityMapper.toEntity(teacher)));
    }

    private Optional<StudentEntity> saveStudent(Student student) {
        return Optional.of(studentRepository.save(EntityMapper.toEntity(student)));
    }
}
