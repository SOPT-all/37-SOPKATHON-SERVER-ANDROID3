package sopt.sopkathon37.domain.booking.service.dto;

import java.math.BigDecimal;
import lombok.Builder;
import sopt.sopkathon37.domain.booking.repository.Booking;
import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.serviceoffer.repository.ServiceOffer;

@Builder
public record BookingCommand(
    long helperId,
    long serviceOfferId,
    String specialRequests,
    String startTime,
    String endTime,
    BigDecimal estimatedFee,
    BigDecimal reservationFee,
    BigDecimal totalEstimated,
    String visitDate,
    String locate
) {

    public static BookingCommand of(
        long helperId,
        long serviceOfferId,
        String specialRequests,
        String startTime,
        String endTime,
        BigDecimal estimatedFee,
        BigDecimal reservationFee,
        BigDecimal totalEstimated,
        String visitDate,
        String locate
    ) {
        return BookingCommand.builder()
            .helperId(helperId)
            .serviceOfferId(serviceOfferId)
            .specialRequests(specialRequests)
            .startTime(startTime)
            .endTime(endTime)
            .estimatedFee(estimatedFee)
            .reservationFee(reservationFee)
            .totalEstimated(totalEstimated)
            .visitDate(visitDate)
            .locate(locate)
            .build();
    }

    public Booking toBooking(Helper helper, ServiceOffer serviceOffer) {
        return Booking.create(
            serviceOffer,
            helper,
            specialRequests,
            startTime,
            endTime,
            estimatedFee,
            reservationFee,
            totalEstimated,
            visitDate,
            locate
        );
    }
}
