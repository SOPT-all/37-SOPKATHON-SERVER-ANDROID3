package sopt.sopkathon37.global.exception;

import lombok.Getter;
import sopt.sopkathon37.global.message.ErrorMessage;

@Getter
public class ServerException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public ServerException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
