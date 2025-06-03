package co.kr.metacoding.backendtest._core.utils;

public class LottoRankUtils {

    /**
     * matchCount 에 따라 rank 를 돌려준다
     *
     * @param matchCount
     * @return Integer (1, 2, 3, 4, 5) or null when no match
     */
    public static Integer getRank(Integer matchCount) {
        return switch (matchCount) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5;
            default -> null; // 낙첨
        };
    }
}
