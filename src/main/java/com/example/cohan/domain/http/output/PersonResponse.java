package com.example.cohan.domain.http.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {
    private String state;
    private String message;
}
