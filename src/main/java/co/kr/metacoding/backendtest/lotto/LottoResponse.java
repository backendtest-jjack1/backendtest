package co.kr.metacoding.backendtest.lotto;

import lombok.Data;

import java.util.Set;

public class LottoResponse {

    @Data
    public static class DTO {
        private Set<Integer> numbers;

        public DTO(Set<Integer> numbers) {
            this.numbers = numbers;
        }
    }
}
