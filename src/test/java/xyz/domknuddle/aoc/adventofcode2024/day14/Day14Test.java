package xyz.domknuddle.aoc.adventofcode2024.day14;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day14Test extends DayTest<Day14, String, String> {

    protected static List<Day14> getDays() {
        return getDays(Day14.class);
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
                "p=0,4 v=3,-3",
                "p=6,3 v=-1,-3",
                "p=10,3 v=-1,2",
                "p=2,0 v=2,-1",
                "p=0,0 v=1,3",
                "p=3,0 v=-2,-2",
                "p=7,6 v=-1,-3",
                "p=3,0 v=-1,-2",
                "p=9,3 v=2,3",
                "p=7,3 v=-1,2",
                "p=2,4 v=2,-3",
                "p=9,5 v=-3,-3"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
        });
    }

    @Override
    protected String answerOneExample() {
        return "12";
    }

    @Override
    protected String answerTwoExample() {
        return "";
    }

    @Test
    void answerOneExampleTest() {
        Day14Ugly.height = 7;
        Day14Ugly.width = 11;
        Assertions.assertThat(Day14Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerOneExampleTest2() {
        Day14Ugly.height = 7;
        Day14Ugly.width = 11;
        Assertions.assertThat(Day14Ugly.answerOne(Arrays.stream(new String[]{"p=2,4 v=2,-3"}))).isEqualTo("0");
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day14Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}