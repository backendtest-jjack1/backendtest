package co.kr.metacoding.backendtest._core.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Data
public class ExceptionResp {
    private String reason;

    public static ResponseEntity fail(HttpStatus status, String reason) {
        ExceptionResp resp = new ExceptionResp(reason);
        return new ResponseEntity<>(resp, status);
    }
}
