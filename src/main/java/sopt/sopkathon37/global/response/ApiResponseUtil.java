package sopt.sopkathon37.global.response;

import org.springframework.http.ResponseEntity;
import sopt.sopkathon37.global.message.ErrorMessage;
import sopt.sopkathon37.global.message.SuccessMessage;

public interface ApiResponseUtil {

    static ResponseEntity<BaseResponse<Void>> success(SuccessMessage successMessage) {
        return ResponseEntity.status(successMessage.getHttpStatus())
            .body(BaseResponse.from(successMessage));
    }

    static <T> ResponseEntity<BaseResponse<T>> success(SuccessMessage successMessage, T data) {
        return ResponseEntity.status(successMessage.getHttpStatus())
            .body(BaseResponse.of(successMessage, data));
    }

    static ResponseEntity<BaseResponse<Void>> failure(ErrorMessage errorMessage) {
        return ResponseEntity.status(errorMessage.getHttpStatus())
            .body(BaseResponse.from(errorMessage));
    }
}
