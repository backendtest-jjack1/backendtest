package co.kr.metacoding.backendtest._core.config;

import co.kr.metacoding.backendtest._core.filter.SpecialCharacterFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilterConfig {

    // URL에 허용되지 않은 특수문자가 포함된 요청을 차단하는 필터 등록
    @Bean
    public FilterRegistrationBean<SpecialCharacterFilter> specialCharacterFilter() {
        FilterRegistrationBean<SpecialCharacterFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SpecialCharacterFilter()); // 실제 필터 객체 등록
        registrationBean.addUrlPatterns("/*"); // 모든 URL 요청에 필터 적용
        registrationBean.setOrder(1); // 필터 실행 순서 설정 (숫자가 작을수록 먼저 실행)
        return registrationBean;
    }
}