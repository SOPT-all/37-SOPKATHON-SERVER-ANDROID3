package sopt.sopkathon37.domain.booking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.sopkathon37.domain.booking.controller.dto.BookingRequest;
import sopt.sopkathon37.domain.booking.service.BookingService;
import sopt.sopkathon37.global.message.SuccessMessage;
import sopt.sopkathon37.global.response.ApiResponseUtil;
import sopt.sopkathon37.global.response.BaseResponse;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> booking(
        @Valid BookingRequest request
    ) {
        bookingService.booking(request.toBookingCommand());
        return ApiResponseUtil.success(SuccessMessage.CREATED);
    }
}
