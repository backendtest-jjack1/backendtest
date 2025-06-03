package co.kr.metacoding.backendtest._core.log.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 사용자 요청의 User-Agent 헤더를 로깅하기 위한 커스텀 어노테이션입니다.
 * <p>
 * 이 어노테이션은 메서드에만 적용 가능하며, 런타임 시 AOP 등을 통해 로깅 처리를 수행합니다.
 */
@Target(ElementType.METHOD) // 메서드에만 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) // 런타임에 동작하는 AOP 등에 사용할 수 있음
public @interface LogUserAgent {
}
