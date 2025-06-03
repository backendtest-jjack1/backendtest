package co.kr.metacoding.backendtest.lotto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoResponse.DTO generateLottoNumbers() {
        Set<Integer> lottoSet = new HashSet<>();
        Random random = new Random();
        while (true) {
            lottoSet.add(random.nextInt(45) + 1);
            if (lottoSet.size() == 6) break;
        }
        return new LottoResponse.DTO(lottoSet);
    }
}
