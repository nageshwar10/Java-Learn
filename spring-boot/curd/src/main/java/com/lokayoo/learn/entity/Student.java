package com.lokayoo.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a student record in the database.
 *
 * <p>Hibernate maps this class to a database table named {@code student}.</p>
 */
@Entity
@Getter
@Setter
public class Student {

    /**
     * Unique identifier for the student.
     *
     * <p>The database generates this value automatically.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Student's full name.
     */
    private String name;

    /**
     * Student's age.
     */
    private Integer age;

    /**
     * Student's email address.
     */
    private String email;
}
