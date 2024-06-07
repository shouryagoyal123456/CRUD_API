package com.spring.practice.controller;

import com.spring.practice.domain.Student;
import com.spring.practice.dto.StudentDto;
import com.spring.practice.exception.ResourceNotFoundException;
import com.spring.practice.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/student")
    public List<Student> getAllData() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Object> getData(@PathVariable(value = "id") Long studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            return ResponseEntity.ok().body(student);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body("Resource not found: " + studentId);
        }
    }

    @PostMapping("/new/student")
    public Student newStudent(@Valid @RequestBody StudentDto student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update/student/{id}")
    public ResponseEntity<Object> updateCred(@PathVariable(value = "id") Long studentId, @RequestBody StudentDto students) {
        try {
            Student updatedStudent = studentService.updateStudent(studentId, students);
            return ResponseEntity.ok(updatedStudent);
        } catch (ResourceNotFoundException e) {
            System.out.println("Student not found: " + studentId);
            return ResponseEntity.badRequest().body("Resource not found: " + studentId);
        }
    }

    @DeleteMapping("/delete/student/{id}")
    public Map<String, Boolean> deleteCred(@PathVariable(value = "id") Long studentId) {
        try {
            return studentService.deleteStudent(studentId);
        } catch (ResourceNotFoundException e) {
            Map<String, Boolean> response = new HashMap<>();
            response.put("Student Identified", Boolean.FALSE);
            return response;
        }
    }
}
