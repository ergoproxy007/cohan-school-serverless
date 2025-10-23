package com.example.cohan.domain.http.output;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
