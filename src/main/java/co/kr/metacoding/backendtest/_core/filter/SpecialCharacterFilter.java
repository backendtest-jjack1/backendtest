package co.kr.metacoding.backendtest._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
            sendForbiddenResponse(res);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 상태코드 403 및 url 허용금지 메시지 응답
     *
     * @param res
     * @throws IOException
     */
    void sendForbiddenResponse(HttpServletResponse res) throws IOException {
        // 응답 상태코드 403
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // utf-8 설정
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // json 응답
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 응답 본문 출력 및 flush
        PrintWriter writer = res.getWriter();
        writer.println("{\"reason\": \"URL에 허용되지 않는 특수문자가 포함되어 있습니다.\"}"); // \n 이 명확하게 포함되야함
        writer.flush(); // 명확하게 버퍼 응답
    }
}
