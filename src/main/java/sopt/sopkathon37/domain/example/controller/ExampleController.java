package sopt.sopkathon37.domain.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.sopkathon37.domain.example.controller.docs.ExampleControllerDocs;
import sopt.sopkathon37.domain.example.controller.dto.response.ExampleResponse;
import sopt.sopkathon37.domain.example.repository.Example;
import sopt.sopkathon37.domain.example.service.ExampleService;
import sopt.sopkathon37.global.message.SuccessMessage;
import sopt.sopkathon37.global.response.ApiResponseUtil;
import sopt.sopkathon37.global.response.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/examples")
public class ExampleController implements ExampleControllerDocs {

    private final ExampleService exampleService;

    @GetMapping("/{exampleId}")
    public ResponseEntity<BaseResponse<ExampleResponse>> getExample(
        @PathVariable Long exampleId
    ) {
        return ApiResponseUtil.success(SuccessMessage.SUCCESS,
            ExampleResponse.from(Example.create("example")));
    }
}
