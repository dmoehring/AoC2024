package xyz.domknuddle.aoc.adventofcode2024.day06;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day06Ugly implements Day06 {

    public static void main(String[] args) {
        Day06 day = new Day06Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        Map<Position, Character> map = new HashMap<>();
        List<String> list = input.collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                map.put(new Position(i, j), chars[j]);
            }
        }

        Position pointer = map.entrySet().stream().filter(e -> e.getValue() == '^').findFirst().orElseThrow().getKey();
        int dir = 0;
        map.compute(pointer, (k, v) -> 'X');
        while (isOnField(pointer, map, dir)) {
            if (map.get(pointer.next(dir)) == '#') {
                dir = (dir + 1) % 4;
            }
            pointer = pointer.next(dir);
            map.compute(pointer, (k, v) -> 'X');
        }
        return Integer.toString(Collections.frequency(map.values(), 'X'));

    }

    //between 1804 and 2083
    public static String answerTwo(Stream<String> input) {
        Map<Position, Character> map = new HashMap<>();
        List<String> list = input.collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                map.put(new Position(i, j), chars[j]);
            }
        }
        int counter = 0;
        Position pointer = map.entrySet().stream().filter(e -> e.getValue() == '^').findFirst().orElseThrow().getKey();
        int dir = 0;
        map.compute(pointer, (k, v) -> 'X');
        while (isOnField(pointer, map, dir)) {
            if (map.get(pointer.next(dir)) == '#') {
                dir = (dir + 1) % 4;
            } else {
                if (nextIsPositionsForAnObstruction(pointer, map, (dir + 1) % 4)) {
                    counter++;
                }
            }
            pointer = pointer.next(dir);
            if (pointer.x == 34 && pointer.y == 42) {
                System.out.println("hier");
            }
        }
        return Integer.toString(counter);

    }

    // Prüft, ob ein Zyklus (eine geschlossene Schleife) existiert
    private static boolean nextIsPositionsForAnObstruction(final Position position, Map<Position, Character> map, int direction) {
        Map<Integer, Set<Position>> positions = new HashMap<>();
        positions.put(0, new HashSet<>());
        positions.put(1, new HashSet<>());
        positions.put(2, new HashSet<>());
        positions.put(3, new HashSet<>());

        Position pointer = position;

        // Bewegt sich entlang der Richtung, bis eine Schleife entdeckt wird
        while (isOnField(pointer, map, direction) && !positions.get(direction).contains(pointer)) {
            if (map.get(pointer.next(direction)) == '#') {
                direction = (direction + 1) % 4; // Dreht nach rechts
            }
            positions.get(direction).add(pointer);
            pointer = pointer.next(direction);

            if (pointer.equals(position)) { // Zyklus gefunden
                return true;
            }
        }
        return false;
    }

    // Prüft, ob die nächste Position innerhalb des Spielfelds liegt
    private static boolean isOnField(Position pointer, Map<Position, Character> map, int dir) {
        Position nextPosition = pointer.next(dir);
        if (map.containsKey(nextPosition)) {
            if (map.get(nextPosition) == '#') {
                return isOnField(pointer, map, (dir + 1) % 4); // Dreht nach rechts, wenn Wand
            }
            return true; // Nächste Position ist gültig
        }
        return false; // Außerhalb des Felds
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

    public Position next(int i) {
        switch (i) {
            case 0:
                return goUp();
            case 1:
                return goRight();
            case 2:
                return goDown();
            case 3:
                return goLeft();
        }
        return null;
    }

    public Position goUp() {
        return new Position(x - 1, y);
    }

    public Position goDown() {
        return new Position(x + 1, y);
    }

    public Position goLeft() {
        return new Position(x, y - 1);
    }

    public Position goRight() {
        return new Position(x, y + 1);
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