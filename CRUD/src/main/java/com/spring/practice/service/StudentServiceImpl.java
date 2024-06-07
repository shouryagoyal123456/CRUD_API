package com.spring.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.practice.domain.Student;
import com.spring.practice.dto.StudentDto;
import com.spring.practice.exception.ResourceNotFoundException;
import com.spring.practice.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) throws ResourceNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
    }

    public Student createStudent(@Valid StudentDto studentDto) {
        Student student = new Student();
        student = new ObjectMapper().convertValue(studentDto, Student.class);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, StudentDto studentDto) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        Student changes = new ObjectMapper().convertValue(studentDto, Student.class);
        changes.setId(student.getId());
        return studentRepository.save(changes);
    }

    public Map<String, Boolean> deleteStudent(Long studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentRepository.delete(student);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
