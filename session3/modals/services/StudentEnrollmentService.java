package com.example.session3.modals.services;

import com.example.session3.modals.entities.Course;
import com.example.session3.modals.entities.Student;
import com.example.session3.modals.entities.StudentEnrollment;
import com.example.session3.modals.repositories.CourseRepository;
import com.example.session3.modals.repositories.StudentEnrollmentRepository;
import com.example.session3.modals.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentEnrollmentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository enrollmentRepository;

    public StudentEnrollmentService(StudentRepository studentRepository,
                                    CourseRepository courseRepository,
                                    StudentEnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public StudentEnrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy sinh viên với ID: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khóa học với ID: " + courseId));

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }
}
