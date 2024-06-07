package com.spring.practice.service;

import com.spring.practice.domain.Student;
import com.spring.practice.dto.StudentDto;
import com.spring.practice.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public List<Student> getAllStudents();
    public Student getStudentById(Long studentId) throws ResourceNotFoundException;
    public Student createStudent(@Valid StudentDto studentDto);
    public Student updateStudent(Long studentId, StudentDto studentDto) throws ResourceNotFoundException;
    public Map<String, Boolean> deleteStudent(Long studentId) throws ResourceNotFoundException;

}
