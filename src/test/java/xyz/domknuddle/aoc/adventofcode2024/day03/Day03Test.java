package xyz.domknuddle.aoc.adventofcode2024.day03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day03Test extends DayTest<Day03, String, String> {

    protected static List<Day03> getDays() {
        return getDays(Day03.class);
    }

    @Override
    protected String answerOne() {
        return "161";
    }

    @Override
    protected String answerTwo() {
        return "";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        });
    }

    @Override
    protected String answerOneExample() {
        return "161";
    }

    @Override
    protected String answerTwoExample() {
        return "48";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day03Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day03Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}