package sopt.sopkathon37.domain.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.helper.repository.HelperRepository;
import sopt.sopkathon37.global.exception.ServerException;
import sopt.sopkathon37.global.message.ErrorMessage;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HelperService {

	private final HelperRepository helperRepository;

	public List<Helper> getAllHelpers() {
		return helperRepository.findAll();
	}

	public Helper getHelperById(final long id) {
		return helperRepository.findByIdWithOffers(id)
				.orElseThrow(() -> new ServerException(ErrorMessage.NOT_FOUND));
	}
}
