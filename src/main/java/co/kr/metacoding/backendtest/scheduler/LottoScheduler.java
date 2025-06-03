package co.kr.metacoding.backendtest.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LottoScheduler {
    private final LottoSchedulerService lottoSchedulerService;

    @Scheduled(cron = "0 0 0 * * SUN")
    public void runSchedule() {
        lottoSchedulerService.checkWinners();
    }
}
