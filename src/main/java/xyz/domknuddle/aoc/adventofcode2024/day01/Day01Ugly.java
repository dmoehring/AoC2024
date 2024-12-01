package xyz.domknuddle.aoc.adventofcode2024.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01Ugly implements Day01 {

    public static void main(String[] args) {
        Day01 day = new Day01Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        input.forEach(line -> {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                left.add(Long.parseLong(parts[0]));
                right.add(Long.parseLong(parts[1]));
            }
        });

        left.sort(Long::compareTo);
        right.sort(Long::compareTo);
        long sum = 0L;

        for (int i = 0; i < left.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }

        return Long.toString(sum);
    }

    public static String answerTwo(Stream<String> input) {
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        input.forEach(line -> {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                left.add(Long.parseLong(parts[0]));
                right.add(Long.parseLong(parts[1]));
            }
        });

        Map<Long, Long> rightCounting = right.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long sum = 0L;
        for (Long aLong : left) {
            if (rightCounting.containsKey(aLong)) {
                sum += aLong * rightCounting.get(aLong);
            }
        }

        return Long.toString(sum);
    }

    @Override
    public String answerOne() {
        return answerOne(input());
    }

    @Override
    public String answerTwo() {
        return answerTwo(input());
    }

}