package com.example.session3.modals.repositories;

import com.example.session3.dto.CourseResponseV2;
import com.example.session3.modals.entities.Course;
import com.example.session3.modals.entities.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.status = :status")
    Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);

    @Query("""
            SELECT new com.example.session3.dto.CourseResponseV2(
                c.id,
                c.title,
                c.status
            )
            FROM Course c
            WHERE c.status = :status
            """)
    Page<CourseResponseV2> findAllByStatusV2(
            @Param("status") CourseStatus status,
            Pageable pageable);
}