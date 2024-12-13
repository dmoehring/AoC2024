package xyz.domknuddle.aoc.adventofcode2024.day13;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day13Test extends DayTest<Day13, String, String> {

    protected static List<Day13> getDays() {
        return getDays(Day13.class);
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
                "Button A: X+94, Y+34",
                "Button B: X+22, Y+67",
                "Prize: X=8400, Y=5400",
                "",
                "Button A: X+26, Y+66",
                "Button B: X+67, Y+21",
                "Prize: X=12748, Y=12176",
                "",
                "Button A: X+17, Y+86",
                "Button B: X+84, Y+37",
                "Prize: X=7870, Y=6450",
                "",
                "Button A: X+69, Y+23",
                "Button B: X+27, Y+71",
                "Prize: X=18641, Y=10279"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
        });
    }

    @Override
    protected String answerOneExample() {
        return "480";
    }

    @Override
    protected String answerTwoExample() {
        return "";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day13Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day13Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}