package xyz.domknuddle.aoc.adventofcode2024.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Position implements Comparable<Position> {
    public int i;
    public int j;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static Map<Position, Character> inputToMap(Stream<String> input) {
        List<String> inputList = input.toList();
        Map<Position, Character> map = new HashMap<>();
        for (int i = 0; i < inputList.size(); i++) {
            String line = inputList.get(i);
            for (int j = 0; j < line.length(); j++) {
                map.put(new Position(i, j), line.charAt(j));
            }
        }
        return map;
    }

    public int compareTo(Position o) {
        if (i + j == o.i + o.j) {
            return i == o.i ? o.j - j : o.i - i;
        } else {
            return i + j - (o.i + o.j);
        }
    }

    @Override
    public String toString() {
        return i + " " + j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return i == position.i && j == position.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
