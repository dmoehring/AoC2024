package xyz.domknuddle.aoc.adventofcode2024.day12;

import java.util.*;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

class Day12Test extends DayTest<Day12, String, String> {

	protected static List<Day12> getDays() {
		return getDays(Day12.class);
	}

	@Override
	protected String answerOne() {
		return "140";
	}

	@Override
	protected String answerTwo() {
		return "80";
	}

	 @Override
        protected Stream<String> inputOneExample() {
            return Arrays.stream(new String[]{
                    "AAAA",
                    "BBCD",
                    "BBCC",
                    "EEEC"
            });
        }

        @Override
        protected Stream<String> inputTwoExample() {
            return Arrays.stream(new String[]{
                    "AAAA",
                    "BBCD",
                    "BBCC",
                    "EEEC"
            });
        }

        @Override
        protected String answerOneExample() {
            return "140";
        }

        @Override
        protected String answerTwoExample() {
            return "80";
        }

        @Test
        void answerOneExampleTest() {
            Assertions.assertThat(Day12Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
        }

        @Test
        void assertOneExampleTest2() {
            Stream<String> input = Arrays.stream(new String[]{
                    "OOOOO",
                    "OXOXO",
                    "OOOOO",
                    "OXOXO",
                    "OOOOO"
            });
            Assertions.assertThat(Day12Ugly.answerOne(input)).isEqualTo("772");
        }

        @Test
        void answerTwoExampleTest() {
            Assertions.assertThat(Day12Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
        }
}