package sopt.sopkathon37.domain.example.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sopt.sopkathon37.domain.example.controller.dto.response.ExampleResponse;
import sopt.sopkathon37.global.response.BaseResponse;

@Tag(name = "예시 스웨거")
public interface ExampleControllerDocs {

    @Operation(summary = "예시 엔드포인트입니다.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "성공"
        )
    })
    @GetMapping("/{exampleId}")
    ResponseEntity<BaseResponse<ExampleResponse>> getExample(
        @PathVariable Long exampleId
    );
}
