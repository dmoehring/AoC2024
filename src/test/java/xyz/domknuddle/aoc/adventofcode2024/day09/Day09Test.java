package xyz.domknuddle.aoc.adventofcode2024.day09;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day09Test extends DayTest<Day09, String, String> {

    protected static List<Day09> getDays() {
        return getDays(Day09.class);
    }

    @Override
    protected String answerOne() {
        return "1928";
    }

    @Override
    protected String answerTwo() {
        return "2858";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "2333133121414131402"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "2333133121414131402"
        });
    }

    @Override
    protected String answerOneExample() {
        return "1928";
    }

    @Override
    protected String answerTwoExample() {
        return "2858";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day09Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day09Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}