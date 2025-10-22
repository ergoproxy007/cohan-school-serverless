package com.example.cohan.domain.school.model;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public abstract class Person {
    private String dni;
    private String name;
    private String phoneNumber;
    private String email;
}
