package com.lokayoo.learn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import com.lokayoo.learn.dto.AddStudentReqDto;
import com.lokayoo.learn.dto.StudentDto;
import com.lokayoo.learn.service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(
            @RequestBody @Valid AddStudentReqDto addStudentReqDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createNewStudent(addStudentReqDto));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudentById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @RequestBody AddStudentReqDto addStudentReqDto) {

        return ResponseEntity
                .ok(studentService.updateStudent(id, addStudentReqDto));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<StudentDto> patchStudent(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity
                .ok(studentService.patchStudent(id, updates));
    }
}