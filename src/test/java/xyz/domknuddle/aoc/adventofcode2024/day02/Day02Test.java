package xyz.domknuddle.aoc.adventofcode2024.day02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day02Test extends DayTest<Day02, String, String> {

    protected static List<Day02> getDays() {
        return getDays(Day02.class);
    }

    @Override
    protected String answerOne() {
        return "";
    }

    @Override
    protected String answerTwo() {
        return "";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
        });
    }

    @Override
    protected String answerOneExample() {
        return "2";
    }

    @Override
    protected String answerTwoExample() {
        return "4";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day02Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day02Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}