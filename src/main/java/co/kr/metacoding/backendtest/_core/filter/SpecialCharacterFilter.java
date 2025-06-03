package co.kr.metacoding.backendtest._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class SpecialCharacterFilter implements Filter {

    // 허용 문자 정규식 패턴 (영문자, 숫자, ? & = : / 만 허용)
    private static final Pattern ALLOWED_PATTERN = Pattern.compile("^[a-zA-Z0-9?&=:/]*$");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();             // 경로 (예: /api/user)
        String query = req.getQueryString();          // 쿼리 스트링 (예: id=123&name=abc)

        // 검사할 대상 문자열 생성 (URI + ? + query)
        String fullUrl = uri + (query == null ? "" : "?" + query);

        // h2-console 경로는 필터 통과
        if (uri.startsWith("/h2-console")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 정규식으로 허용되지 않은 문자 검사
        if (!ALLOWED_PATTERN.matcher(fullUrl).matches()) {
            // 응답 상태코드 403
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // json 응답
            res.setContentType("application/json;charset=UTF-8");

            // json 응답 모양으로 return
            res.getWriter().write("{\"reason\": \"URL에 허용되지 않는 특수문자가 포함되어 있습니다.\"}");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
