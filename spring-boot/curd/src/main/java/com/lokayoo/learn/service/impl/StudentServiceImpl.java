package com.lokayoo.learn.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.lokayoo.learn.dto.AddStudentReqDto;
import com.lokayoo.learn.dto.StudentDto;
import com.lokayoo.learn.entity.Student;
import com.lokayoo.learn.repository.StudentRepository;
import com.lokayoo.learn.service.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {

        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Student not found with id: " + id
                        )
                );

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentReqDto addStudentReqDto) {

        Student student = modelMapper.map(addStudentReqDto, Student.class);

        student = studentRepository.save(student);

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Student not found with id: " + id
            );
        }

        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(
            Long id,
            AddStudentReqDto addStudentReqDto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Student not found with id: " + id
                        )
                );

        existingStudent.setName(addStudentReqDto.getName());
        existingStudent.setAge(addStudentReqDto.getAge());
        existingStudent.setEmail(addStudentReqDto.getEmail());

        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Override
    public StudentDto patchStudent(
            Long id,
            Map<String, Object> updates) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Student not found with id: " + id
                        )
                );

        updates.forEach((field, value) -> {

            switch (field) {
                case "name" -> existingStudent.setName((String) value);

                case "age" -> existingStudent.setAge((Integer) value);

                case "email" -> existingStudent.setEmail((String) value);

                default -> throw new IllegalArgumentException(
                        "Invalid field: " + field
                );
            }
        });

        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }
}