package co.kr.metacoding.backendtest._core.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Aspect: 이 클래스가 AOP(관점 지향 프로그래밍) Aspect임을 나타냅니다.
 * 특정 포인트컷에 대해 공통 관심 사항(Advice)을 적용할 수 있습니다.
 * @Component: 스프링 빈으로 등록되어 DI(의존성 주입) 및 관리가 가능하도록 합니다.
 * @RequiredArgsConstructor: final 또는 @NonNull 필드에 대해 생성자를 자동 생성합니다.
 * (Lombok 어노테이션)
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogHandler {

    private final HttpServletRequest req;

    /**
     * @param joinPoint 실행되는 메서드의 join point 정보
     * @LogUserAgent 애노테이션이 붙은 메서드 실행 전에 동작하는 AOP advice.
     * <p>
     * HTTP 요청 헤더에서 User-Agent 값을 가져와 로그로 출력합니다.
     */
    @Before("@annotation(co.kr.metacoding.backendtest._core.log.anno.LogUserAgent)")
    public void logUserAgent(JoinPoint joinPoint) {
        // HTTP 요청 헤더에서 User-Agent 정보 추출
        String userAgent = req.getHeader("User-Agent");

        // User-Agent 정보 로그 출력
        log.info("Client Agent: " + userAgent);
    }
}
