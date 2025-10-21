package com.example.cohan.application.mapper.output;

import com.example.cohan.domain.http.output.PersonResponse;

public class ResponseMapper {

    public static PersonResponse toPersonResponse(
            String state,
            String message
    ) {
        return PersonResponse.builder()
                .state(state)
                .message(message)
                .build();
    }
}
