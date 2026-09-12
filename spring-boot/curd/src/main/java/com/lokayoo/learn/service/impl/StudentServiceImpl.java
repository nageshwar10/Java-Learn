package com.lokayoo.learn.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lokayoo.learn.dto.AddStudentReqDto;
import com.lokayoo.learn.dto.StudentDto;
import com.lokayoo.learn.entity.Student;
import com.lokayoo.learn.repository.StudentRepository;
import com.lokayoo.learn.service.StudentService;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of the student business service.
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    /**
     * Retrieves all students and converts entities to DTOs.
     *
     * @return list of student DTOs
     */
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    /**
     * Retrieves one student by identifier.
     *
     * @param id student identifier
     * @return matching student DTO
     * @throws IllegalArgumentException if the student does not exist
     */
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = findStudentById(id);
        return modelMapper.map(student, StudentDto.class);
    }

    /**
     * Creates and persists a new student.
     *
     * @param request student creation data
     * @return created student DTO
     */
    @Override
    public StudentDto createNewStudent(AddStudentReqDto request) {
        Student student = modelMapper.map(request, Student.class);
        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentDto.class);
    }

    /**
     * Deletes a student by identifier.
     *
     * @param id student identifier
     * @throws IllegalArgumentException if the student does not exist
     */
    @Override
    public void deleteStudentById(Long id) {
        findStudentById(id);
        studentRepository.deleteById(id);
    }

    /**
     * Replaces all editable fields of an existing student.
     *
     * @param id student identifier
     * @param request replacement student data
     * @return updated student DTO
     */
    @Override
    public StudentDto updateStudent(
            Long id,
            AddStudentReqDto request) {

        Student student = findStudentById(id);

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    /**
     * Updates only the fields supplied by the client.
     *
     * @param id student identifier
     * @param updates fields to update
     * @return updated student DTO
     */
    @Override
    public StudentDto patchStudent(
            Long id,
            Map<String, Object> updates) {

        Student student = findStudentById(id);

        updates.forEach((field, value) -> {
            switch (field) {
                case "name" -> student.setName((String) value);
                case "age" -> student.setAge(((Number) value).intValue());
                case "email" -> student.setEmail((String) value);
                default -> throw new IllegalArgumentException(
                        "Invalid student field: " + field);
            }
        });

        Student updatedStudent = studentRepository.save(student);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    /**
     * Finds a student or throws an exception when it does not exist.
     *
     * @param id student identifier
     * @return existing student entity
     */
    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Student not found with id: " + id));
    }
}
