package co.kr.metacoding.backendtest._core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Resp {

    /**
     * 성공 응답 생성 메서드
     * <p>
     * HTTP 상태코드 200(OK)와 함께 body를 반환합니다.
     *
     * @param body 클라이언트에게 응답할 데이터
     * @param <T>  응답 데이터의 타입 (예: DTO, Map 등)
     * @return ResponseEntity<T> - 상태코드 200 + body 포함
     */
    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    /**
     * 실패 응답 생성 메서드
     * <p>
     * HTTP 상태코드와 실패 사유(reason)를 담아 클라이언트에게 반환합니다.
     *
     * @param status 응답할 HTTP 상태 코드 (예: 400, 403, 404 등)
     * @param reason 클라이언트에게 전달할 실패 사유 메시지
     * @return ResponseEntity<Map < String, String>> - 상태코드 + reason 포함
     */
    public static ResponseEntity<Map<String, String>> fail(HttpStatus status, String reason) {
        Map<String, String> body = new HashMap<>();
        body.put("reason", reason);
        return ResponseEntity.status(status).body(body);
    }
}