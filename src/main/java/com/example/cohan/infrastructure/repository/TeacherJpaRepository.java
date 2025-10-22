package com.example.cohan.infrastructure.repository;

import com.example.cohan.domain.school.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherJpaRepository extends JpaRepository<TeacherEntity, Long> {
    boolean existsByDni(String dni);
    TeacherEntity findByDni(String dni);
}
