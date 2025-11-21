package sopt.sopkathon37.domain.example.controller.dto.response;

import sopt.sopkathon37.domain.example.repository.Example;

public record ExampleResponse(
    long id,
    String name
) {
    public static ExampleResponse from(Example example) {
        return new ExampleResponse(
            example.getId(),
            example.getName()
        );
    }
}
