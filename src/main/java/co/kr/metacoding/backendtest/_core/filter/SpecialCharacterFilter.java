package co.kr.metacoding.backendtest._core.filter;

import co.kr.metacoding.backendtest._core.error.ExceptionResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SpecialCharacterFilter implements Filter {

    // 허용하는 특수문자 목록
    private static final String ALLOWED_SPECIALS = "?&=:/";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();             // 경로 (예: /api/user)
        String query = req.getQueryString();          // 쿼리 스트링 (예: id=123&name=abc)

        // 검사할 대상 문자열 생성 (URI + ? + query)
        String fullUrl = uri + (query == null ? "" : "?" + query);

        // 허용 문자 제외한 특수문자 있는지 검사
        // 허용 문자(영숫자 + ALLOWED_SPECIALS) 이외의 문자가 있으면 차단
        if (containsForbiddenCharacters(fullUrl)) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.setContentType("application/json;charset=UTF-8");

            ExceptionResp resp = new ExceptionResp("URL에 허용되지 않는 특수문자가 포함되어 있습니다.");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resp);

            res.getWriter().write(json);
            res.getWriter().flush();
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean containsForbiddenCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (!isAllowedCharacter(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAllowedCharacter(char ch) {
        // 영문자, 숫자 허용
        if (Character.isLetterOrDigit(ch)) {
            return true;
        }
        // 허용 특수문자 포함 여부 검사
        if (ALLOWED_SPECIALS.indexOf(ch) >= 0) {
            return true;
        }
        // '/' 2개 연속 허용하니 따로 검사하지 않아도 됨 (이미 '/'는 허용됨)
        return false;
    }
}
