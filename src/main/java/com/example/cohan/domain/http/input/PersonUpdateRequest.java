package com.example.cohan.domain.http.input;

import com.example.cohan.domain.school.enums.PersonType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateRequest {
    private Long id;
    private String dni;
    private String phoneNumber;
    private String email;
    private PersonType type;
    private Double salary;
    private Double averageMark;
}
