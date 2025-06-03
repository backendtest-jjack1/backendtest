package co.kr.metacoding.backendtest.lotto;

import co.kr.metacoding.backendtest.lotto.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LottoService {
    private final LottoRepository lottoRepository;

    @Transactional
    public LottoResponse.DTO generateLottoNumbers() {
        List<Integer> lottoNumbers = Utils.generateUniqueRandomNumbers(6, 1, 45);
        Lotto lotto = Lotto.builder()
                .number1(lottoNumbers.get(0))
                .number2(lottoNumbers.get(1))
                .number3(lottoNumbers.get(2))
                .number4(lottoNumbers.get(3))
                .number5(lottoNumbers.get(4))
                .number6(lottoNumbers.get(5))
                .build();
        Lotto savedLotto = lottoRepository.save(lotto);
        return new LottoResponse.DTO(savedLotto);
    }
}
