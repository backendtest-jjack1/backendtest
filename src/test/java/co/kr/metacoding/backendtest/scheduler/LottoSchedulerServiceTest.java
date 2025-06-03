package co.kr.metacoding.backendtest.scheduler;

import co.kr.metacoding.backendtest.lotto.winner.Winner;
import co.kr.metacoding.backendtest.lotto.winner.WinnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LottoSchedulerService의 스케줄러 동작 관련 통합 테스트 클래스입니다.
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
public class LottoSchedulerServiceTest {

    @Autowired
    private LottoSchedulerService lottoSchedulerService;
    @Autowired
    private WinnerRepository winnerRepository;

    /**
     * [통합 테스트] 스케줄러에서 호출하는 당첨자 체크 기능이 정상 동작하는지 검증한다.
     * - 당첨자 데이터가 저장되어 있는 경우, 첫번째 당첨자의 순위와 로또 번호가 정상인지 확인한다.
     */
    @Test
    public void check_winners_test() {
        // when
        lottoSchedulerService.checkWinners();

        // eye
        List<Winner> winnersPS = winnerRepository.findAll();

        if (winnersPS.isEmpty()) {
            System.out.println("저장된 당첨자가 없습니다.");
            return;
        }

        Winner firstWinner = winnersPS.get(0);
        System.out.println("위너 아이디: " + firstWinner.getId());
        System.out.println("위너 로또아이디: " + firstWinner.getLotto().getId());
        System.out.println("위너 순위: " + firstWinner.getRank());

        // then
        assertThat(firstWinner.getRank()).isBetween(1, 5); // 순위가 1 ~ 5 사이인가
        assertThat(firstWinner.getLotto()).isNotNull(); // 로또 번호가 존재 하는가
    }
}