package sopt.sopkathon37.domain.helper.controller.dto.response;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.helperserviceoffer.repository.HelperServiceOffer;

public record HelperDetailResponse(
		long helperId,
		String imageURL,
		String name,
		String location,
		float rate,
		boolean verified,
		List<ServiceOfferInfo> serviceOffers,
		String about,
		String experience,
		List<AvailabilityInfo> availabilities,
		List<String> languages,
		BigDecimal hourlyRate

) {
	public static HelperDetailResponse from(Helper helper) {
		return new HelperDetailResponse(
				helper.getId(),
				helper.getImageURL(),
				helper.getName(),
				helper.getLocation(),
				helper.getRate(),
				helper.isVerified(),
				helper.getOffers().stream().map(ServiceOfferInfo::from).toList(),
				helper.getAbout(),
				helper.getExperience(),
				Arrays.stream(helper.getAvailability().split("\\|")).map(AvailabilityInfo::from).toList(),
				Arrays.stream(helper.getLanguage().split("\\|")).toList(),
				helper.getHourlyRate()
		);
	}

	private record ServiceOfferInfo(
			String name,
			String takenTime
	){
		private static ServiceOfferInfo from(HelperServiceOffer helperServiceOffer) {
			return new ServiceOfferInfo(
					helperServiceOffer.getServiceOffer().getName(),
					helperServiceOffer.getTimeTaken()
			);
		}
	}

	private record AvailabilityInfo(String availableDayOfTheWeek, String availableTime) {
		private static AvailabilityInfo from(String availability) {
			String[] info = availability.split(",");
			return new AvailabilityInfo(info[0], info[1]);
		}
	}
}
