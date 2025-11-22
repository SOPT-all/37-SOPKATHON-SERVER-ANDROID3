package sopt.sopkathon37.domain.booking.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import sopt.sopkathon37.domain.booking.service.dto.BookingCommand;

public record BookingRequest(
    @Min(1)
    long helperId,
    @Min(1)
    long serviceOfferId,
    String specialRequests,
    @NotBlank
    String startTime,
    @NotBlank
    String endTime,
    @NotNull
    BigDecimal estimatedFee,
    @NotNull
    BigDecimal reservationFee,
    @NotNull
    BigDecimal totalEstimated,
    @NotBlank
    String visitDate,
    @NotBlank
    String locate
) {

    public BookingCommand toBookingCommand() {
        return BookingCommand.of(
            helperId,
            serviceOfferId,
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
