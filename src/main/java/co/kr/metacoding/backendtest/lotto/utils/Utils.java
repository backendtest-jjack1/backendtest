package co.kr.metacoding.backendtest.lotto.utils;

import java.util.*;

public class Utils {

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
