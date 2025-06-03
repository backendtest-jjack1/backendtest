package co.kr.metacoding.backendtest._core.error;

import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi400;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi401;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi403;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi404;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionApi400.class)
    @ResponseBody
    public ResponseEntity<?> exApi400(ExceptionApi400 e) {
        return ExceptionResp.fail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ExceptionApi401.class)
    @ResponseBody
    public ResponseEntity<?> exApi401(ExceptionApi401 e) {
        return ExceptionResp.fail(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(ExceptionApi403.class)
    @ResponseBody
    public ResponseEntity<?> exApi403(ExceptionApi403 e) {
        return ExceptionResp.fail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(ExceptionApi404.class)
    @ResponseBody
    public ResponseEntity<?> exApi404(ExceptionApi404 e) {
        return ExceptionResp.fail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> exUnknown(ExceptionApi400 e) {
        return ExceptionResp.fail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
