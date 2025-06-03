package co.kr.metacoding.backendtest.lotto;

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

import static org.hamcrest.Matchers.*;

/**
 * LottoController의 REST API 테스트 클래스입니다.
 * <p>
 * - 각 테스트는 통합 테스트로 실행되며, MockMvc를 이용하여 컨트롤러의 실제 동작을 검증합니다.
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
public class LottoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;

    /**
     * [성공 테스트] 로또 번호 생성 시, 1~45 사이의 중복되지 않은 숫자 6개가 반환되어야 함.
     */
    @Test
    public void generate_lotto_numbers_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/lottos")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers", hasSize(6))) // 숫자가 6개 있는지
                .andExpect(MockMvcResultMatchers.jsonPath("$.numbers[*]",
                        everyItem(
                                allOf(
                                        greaterThanOrEqualTo(1), // 숫자가 1보다 같거나 큰지
                                        lessThanOrEqualTo(45) // 숫자가 45보다 같거나 작은지
                                ))));
    }
}
