package co.kr.metacoding.backendtest.lotto;

import co.kr.metacoding.backendtest._core.utils.RandomNumberUtils;
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
        // 1. 로또 번호 배열 생성
        List<Integer> lottoNumbers = RandomNumberUtils.generateUniqueRandomNumbers(6, 1, 45);

        // 2. 로또 객체 생성
        Lotto lotto = Lotto.builder()
                .number1(lottoNumbers.get(0))
                .number2(lottoNumbers.get(1))
                .number3(lottoNumbers.get(2))
                .number4(lottoNumbers.get(3))
                .number5(lottoNumbers.get(4))
                .number6(lottoNumbers.get(5))
                .build();

        // 3. 로또 저장
        Lotto lottoPS = lottoRepository.save(lotto);

        // 4. 로또 응답
        return new LottoResponse.DTO(lottoPS);
    }
}
