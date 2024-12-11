package xyz.domknuddle.aoc.adventofcode2024.day10;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10Ugly implements Day10 {

    public static void main(String[] args) {
        Day10 day = new Day10Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        Map<Position, Integer> fullMap = new HashMap<>();
        List<String> list = input.toList();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            char[] arr = s.toCharArray();
            for (int j = 0; j < arr.length; j++) {
                fullMap.put(new Position(i, j), Integer.parseInt(arr[j] + ""));
            }
        }

        long sum = 0L;

        List<Position> startingMap = fullMap.entrySet().stream().filter(e->e.getValue() == 0).map(Map.Entry::getKey).toList();
        for (Position p : startingMap) {
            Set<Position> endPositions = new HashSet<>();
            Set<Position> positions = new HashSet<>();
            positions.add(p);
            while (!positions.isEmpty()) {
                Position actualPosition = getPositionWithSmalestHeight (positions, fullMap);
                positions.remove(actualPosition);
                if (fullMap.get(actualPosition) == 9) {
                    endPositions.add(actualPosition);
                } else {
                    positions.addAll(actualPosition.nextPossiblePositions(fullMap));
                }
            }
            sum += endPositions.size();
        }
        return Long.toString(sum);
    }


    public static String answerTwo(Stream<String> input) {
        Map<Position, Integer> fullMap = new HashMap<>();
        List<String> list = input.toList();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            char[] arr = s.toCharArray();
            for (int j = 0; j < arr.length; j++) {
                fullMap.put(new Position(i, j), Integer.parseInt(arr[j] + ""));
            }
        }
        List<Position> startingMap = fullMap.entrySet().stream().filter(e->e.getValue() == 0).map(Map.Entry::getKey).toList();
        //TODO
        return "";
    }

    private static Position getPositionWithSmalestHeight(Set<Position> positions, Map<Position, Integer> fullMap) {
        Map<Position, Integer> map = positions.stream().collect(Collectors.toMap(e->e, fullMap::get));
        int min = 10;
        Position minPos = null;
        for (Map.Entry<Position, Integer> entry : map.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                minPos = entry.getKey();
            }
        }
        if (minPos == null) {
            throw new RuntimeException();
        }
        return minPos;
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

    public Set<Position> nextPossiblePositions(Map<Position, Integer> fullMap) {
        int level = fullMap.get(this);
        Set<Position> nextPossiblePositions = new HashSet<>();
        Position left = new Position(x , y-1);
        if (fullMap.containsKey(left) && fullMap.get(left) == level+1) {
            nextPossiblePositions.add(left);
        }
        Position right = new Position(x, y+1);
        if (fullMap.containsKey(right) && fullMap.get(right) == level+1) {
            nextPossiblePositions.add(right);
        }
        Position up = new Position(x - 1, y);
        if (fullMap.containsKey(up) && fullMap.get(up) == level+1) {
            nextPossiblePositions.add(up);
        }
        Position down = new Position(x + 1, y);
        if (fullMap.containsKey(down) && fullMap.get(down) == level+1) {
            nextPossiblePositions.add(down);
        }
        return nextPossiblePositions;
    }
}