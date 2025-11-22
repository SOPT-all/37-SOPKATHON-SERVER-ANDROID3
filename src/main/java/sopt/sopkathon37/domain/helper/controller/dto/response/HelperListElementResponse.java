package sopt.sopkathon37.domain.helper.controller.dto.response;

import sopt.sopkathon37.domain.helper.repository.Helper;

public record HelperListElementResponse(
		long helperId,
		String imageURL,
		String name,
		String about,
		float rate,
		String distance,
		boolean verified
) {
	public static HelperListElementResponse from(Helper helper) {
		return new HelperListElementResponse(
				helper.getId(),
				helper.getImageURL(),
				helper.getName(),
				helper.getAbout(),
				helper.getRate(),
				helper.getDistance(),
				helper.isVerified()
		);
	}
}
