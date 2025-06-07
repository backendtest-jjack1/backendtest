package co.kr.metacoding.backendtest.scheduler;

import co.kr.metacoding.backendtest._core.utils.LottoRankUtils;
import co.kr.metacoding.backendtest.lotto.Lotto;
import co.kr.metacoding.backendtest.lotto.LottoRepository;
import co.kr.metacoding.backendtest.lotto.winner.Winner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LottoSchedulerServiceTest {

    @Mock
    private LottoRepository lottoRepository;

    @InjectMocks
    private LottoSchedulerService lottoSchedulerService;

    /**
     * [단위 테스트] checkWinners 내부 로직을 직접 수행하여,
     * 특정 로또 번호에 대해 당첨 등수가 정확히 계산되는지 검증한다.
     * <p>
     * - 임의로 로또 번호 리스트를 Mock하여 리턴하도록 설정한다.
     * <p>
     * - 3등에 해당하는 당첨 번호를 만들어서 검증한다.
     */
    @Test
    public void check_winners_logic_test() {
        // given
        // DB에 저장된 번호 (3등 당첨 가능하게)
        Lotto lottoPS = Lotto.builder()
                .number1(1)
                .number2(2)
                .number3(3)
                .number4(4)
                .number5(5)
                .number6(6)
                .build();

        // lottoRepository.findAll() 호출 시 위 리스트 리턴하도록 Mock 설정
        when(lottoRepository.findAll()).thenReturn(List.of(lottoPS));

        // 1. 당첨 번호 생성 (3등 맞추기 위해 예시 번호 설정)
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 11, 12);

        // when
        // 2. 모든 로또 번호 불러오기
        List<Lotto> lottosPS = lottoRepository.findAll();

        // 3. 비교 등수 계산
        List<Winner> winners = new ArrayList<>();

        for (Lotto lotto : lottosPS) {
            // 로또 번호 배열 생성
            List<Integer> lottoNumbers = List.of(
                    lotto.getNumber1(), lotto.getNumber2(), lotto.getNumber3(),
                    lotto.getNumber4(), lotto.getNumber5(), lotto.getNumber6()
            );

            // 로또 번호 매칭 개수
            int matchCount = (int) lottoNumbers.stream()
                    .filter(number -> winningNumbers.contains(number))
                    .count();

            // 등수 계산
            Integer rank = LottoRankUtils.getRank(matchCount);

            if (rank != null) {
                winners.add(Winner.builder()
                        .lotto(lotto)
                        .rank(rank)
                        .build());
            }
        }

        // then
        assertThat(winners).isNotEmpty();
        Winner firstWinner = winners.get(0);

        // matchCount가 3개니까 3등이어야 함
        assertThat(firstWinner.getRank()).isEqualTo(3);

        // lotto 객체가 정확히 연결되어 있는지
        assertThat(firstWinner.getLotto()).isEqualTo(lottoPS);
    }
}