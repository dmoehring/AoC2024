package xyz.domknuddle.aoc.adventofcode2024.day07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day07Ugly implements Day07 {

    public static void main(String[] args) {
        Day07 day = new Day07Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        return Long.toString(input.mapToLong(e -> {
            Calibration calibration = new Calibration(e);
            if (calibration.isCorrectForAnswerOne()) {
                return calibration.testValue;
            } else {
                return 0;
            }
        }).sum());
    }

    public static String answerTwo(Stream<String> input) {
        return Long.toString(input.mapToLong(e -> {
            Calibration calibration = new Calibration(e);
            if (calibration.isCorrectForAnswerTwo()) {
                return calibration.testValue;
            } else {
                return 0;
            }
        }).sum());
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

class Calibration {
    public final long testValue;
    List<Long> remainingNumbers;

    public Calibration(String line) {
        String regex = "(\\d+):((?: \\d+)+)";
        Pattern stringPattern = Pattern.compile(regex);
        Matcher matcher = stringPattern.matcher(line);
        if (matcher.find()) {
            testValue = Long.parseLong(matcher.group(1));
            remainingNumbers = Arrays.stream(matcher.group(2).trim().split(" ")).map(Long::parseLong).toList();
        } else {
            throw new RuntimeException();
        }

    }

    public boolean isCorrectForAnswerOne() {
        Set<Long> solutions = new HashSet<>();
        for (Long number : remainingNumbers) {
            if (solutions.isEmpty()) {
                solutions.add(number);
            } else {
                Set<Long> nextSolutions = new HashSet<>();
                for (Long solution : solutions) {
                    nextSolutions.add(number + solution);
                    nextSolutions.add(number * solution);
                }
                solutions = nextSolutions;
            }
        }
        return solutions.contains(testValue);
    }

    public boolean isCorrectForAnswerTwo() {
        Set<Long> solutions = new HashSet<>();
        for (Long number : remainingNumbers) {
            if (solutions.isEmpty()) {
                solutions.add(number);
            } else {
                Set<Long> nextSolutions = new HashSet<>();
                for (Long solution : solutions) {
                    nextSolutions.add(number + solution);
                    nextSolutions.add(number * solution);
                    nextSolutions.add(Long.parseLong(Long.toString(solution)+Long.toString(number)));
                }
                solutions = nextSolutions;
            }
        }
        return solutions.contains(testValue);
    }
}