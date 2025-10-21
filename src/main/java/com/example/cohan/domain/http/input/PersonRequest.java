package com.example.cohan.domain.http.input;

import com.example.cohan.domain.school.enums.PersonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    @NotBlank(message = "dni is required")
    private String dni;
    @NotBlank(message = "name is required")
    private String name;
    private String phoneNumber;
    private String email;
    @NotNull(message = "type is required")
    private PersonType type;
}
