package co.kr.metacoding.backendtest._core.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogHandler {
    private final HttpServletRequest req;

    @Before("@annotation(co.kr.metacoding.backendtest._core.log.anno.LogUserAgent)")
    public void logUserAgent(JoinPoint joinPoint) {
        String userAgent = req.getHeader("User-Agent");
        log.info("Client Agent: " + userAgent);
    }
}
