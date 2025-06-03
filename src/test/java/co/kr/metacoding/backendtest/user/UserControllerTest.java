package co.kr.metacoding.backendtest.user;

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
 * UserController의 REST API 테스트 클래스입니다.
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
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;

    /**
     * [실패 테스트] 이미 존재하는 name으로 회원 가입 시 예외가 발생해야 함.
     */
    @Test
    public void save_user_fail_test() throws Exception {
        // given
        UserRequest.SaveDTO reqDTO = new UserRequest.SaveDTO();
        reqDTO.setName("ssar");

        String requestBody = om.writeValueAsString(reqDTO);
        System.out.println("requestBody: " + requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("이미 존재하는 name 입니다"));
    }

    /**
     * [성공 테스트] 새로운 유저를 정상적으로 등록할 수 있어야 함.
     */
    @Test
    public void save_user_test() throws Exception {
        // given
        UserRequest.SaveDTO reqDTO = new UserRequest.SaveDTO();
        reqDTO.setName("test");

        String requestBody = om.writeValueAsString(reqDTO);
        System.out.println("requestBody: " + requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }

    /**
     * [실패 테스트] 존재하지 않는 id로 유저 조회 시 예외가 발생해야 함.
     */
    @Test
    public void get_user_fail_test() throws Exception {
        // given
        Integer id = 5;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("존재하지 않는 user 입니다"));
    }

    /**
     * [성공 테스트] id로 유저를 정상적으로 조회할 수 있어야 함.
     */
    @Test
    public void get_user_test() throws Exception {
        // given
        Integer id = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ssar"));
    }

    /**
     * [실패 테스트] 이미 존재하는 name으로 수정하려 하면 예외가 발생해야 함.
     */
    @Test
    public void update_user_fail_test() throws Exception {
        // given
        Integer id = 1;
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setName("ssar");

        String requestBody = om.writeValueAsString(reqDTO);
        System.out.println("requestBody: " + requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("이미 존재하는 name 입니다"));
    }

    /**
     * [성공 테스트] 유저의 이름을 정상적으로 수정할 수 있어야 함.
     */
    @Test
    public void update_user_test() throws Exception {
        // given
        Integer id = 1;
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setName("test");

        String requestBody = om.writeValueAsString(reqDTO);
        System.out.println("requestBody: " + requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"));
    }
}
