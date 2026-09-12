package com.lokayoo.learn.service;

import java.util.List;
import java.util.Map;

import com.lokayoo.learn.dto.AddStudentReqDto;
import com.lokayoo.learn.dto.StudentDto;

/**
 * Defines business operations for student management.
 */
public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentReqDto request);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentReqDto request);

    StudentDto patchStudent(Long id, Map<String, Object> updates);
}
