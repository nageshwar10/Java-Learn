package com.lokayoo.learn.dto;

import lombok.Data;

/**
 * Data Transfer Object used to return student information
 * from the service layer to API clients.
 */
@Data
public class StudentDto {

    private Long id;
    private String name;
    private int age;
    private String email;

    /**
     * Creates a student DTO with the supplied values.
     *
     * @param id student identifier
     * @param name student name
     * @param age student age
     * @param email student email address
     */
    public StudentDto(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    /**
     * Creates an empty student DTO.
     */
    public StudentDto() {
    }

    /**
     * Returns the student identifier.
     *
     * @return student identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Updates the student identifier.
     *
     * @param id student identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the student name.
     *
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the student name.
     *
     * @param name student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the student age.
     *
     * @return student age
     */
    public int getAge() {
        return age;
    }

    /**
     * Updates the student age.
     *
     * @param age student age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the student email address.
     *
     * @return student email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the student email address.
     *
     * @param email student email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
