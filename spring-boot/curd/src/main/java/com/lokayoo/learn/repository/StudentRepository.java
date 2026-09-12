package com.lokayoo.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lokayoo.learn.entity.Student;

/**
 * Repository interface for performing database operations on Student entities.
 *
 * <p>JpaRepository provides standard operations such as:</p>
 * <ul>
 *     <li>findAll()</li>
 *     <li>findById()</li>
 *     <li>save()</li>
 *     <li>deleteById()</li>
 *     <li>existsById()</li>
 * </ul>
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom student database queries can be added here when required.
}
