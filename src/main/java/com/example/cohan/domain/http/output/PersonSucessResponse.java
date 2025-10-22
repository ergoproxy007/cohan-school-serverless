package com.example.cohan.domain.http.output;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonSucessResponse {
    private Long id;
    private String state;
    private String message;
}
