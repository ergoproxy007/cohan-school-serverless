package com.example.cohan.domain.school.port.input;

import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;

public interface PersonCommandUseCase {
    PersonResponse create(PersonRequest request);
}
