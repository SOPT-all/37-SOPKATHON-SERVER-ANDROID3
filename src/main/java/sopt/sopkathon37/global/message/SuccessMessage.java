package sopt.sopkathon37.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS(HttpStatus.OK, "요청이 성공했습니다."),
    CREATED(HttpStatus.CREATED, "생성되었습니다."),
    DELETED(HttpStatus.OK, "삭제가 완료되었습니다."),
    UPDATED(HttpStatus.OK, "업데이트가 완료되었습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
