package sopt.sopkathon37.domain.booking.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import sopt.sopkathon37.domain.booking.controller.dto.BookingRequest;
import sopt.sopkathon37.global.response.BaseResponse;

@Tag(name = "예약 등록")
public interface BookingControllerDocs {

    @Operation(summary = "예약 등록 API")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "성공"
        )
    })
    @PostMapping
    ResponseEntity<BaseResponse<Void>> booking(
        @Valid BookingRequest request
    );
}
