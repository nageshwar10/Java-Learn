package com.lokayoo.learn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Request payload used to create or replace a student.
 */
@Data
public class AddStudentReqDto {

    /**
     * Student's full name.
     */
    @NotBlank(message = "Name is mandatory")
    @Size(
        min = 2,
        max = 50,
        message = "Name must be between 2 and 50 characters"
    )
    private String name;

    /**
     * Student's age. Zero and positive values are accepted.
     */
    @NotNull(message = "Age is mandatory")
    @PositiveOrZero(message = "Age must be a positive number")
    private Integer age;

    /**
     * Student's email address.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;
}
