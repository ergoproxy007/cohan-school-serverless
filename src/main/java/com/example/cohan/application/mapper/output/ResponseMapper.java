package com.example.cohan.application.mapper.output;

import com.example.cohan.domain.http.output.PersonResponse;

public class ResponseMapper {

    public static PersonResponse toPersonResponse(
            Long id,
            String success,
            String message
    ) {
        return PersonResponse.builder()
                .id(id)
                .state(success)
                .message(message)
                .build();
    }
}
