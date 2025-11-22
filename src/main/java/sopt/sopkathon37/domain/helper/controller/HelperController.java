package sopt.sopkathon37.domain.helper.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sopt.sopkathon37.domain.helper.controller.docs.HelperControllerDocs;
import sopt.sopkathon37.domain.helper.controller.dto.response.HelperDetailResponse;
import sopt.sopkathon37.domain.helper.controller.dto.response.HelperListElementResponse;
import sopt.sopkathon37.domain.helper.service.HelperService;
import sopt.sopkathon37.global.message.SuccessMessage;
import sopt.sopkathon37.global.response.ApiResponseUtil;
import sopt.sopkathon37.global.response.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/helpers")
public class HelperController implements HelperControllerDocs {

	private final HelperService helperService;

	@GetMapping
	public ResponseEntity<BaseResponse<List<HelperListElementResponse>>> getHelperList() {
		return ApiResponseUtil.success(SuccessMessage.SUCCESS,
				helperService.getAllHelpers().stream().map(HelperListElementResponse::from).toList());
	}

	@GetMapping(path = "/{helperId}")
	public ResponseEntity<BaseResponse<HelperDetailResponse>> getHelperDetail(
			@PathVariable(name = "helperId") final long helperId) {
		return ApiResponseUtil.success(SuccessMessage.SUCCESS,
				HelperDetailResponse.from(helperService.getHelperById(helperId)));
	}
}
