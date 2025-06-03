package co.kr.metacoding.backendtest.scheduler;

import co.kr.metacoding.backendtest.lotto.winner.Winner;
import co.kr.metacoding.backendtest.lotto.winner.WinnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class LottoSchedulerServiceTest {

    @Autowired
    private LottoSchedulerService lottoSchedulerService;
    @Autowired
    private WinnerRepository winnerRepository;

    // 스케줄러가 호출할 대상 메서드만 테스트하는 것이 일반적
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