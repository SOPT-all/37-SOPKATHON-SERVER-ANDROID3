package sopt.sopkathon37.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import sopt.sopkathon37.global.message.ErrorMessage;
import sopt.sopkathon37.global.message.SuccessMessage;

@Builder
public record BaseResponse<T>(
    int status,
    String message,
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    T data
) {
    public static BaseResponse<Void> from(SuccessMessage successMessage) {
        return BaseResponse.<Void>builder()
            .status(successMessage.getHttpStatus().value())
            .message(successMessage.getMessage())
            .build();
    }

    public static <T> BaseResponse<T> of(SuccessMessage successMessage, T data) {
        return BaseResponse.<T>builder()
            .status(successMessage.getHttpStatus().value())
            .message(successMessage.getMessage())
            .data(data)
            .build();
    }

    public static BaseResponse<Void> from(ErrorMessage errorMessage) {
        return BaseResponse.<Void>builder()
            .status(errorMessage.getHttpStatus().value())
            .message(errorMessage.getMessage())
            .build();
    }
}
