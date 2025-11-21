package sopt.sopkathon37.domain.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.sopkathon37.domain.example.repository.ExampleRepository;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;
}
