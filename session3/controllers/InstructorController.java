package com.example.session3.controllers;

import com.example.session3.dto.ApiResponse;
import com.example.session3.modals.entities.Instructors;
import com.example.session3.modals.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructors>>> getAllInstructors() {
        List<Instructors> instructors = instructorService.findAllInstructors();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách giảng viên thành công", instructors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructors>> getInstructorById(@PathVariable Long id) {
        try {
            Instructors instructor = instructorService.findInstructorById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin giảng viên thành công", instructor));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy giảng viên với ID: " + id, null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInstructor(@RequestBody Instructors instructor) {
        try {
            instructorService.createInstructor(instructor);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Tạo mới giảng viên thành công", null));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Thông tin giảng viên không hợp lệ", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructors>> updateInstructor(@PathVariable Long id, @RequestBody Instructors instructor) {
        try {
            Instructors updated = instructorService.updateInstructor(id, instructor);
            return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật giảng viên thành công", updated));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy giảng viên với ID: " + id, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructors>> deleteInstructorById(@PathVariable Long id) {
        try {
            Instructors deleted = instructorService.deleteInstructorById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Xóa giảng viên thành công", deleted));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy giảng viên với ID: " + id, null));
        }
    }
}