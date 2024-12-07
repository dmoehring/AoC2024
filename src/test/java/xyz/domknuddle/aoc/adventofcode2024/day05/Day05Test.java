package xyz.domknuddle.aoc.adventofcode2024.day05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day05Test extends DayTest<Day05, String, String> {

    protected static List<Day05> getDays() {
        return getDays(Day05.class);
    }

    @Override
    protected String answerOne() {
        return "143";
    }

    @Override
    protected String answerTwo() {
        return "123";
    }

    @Override
    protected Stream<String> inputOneExample() {
        return Arrays.stream(new String[]{
                "47|53",
                "97|13",
                "97|61",
                "97|47",
                "75|29",
                "61|13",
                "75|53",
                "29|13",
                "97|29",
                "53|29",
                "61|53",
                "97|53",
                "61|29",
                "47|13",
                "75|47",
                "97|75",
                "47|61",
                "75|61",
                "47|29",
                "75|13",
                "53|13",
                "",
                "75,47,61,53,29",
                "97,61,53,29,13",
                "75,29,13",
                "75,97,47,61,53",
                "61,13,29",
                "97,13,75,29,47"
        });
    }

    @Override
    protected Stream<String> inputTwoExample() {
        return Arrays.stream(new String[]{
                "47|53",
                "97|13",
                "97|61",
                "97|47",
                "75|29",
                "61|13",
                "75|53",
                "29|13",
                "97|29",
                "53|29",
                "61|53",
                "97|53",
                "61|29",
                "47|13",
                "75|47",
                "97|75",
                "47|61",
                "75|61",
                "47|29",
                "75|13",
                "53|13",
                "",
                "75,47,61,53,29",
                "97,61,53,29,13",
                "75,29,13",
                "75,97,47,61,53",
                "61,13,29",
                "97,13,75,29,47"
        });
    }

    @Override
    protected String answerOneExample() {
        return "143";
    }

    @Override
    protected String answerTwoExample() {
        return "123";
    }

    @Test
    void answerOneExampleTest() {
        Assertions.assertThat(Day05Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
    }

    @Test
    void answerTwoExampleTest() {
        Assertions.assertThat(Day05Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
    }
}