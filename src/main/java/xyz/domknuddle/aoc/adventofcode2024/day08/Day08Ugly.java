package xyz.domknuddle.aoc.adventofcode2024.day08;

import java.util.*;
import java.util.stream.Stream;

public class Day08Ugly implements Day08 {

    public static void main(String[] args) {
        Day08 day = new Day08Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    // Between 280
    public static String answerOne(Stream<String> input) {
        Map<Position, Character> map = new HashMap<>();
        List<String> list = input.toList();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            char[] arr = s.toCharArray();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != '.') {
                    map.put(new Position(i, j), arr[j]);
                }
            }
        }
        int counter = 0;
        for (Character c : new HashSet<>(map.values())) {
            List<Position> positions = map.entrySet().stream().filter(e -> e.getValue().equals(c)).map(Map.Entry::getKey).toList();
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position a = positions.get(i);
                    Position b = positions.get(j);
                    int abx = b.x - a.x;
                    int aby = b.y - a.y;
                    Position aAntinode = new Position(a.x + 2 * abx, a.y + 2 * aby);
                    Position bAntinode = new Position(a.x - abx, a.y - aby);
                    if (!map.containsKey(aAntinode) && aAntinode.x >= 0 && aAntinode.x < list.size() && aAntinode.y >= 0 && aAntinode.y < list.get(0).length()) {
                        counter++;
                    }
                    if (!map.containsKey(bAntinode) && bAntinode.x >= 0 && bAntinode.x < list.size() && bAntinode.y >= 0 && bAntinode.y < list.get(0).length()) {
                        counter++;
                    }
                }
            }
        }

        return Integer.toString(counter);
    }


    // 958
    public static String answerTwo(Stream<String> input) {
        Map<Position, Character> map = new HashMap<>();
        List<String> list = input.toList();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            char[] arr = s.toCharArray();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != '.') {
                    map.put(new Position(i, j), arr[j]);
                }
            }
        }
        int counter = 0;
        for (Character c : new HashSet<>(map.values())) {
            List<Position> positions = map.entrySet().stream().filter(e -> e.getValue().equals(c)).map(Map.Entry::getKey).toList();
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position a = positions.get(i);
                    Position b = positions.get(j);
                    int abx = b.x - a.x;
                    int aby = b.y - a.y;
                    int r = 1;
                    while (true) {
                        Position aAntinode = new Position(a.x + r * abx, a.y + r * aby);
                        if (aAntinode.x >= 0 && aAntinode.x < list.size() && aAntinode.y >= 0 && aAntinode.y < list.get(0).length()) {
//                            if (!map.containsKey(aAntinode)) {
                                counter++;
//                            }
                        } else {
                            break;
                        }
                        r++;
                    }
                    r=-1;
                    while (true) {
                        Position aAntinode = new Position(a.x + r * abx, a.y + r * aby);
                        if (aAntinode.x >= 0 && aAntinode.x < list.size() && aAntinode.y >= 0 && aAntinode.y < list.get(0).length()) {
//                            if (!map.containsKey(aAntinode)) {
                                counter++;
//                            }
                        } else {
                            break;
                        }
                        r--;
                    }
                }
            }
        }

        return Integer.toString(counter);
    }

    @Override
    public String answerOne() {
        return answerOne(input());
    }

    @Override
    public String answerTwo() {
        return answerTwo(input());
    }

}

class Position implements Comparable<Position> {
    final public int x;
    final public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Position o) {
        if (this.x == o.x) {
            return this.y - o.y;
        } else {
            return this.x - o.x;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}