package com.example.session3.modals.services;

import com.example.session3.modals.entities.StudentEnrollment;
import com.example.session3.modals.repositories.CourseRepository;
import com.example.session3.modals.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentEnrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public StudentEnrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy lượt đăng ký với ID: " + id));
    }

    public StudentEnrollment createEnrollment(StudentEnrollment enrollment) {
        if (enrollment.getCourse() == null
                || enrollment.getCourse().getId() == null
                || courseRepository.findById(enrollment.getCourse().getId()).isEmpty()) {
            throw new IllegalArgumentException("Khóa học không tồn tại hoặc dữ liệu thiếu");
        }
        return enrollmentRepository.save(enrollment);
    }

    public StudentEnrollment updateEnrollment(Long id, StudentEnrollment enrollment) {
        if (enrollment.getCourse() != null
                && enrollment.getCourse().getId() != null
                && courseRepository.findById(enrollment.getCourse().getId()).isEmpty()) {
            throw new IllegalArgumentException("Khóa học không tồn tại");
        }
        StudentEnrollment existing = getEnrollmentById(id);
        existing.setStudent(enrollment.getStudent());
        existing.setCourse(enrollment.getCourse());
        return enrollmentRepository.save(existing);
    }

    public StudentEnrollment deleteEnrollmentById(Long id) {
        StudentEnrollment existing = getEnrollmentById(id);
        enrollmentRepository.delete(existing);
        return existing;
    }
}
