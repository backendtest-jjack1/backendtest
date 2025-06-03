package co.kr.metacoding.backendtest.scheduler;

import co.kr.metacoding.backendtest._core.utils.LottoRankUtils;
import co.kr.metacoding.backendtest._core.utils.RandomNumberUtils;
import co.kr.metacoding.backendtest.lotto.Lotto;
import co.kr.metacoding.backendtest.lotto.LottoRepository;
import co.kr.metacoding.backendtest.lotto.winner.Winner;
import co.kr.metacoding.backendtest.lotto.winner.WinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LottoSchedulerService {
    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;

    @Transactional
    public void checkWinners() {
        // 1. 당첨 번호 생성
        List<Integer> winningNumbers = RandomNumberUtils.generateUniqueRandomNumbers(6, 1, 45);

        // 2. 모든 로또 번호 불러오기
        List<Lotto> lottosPS = lottoRepository.findAll();

        // 3. 비교 및 등수 계산
        List<Winner> winners = new ArrayList<>(); // 배치 저장을 위한 배열

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

        // 4. 당첨자 저장
        winnerRepository.saveAllWithJdbc(winners);
    }
}
