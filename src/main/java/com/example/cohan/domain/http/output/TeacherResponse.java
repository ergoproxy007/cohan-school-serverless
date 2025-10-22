package com.example.cohan.domain.http.output;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherResponse {
    private Long id;
    private String dni;
    private String name;
    private String phoneNumber;
    private String email;
    private Double salary;
}
