package co.kr.metacoding.backendtest._core.config;

import co.kr.metacoding.backendtest._core.filter.SpecialCharacterFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SpecialCharacterFilter> specialCharacterFilter() {
        FilterRegistrationBean<SpecialCharacterFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SpecialCharacterFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 적용
        registrationBean.setOrder(1); // 필터 순서 설정
        return registrationBean;
    }
}