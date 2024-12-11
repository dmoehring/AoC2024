package xyz.domknuddle.aoc.adventofcode2024.day10;

import java.util.*;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.domknuddle.aoc.adventofcode2024.DayTest;

class Day10Test extends DayTest<Day10, String, String> {

	protected static List<Day10> getDays() {
		return getDays(Day10.class);
	}

	@Override
	protected String answerOne() {
		return "36";
	}

	@Override
	protected String answerTwo() {
		return "81";
	}

	 @Override
        protected Stream<String> inputOneExample() {
            return Arrays.stream(new String[]{
                    "89010123",
                    "78121874",
                    "87430965",
                    "96549874",
                    "45678903",
                    "32019012",
                    "01329801",
                    "10456732"
            });
        }

        @Override
        protected Stream<String> inputTwoExample() {
            return Arrays.stream(new String[]{
                    "89010123",
                    "78121874",
                    "87430965",
                    "96549874",
                    "45678903",
                    "32019012",
                    "01329801",
                    "10456732"
            });
        }

        @Override
        protected String answerOneExample() {
            return "36";
        }

        @Override
        protected String answerTwoExample() {
            return "81";
        }

        @Test
        void answerOneExampleTest() {
            Assertions.assertThat(Day10Ugly.answerOne(inputOneExample())).isEqualTo(answerOneExample());
        }

        @Test
        void answerTwoExampleTest() {
            Assertions.assertThat(Day10Ugly.answerTwo(inputTwoExample())).isEqualTo(answerTwoExample());
        }
}