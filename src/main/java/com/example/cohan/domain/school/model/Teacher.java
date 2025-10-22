package com.example.cohan.domain.school.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Teacher extends Person {
    private Double salary;
}
