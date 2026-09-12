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

import com.lokayoo.learn.dto.AddStudentReqDto;
import com.lokayoo.learn.dto.StudentDto;
import com.lokayoo.learn.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller that exposes CRUD endpoints for students.
 */
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Returns all students.
     *
     * @return HTTP 200 with all students
     */
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    /**
     * Returns a student by its identifier.
     *
     * @param id student identifier
     * @return HTTP 200 with the requested student
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    /**
     * Creates a new student.
     *
     * @param request student data
     * @return HTTP 201 with the created student
     */
    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(
            @Valid @RequestBody AddStudentReqDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createNewStudent(request));
    }

    /**
     * Deletes a student by its identifier.
     *
     * @param id student identifier
     * @return HTTP 204 when deletion succeeds
     */
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Replaces all editable fields of an existing student.
     *
     * @param id student identifier
     * @param request replacement student data
     * @return HTTP 200 with the updated student
     */
    @PutMapping("/put/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody AddStudentReqDto request) {
        return ResponseEntity.ok(
                studentService.updateStudent(id, request));
    }

    /**
     * Updates only the fields supplied in the request body.
     *
     * @param id student identifier
     * @param updates fields to update
     * @return HTTP 200 with the updated student
     */
    @PatchMapping("/patch/{id}")
    public ResponseEntity<StudentDto> patchStudent(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(
                studentService.patchStudent(id, updates));
    }
}
