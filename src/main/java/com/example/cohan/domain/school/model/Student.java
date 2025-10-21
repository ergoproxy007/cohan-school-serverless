package com.example.cohan.domain.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    private Long number;
    private Double averageMark;
}
