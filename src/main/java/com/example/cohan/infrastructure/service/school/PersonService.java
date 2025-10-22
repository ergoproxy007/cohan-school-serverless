package com.example.cohan.infrastructure.service.school;

import com.example.cohan.domain.exceptions.ApiException;
import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.mapper.DomainMapper;
import com.example.cohan.domain.mapper.EntityMapper;
import com.example.cohan.domain.school.entity.PersonEntity;
import com.example.cohan.domain.school.entity.StudentEntity;
import com.example.cohan.domain.school.entity.TeacherEntity;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.model.Student;
import com.example.cohan.domain.school.model.Teacher;
import com.example.cohan.domain.school.port.input.PersonPort;
import com.example.cohan.infrastructure.repository.StudentJpaRepository;
import com.example.cohan.infrastructure.repository.TeacherJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void save(Person person) {
        Optional<PersonEntity> entitySaved = Optional.empty();
        if (person instanceof Teacher teacher) {
            entitySaved = saveTeacher(teacher);
        } if (person instanceof Student student) {
            entitySaved = saveStudent(student);
        }
        if (entitySaved.isEmpty()) {
            throw new IllegalArgumentException("Unsupported person type: " + person.getClass().getSimpleName());
        }
    }

    private Optional<PersonEntity> saveTeacher(Teacher teacher) {
        if (teacher.getSalary() == null) {
            throw new BusinessException.BadRequest(
                    "PERSON_RULE_001",
                    "TEACHER_SAVE_PERSON_SERVICE",
                    "[PersonService] Salary is required"
            );
        }
        return Optional.of(teacherRepository.save(EntityMapper.toEntity(teacher)));
    }

    private Optional<PersonEntity> saveStudent(Student student) {
        return Optional.of(studentRepository.save(EntityMapper.toEntity(student)));
    }
}
