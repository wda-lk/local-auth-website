package lk.wda.localauthwebsite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MalformedImageURL extends APIException {
    public MalformedImageURL() {
    }

    public MalformedImageURL(String msg) {
        super(msg);
    }

    public MalformedImageURL(String msg, Throwable e) {
        super(msg, e);
    }
}
