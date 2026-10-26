package com.example.session3.modals.repositories;

import com.example.session3.modals.entities.Instructors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructors, Long> {
}