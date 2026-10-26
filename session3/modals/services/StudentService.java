package com.example.session3.modals.services;

import com.example.session3.dto.StudentCreateRequest;
import com.example.session3.modals.entities.Student;
import com.example.session3.modals.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentCreateRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        return studentRepository.save(student);
    }
}
