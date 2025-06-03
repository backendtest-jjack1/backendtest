package co.kr.metacoding.backendtest.lotto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class LottoResponse {

    @Data
    public static class DTO {
        private List<Integer> numbers;

        public DTO(Lotto lotto) {
            this.numbers = Arrays.asList(
                    lotto.getNumber1(),
                    lotto.getNumber2(),
                    lotto.getNumber3(),
                    lotto.getNumber4(),
                    lotto.getNumber5(),
                    lotto.getNumber6()
            );
        }
    }
}
