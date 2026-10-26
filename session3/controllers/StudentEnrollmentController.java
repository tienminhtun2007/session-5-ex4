package com.example.session3.controllers;

import com.example.session3.dto.ApiResponse;
import com.example.session3.dto.StudentEnrollmentRequest;
import com.example.session3.modals.services.StudentEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {
    private final StudentEnrollmentService enrollmentService;

    public StudentEnrollmentController(StudentEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enrollStudent(@RequestBody StudentEnrollmentRequest request) {
        try {
            enrollmentService.enrollStudent(request.getStudentId(), request.getCourseId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Đăng ký khóa học thành công", null));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }
}
