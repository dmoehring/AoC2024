package xyz.domknuddle.aoc.adventofcode2024.day01;

import java.util.*;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

class Day01Test extends DayTest<Day01, String, String> {

	protected static List<Day01> getDays() {
		return getDays(Day01.class);
	}

	@Override
	protected String answerOne() {
		return "11";
	}

	@Override
	protected String answerTwo() {
		return "";
	}

	 @Override
        protected Stream<String> inputOneExample() {
            return Arrays.stream(new String[]{
                    "3   4",
                    "4   3",
                    "2   5",
                    "1   3",
                    "3   9",
                    "3   3"
            });
        }

        @Override
        protected Stream<String> inputTwoExample() {
            return Arrays.stream(new String[]{
                    "3   4",
                    "4   3",
                    "2   5",
                    "1   3",
                    "3   9",
                    "3   3"
            });
        }

        @Override
        protected String answerOneExample() {
            return "11";
        }

        @Override
        protected String answerTwoExample() {
            return "31";
        }

        @Test
        void answerOneExampleTest() {
            Assertions.assertThat(Day01Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
        }

        @Test
        void answerTwoExampleTest() {
            Assertions.assertThat(Day01Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
        }
}