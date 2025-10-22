package com.example.cohan.domain.http.output;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponse {
    private Long id;
    private String dni;
    private String name;
    private String phoneNumber;
    private String email;
    private Long number;
    private Double averageMark;
}
