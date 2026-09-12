package com.lokayoo.learn.repository;

import com.lokayoo.learn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository 
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}