package com.example.cohan.domain.usecase;

import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;

public interface PersonCommandUseCase {
    PersonResponse create(PersonRequest request);
}
