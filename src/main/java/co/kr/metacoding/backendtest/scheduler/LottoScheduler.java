package co.kr.metacoding.backendtest.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 매주 일요일 00시에 로또 당첨자 검사를 수행하는 스케줄러입니다.
 */
@RequiredArgsConstructor
@Component
public class LottoScheduler {
    private final LottoSchedulerService lottoSchedulerService;

    /**
     * 매주 일요일 자정(00:00:00)에 실행됩니다.
     * <p>
     * 로또 당첨자 확인 로직을 호출합니다.
     */
    @Scheduled(cron = "0 0 0 * * SUN")
    public void runSchedule() {
        lottoSchedulerService.checkWinners();
    }
}