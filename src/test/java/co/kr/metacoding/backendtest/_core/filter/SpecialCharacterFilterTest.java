package co.kr.metacoding.backendtest._core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * SpecialCharacterFilter의 통합 테스트 클래스입니다.
 * <p>
 * - MockMvc를 사용해 실제 컨트롤러 요청에 필터가 정상 동작하는지 검증합니다.
 * <p>
 *
 * @Transactional: 을 통해 각 테스트 실행 후 롤백됩니다.
 */
@Transactional
/**
 * @SpringBootTest: 실제 스프링 컨텍스트를 로딩하여 통합 테스트를 수행할 수 있게 함.
 * (내부적으로 @SpringBootConfiguration, @EnableAutoConfiguration 등을 포함)
 */
@SpringBootTest
/**
 * @AutoConfigureMockMvc: MockMvc 객체를 자동 설정 및 주입해줌.
 * (MockMvc는 실제 HTTP 요청 없이 컨트롤러 계층 테스트 가능)
 */
@AutoConfigureMockMvc
public class SpecialCharacterFilterTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;

    /**
     * [실패 테스트] URL에 허용되지 않는 특수문자가 포함되어 필터에서 차단되어야 함.
     */
    @Test
    public void do_filter_test() throws Exception {
        // given
        Integer id = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}?name=test!!", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("URL에 허용되지 않는 특수문자가 포함되어 있습니다."));
    }
}
