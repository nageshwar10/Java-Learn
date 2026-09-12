package com.lokayoo.learn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentReqDto {

    @NotBlank (message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @NotBlank (message = "Age is mandatory")
    @Min(value = 0, message = "Age must be a positive number")
    private int age;

    @Email 
    @NotBlank (message = "Email is mandatory")
    private String email;
}
