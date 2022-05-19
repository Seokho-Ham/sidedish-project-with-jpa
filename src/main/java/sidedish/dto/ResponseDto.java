package sidedish.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto<T> {

    private final int result_code;
    private final String result_message;
    private final T result_body;

    public ResponseDto(HttpStatus httpStatus) {
        result_code = httpStatus.value();
        result_message = httpStatus.name();
        result_body = null;
    }

    public ResponseDto(HttpStatus httpStatus, T result_body) {
        result_code = httpStatus.value();
        result_message = httpStatus.name();
        this.result_body = result_body;
    }
}
