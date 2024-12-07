package xyz.domknuddle.aoc.adventofcode2024.day07;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day07Test extends DayTest<Day07, String, String> {

    protected static List<Day07> getDays() {
        return getDays(Day07.class);
    }

    @Override
    protected String answerOne() {
        return "3749";
    }

    @Override
    protected String answerTwo() {
        return "11387";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
        });
    }

    @Override
    protected String answerOneExample() {
        return "3749";
    }

    @Override
    protected String answerTwoExample() {
        return "11387";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day07Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day07Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}