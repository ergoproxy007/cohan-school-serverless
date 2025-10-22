package com.example.cohan.infrastructure.repository;

import com.example.cohan.domain.school.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long>  {
}
