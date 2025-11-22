package sopt.sopkathon37.domain.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.sopkathon37.domain.helper.controller.dto.response.HelperDetailResponse;
import sopt.sopkathon37.domain.helper.controller.dto.response.HelperListElementResponse;
import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.helper.repository.HelperRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HelperService {

	private final HelperRepository helperRepository;

	public List<HelperListElementResponse> getAllHelpers() {
		return helperRepository.findAll().stream()
				.map(HelperListElementResponse::from)
				.toList();
	}

	public HelperDetailResponse getHelperById(final long id) {
		Helper helper = helperRepository.findByIdWithOffers(id).orElseThrow(IllegalArgumentException::new);
		return HelperDetailResponse.from(helper);
	}
}
