package com.lokayoo.learn.service;

import java.util.List;
import java.util.Map;

import com.lokayoo.learn.dto.AddStudentReqDto;
import com.lokayoo.learn.dto.StudentDto;

public interface StudentService {

    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto createNewStudent(AddStudentReqDto addStudentReqDto);
    void deleteStudentById(Long id);
    StudentDto updateStudent(Long id, AddStudentReqDto addStudentReqDto);
    StudentDto patchStudent(Long id, Map<String, Object> updates);
    
}