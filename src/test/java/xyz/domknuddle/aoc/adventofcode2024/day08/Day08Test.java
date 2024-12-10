package xyz.domknuddle.aoc.adventofcode2024.day08;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day08Test extends DayTest<Day08, String, String> {

    protected static List<Day08> getDays() {
        return getDays(Day08.class);
    }

    @Override
    protected String answerOne() {
        return "14";
    }

    @Override
    protected String answerTwo() {
        return "34";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "............",
                "........0...",
                ".....0......",
                ".......0....",
                "....0.......",
                "......A.....",
                "............",
                "............",
                "........A...",
                ".........A..",
                "............",
                "............"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "............",
                "........0...",
                ".....0......",
                ".......0....",
                "....0.......",
                "......A.....",
                "............",
                "............",
                "........A...",
                ".........A..",
                "............",
                "............"
        });
    }

    @Override
    protected String answerOneExample() {
        return "14";
    }

    @Override
    protected String answerTwoExample() {
        return "34";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day08Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day08Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}