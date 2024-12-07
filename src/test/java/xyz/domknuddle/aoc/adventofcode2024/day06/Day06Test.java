package xyz.domknuddle.aoc.adventofcode2024.day06;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day06Test extends DayTest<Day06, String, String> {

    protected static List<Day06> getDays() {
        return getDays(Day06.class);
    }

    @Override
    protected String answerOne() {
        return "41";
    }

    @Override
    protected String answerTwo() {
        return "6";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#..."
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#..."
        });
    }

    @Override
    protected String answerOneExample() {
        return "41";
    }

    @Override
    protected String answerTwoExample() {
        return "6";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day06Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day06Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}