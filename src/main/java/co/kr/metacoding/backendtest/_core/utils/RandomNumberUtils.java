package co.kr.metacoding.backendtest._core.utils;

import java.util.*;

public class RandomNumberUtils {

    /**
     * 랜덤 숫자 배열 생성
     *
     * @param count 생성할 숫자의 개수
     * @param min   최소값 (포함)
     * @param max   최대값 (포함)
     * @return List<Integer> 고유한 랜덤 정수 리스트
     */
    public static List<Integer> generateUniqueRandomNumbers(int count, int min, int max) {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();
        while (true) {
            numbers.add(random.nextInt(max - min + 1) + min);
            if (numbers.size() == 6) break;
        }
        return new ArrayList<>(numbers);
    }
}
