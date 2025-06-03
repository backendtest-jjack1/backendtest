package co.kr.metacoding.backendtest._core.error;

import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi400;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi401;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi403;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi404;
import co.kr.metacoding.backendtest._core.utils.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 모든 컨트롤러에 대한 전역 예외 처리기
public class GlobalExceptionHandler {

    // 400 - 클라이언트의 잘못된 요청 처리
    @ExceptionHandler(ExceptionApi400.class)
    @ResponseBody // JSON 형식으로 응답 (뷰가 아닌 응답 본문으로 처리)
    public ResponseEntity<?> exApi400(ExceptionApi400 e) {
        return Resp.fail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // 401 - 인증 실패 (로그인 안 했거나 토큰 없음 등)
    @ExceptionHandler(ExceptionApi401.class)
    @ResponseBody
    public ResponseEntity<?> exApi401(ExceptionApi401 e) {
        return Resp.fail(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    // 403 - 권한 없음 (접근 금지)
    @ExceptionHandler(ExceptionApi403.class)
    @ResponseBody
    public ResponseEntity<?> exApi403(ExceptionApi403 e) {
        return Resp.fail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    // 404 - 요청한 리소스를 찾을 수 없음
    @ExceptionHandler(ExceptionApi404.class)
    @ResponseBody
    public ResponseEntity<?> exApi404(ExceptionApi404 e) {
        return Resp.fail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // 그 외 알 수 없는 모든 예외 처리
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> exUnknown(Exception e) {
        return Resp.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
