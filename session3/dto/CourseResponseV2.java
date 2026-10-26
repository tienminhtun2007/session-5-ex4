package com.example.session3.dto;

import com.example.session3.modals.entities.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
