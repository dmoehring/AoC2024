package xyz.domknuddle.aoc.adventofcode2024.day04;

import java.util.*;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

class Day04Test extends DayTest<Day04, String, String> {

	protected static List<Day04> getDays() {
		return getDays(Day04.class);
	}

	@Override
	protected String answerOne() {
		return "18";
	}

	@Override
	protected String answerTwo() {
		return "";
	}

	 @Override
        protected Stream<String> inputOneExample() {
            return Arrays.stream(new String[]{
                    "MMMSXXMASM",
                    "MSAMXMSMSA",
                    "AMXSXMAAMM",
                    "MSAMASMSMX",
                    "XMASAMXAMM",
                    "XXAMMXXAMA",
                    "SMSMSASXSS",
                    "SAXAMASAAA",
                    "MAMMMXMMMM",
                    "MXMXAXMASX"
            });
        }

        @Override
        protected Stream<String> inputTwoExample() {
            return Arrays.stream(new String[]{
                    "MMMSXXMASM",
                    "MSAMXMSMSA",
                    "AMXSXMAAMM",
                    "MSAMASMSMX",
                    "XMASAMXAMM",
                    "XXAMMXXAMA",
                    "SMSMSASXSS",
                    "SAXAMASAAA",
                    "MAMMMXMMMM",
                    "MXMXAXMASX"
            });
        }

        @Override
        protected String answerOneExample() {
            return "18";
        }

        @Override
        protected String answerTwoExample() {
            return "9";
        }

        @Test
        void answerOneExampleTest() {
            Assertions.assertThat(Day04Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
        }

        @Test
        void answerTwoExampleTest() {
            Assertions.assertThat(Day04Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
        }
}