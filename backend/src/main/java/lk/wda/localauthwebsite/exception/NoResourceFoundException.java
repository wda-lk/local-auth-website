package lk.wda.localauthwebsite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResourceFoundException extends APIException {
    public NoResourceFoundException() {
    }

    public NoResourceFoundException(String msg) {
        super(msg);
    }

    public NoResourceFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}
