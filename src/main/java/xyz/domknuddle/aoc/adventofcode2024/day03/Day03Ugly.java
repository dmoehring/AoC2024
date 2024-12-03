package xyz.domknuddle.aoc.adventofcode2024.day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day03Ugly implements Day03 {
    static boolean enablesMulInstructions = true;

    public static void main(String[] args) {
        Day03 day = new Day03Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        return Long.toString(input.mapToLong(Day03Ugly::multiplySomeNumbers).sum());
    }

    public static String answerTwo(Stream<String> input) {
        return Long.toString(input.mapToLong(Day03Ugly::multiplySomeNumbersPartTwo).sum());
    }

    private static long multiplySomeNumbers(String input) {
        Pattern stringPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = stringPattern.matcher(input);
        long sum = 0L;
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            sum += Long.parseLong(first) * Long.parseLong(second);
        }
        return sum;
    }

    private static long multiplySomeNumbersPartTwo(String input) {
        Pattern stringPattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\))");
        Matcher matcher = stringPattern.matcher(input);
        long sum = 0L;
        while (matcher.find()) {
            String group = matcher.group();
            switch (group) {
                case "do()":
                    enablesMulInstructions = true;
                    break;
                case "don't()":
                    enablesMulInstructions = false;
                    break;
                default:
                    if (enablesMulInstructions) {
                        String first = matcher.group(2);
                        String second = matcher.group(3);
                        sum += Long.parseLong(first) * Long.parseLong(second);
                    }
                    break;
            }
        }
        return sum;
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