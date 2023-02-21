package lk.wda.localauthwebsite.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class GoogleSheetConfigException extends APIException {
    public GoogleSheetConfigException() {
        super();
    }

    public GoogleSheetConfigException(String msg) {
        super(msg);
    }

    public GoogleSheetConfigException(String msg, Throwable e) {
        super(msg, e);
    }
}
