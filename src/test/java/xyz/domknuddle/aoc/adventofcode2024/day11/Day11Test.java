package xyz.domknuddle.aoc.adventofcode2024.day11;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day11Test extends DayTest<Day11, String, String> {

    protected static List<Day11> getDays() {
        return getDays(Day11.class);
    }

    @Override
    protected String answerOne() {
        return "55312";
    }

    @Override
    protected String answerTwo() {
        return "";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "125 17"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "125 17"
        });
    }

    @Override
    protected String answerOneExample() {
        return "55312";
    }

    @Override
    protected String answerTwoExample() {
        return "";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day11Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day11Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}