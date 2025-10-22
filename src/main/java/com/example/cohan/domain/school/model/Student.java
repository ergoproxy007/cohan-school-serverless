package com.example.cohan.domain.school.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student extends Person {
    private Long id;
    private Long number;
    private Double averageMark;
}
