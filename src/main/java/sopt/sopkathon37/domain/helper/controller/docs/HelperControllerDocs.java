package sopt.sopkathon37.domain.helper.controller.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import sopt.sopkathon37.domain.helper.controller.dto.response.HelperDetailResponse;
import sopt.sopkathon37.domain.helper.controller.dto.response.HelperListElementResponse;
import sopt.sopkathon37.global.response.BaseResponse;

@Tag(name = "Helper 정보 조회")
public interface HelperControllerDocs {
	@Operation(summary = "Helper의 목록을 조회합니다.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "성공"
			)
	})
	@GetMapping
	ResponseEntity<BaseResponse<List<HelperListElementResponse>>> getHelperList();

	@Operation(summary = "Helper의 목록을 조회합니다.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "성공"
			)
	})
	@GetMapping(path = "/{helperId}")
	ResponseEntity<BaseResponse<HelperDetailResponse>> getHelperDetail(long helperId);
}
