package com.example.session3.modals.services;

import com.example.session3.modals.entities.Instructors;
import com.example.session3.modals.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructors> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructors findInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giảng viên với ID: " + id));
    }

    public Instructors createInstructor(Instructors req) {
        Instructors instructor = new  Instructors();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return instructorRepository.save(instructor);
    }

    public Instructors updateInstructor(Long id, Instructors instructor) {
        Instructors existing = findInstructorById(id);
        existing.setName(instructor.getName());
        existing.setEmail(instructor.getEmail());
        return instructorRepository.save(existing);
    }

    public Instructors deleteInstructorById(Long id) {
        Instructors existing = findInstructorById(id);
        instructorRepository.delete(existing);
        return existing;
    }
}
