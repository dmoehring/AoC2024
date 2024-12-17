package xyz.domknuddle.aoc.adventofcode2024.day15;

import org.apache.commons.lang3.NotImplementedException;
import xyz.domknuddle.aoc.adventofcode2024.util.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class Day15Ugly implements Day15 {

    public static void main(String[] args) {
        Day15 day = new Day15Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<String> inputList = input.toList();
        List<String> mapInputList = new ArrayList<>();
        List<String> movesInputList = new ArrayList<>();
        boolean readMap = true;
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).isEmpty()) {
                readMap = false;
            } else {
                if (readMap) {
                    mapInputList.add(inputList.get(i));
                } else {
                    movesInputList.add(inputList.get(i));
                }
            }
        }
        Map<Position, Character> map = new HashMap<>();
        for (int i = 0; i < mapInputList.size(); i++) {
            for (int j = 0; j < mapInputList.get(i).length(); j++) {
                char c = mapInputList.get(i).charAt(j);
                if (c != '.') {
                    map.put(new Position(i, j), c);
                }
            }
        }
        printMap(map);

        for (int i = 0; i < movesInputList.size(); i++) {
            for (int j = 0; j < movesInputList.get(i).length(); j++) {
                char c = movesInputList.get(i).charAt(j);
                System.out.println("Move " + c + ":");
                printMap(map);
                if (canGoPartOne(map, c)) {
                    goPartOne(map, c);
                }
            }
        }

        return Long.toString(calculatePartOne(map));
    }

    public static String answerTwo(Stream<String> input) {
        List<String> inputList = input.toList();
        List<String> mapInputList = new ArrayList<>();
        List<String> movesInputList = new ArrayList<>();
        boolean readMap = true;
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).isEmpty()) {
                readMap = false;
            } else {
                if (readMap) {
                    mapInputList.add(inputList.get(i));
                } else {
                    movesInputList.add(inputList.get(i));
                }
            }
        }
        Map<Position, Character> map = new HashMap<>();
        for (int i = 0; i < mapInputList.size(); i++) {
            for (int j = 0; j < mapInputList.get(i).length(); j++) {
                char c = mapInputList.get(i).charAt(j);
                switch (c) {
                    case '#':
                        map.put(new Position(i, j*2), '#');
                        map.put(new Position(i, j*2+1), '#');
                        break;
                    case 'O':
                        map.put(new Position(i, j*2), '[');
                        map.put(new Position(i, j*2+1), ']');
                        break;
                    case '@':
                        map.put(new Position(i, j*2), '@');
                        break;
                    case '.':
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + c);
                }
            }
        }

        for (int i = 0; i < movesInputList.size(); i++) {
            for (int j = 0; j < movesInputList.get(i).length(); j++) {
                char c = movesInputList.get(i).charAt(j);
                if (canGoPartTwo(map, c)) {
                    goPartTwo(map, c);
                }
            }
        }

        return Long.toString(calculatePartTwo(map));
    }

    private static long calculatePartOne(Map<Position, Character> map) {
        return map.entrySet().stream().filter(e -> e.getValue() == 'O').map(Map.Entry::getKey).mapToLong(e -> e.i * 100L + e.j).sum();
    }

    private static long calculatePartTwo(Map<Position, Character> map) {
        return map.entrySet().stream().filter(e -> e.getValue() == '[').map(Map.Entry::getKey).mapToLong(e -> e.i * 100L + e.j).sum();
    }

    private static void printMap(Map<Position, Character> map) {
        int maxI = map.keySet().stream().mapToInt(e -> e.i).max().orElseThrow();
        int maxJ = map.keySet().stream().mapToInt(e -> e.j).max().orElseThrow();
        for (int i = 0; i <= maxI; i++) {
            for (int j = 0; j <= maxJ; j++) {
                char c = map.getOrDefault(new Position(i, j), '.');
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void goPartTwo(Map<Position, Character> map, char c) {
        throw new NotImplementedException();
    }

    private static void goPartOne(Map<Position, Character> map, char c) {
        Position p = map.entrySet().stream().filter(e -> e.getValue() == '@').findFirst().orElseThrow().getKey();
        map.remove(p);
        Position emptyPosition = null;
        int i = 1;
        switch (c) {
            case '^':
                while (true) {
                    if (!map.containsKey(new Position(p.i - i, p.j))) {
                        emptyPosition = new Position(p.i - i, p.j);
                        break;
                    }
                    i++;
                }
                map.put(emptyPosition, 'O');
                map.replace(new Position(p.i - 1, p.j), '@');
                break;
            case 'v':
                while (true) {
                    if (!map.containsKey(new Position(p.i + i, p.j))) {
                        emptyPosition = new Position(p.i + i, p.j);
                        break;
                    }
                    i++;
                }
                map.put(emptyPosition, 'O');
                map.replace(new Position(p.i + 1, p.j), '@');
                break;
            case '<':
                while (true) {
                    if (!map.containsKey(new Position(p.i, p.j - i))) {
                        emptyPosition = new Position(p.i, p.j - i);
                        break;
                    }
                    i++;
                }
                map.put(emptyPosition, 'O');
                map.replace(new Position(p.i, p.j - 1), '@');
                break;
            case '>':
                while (true) {
                    if (!map.containsKey(new Position(p.i, p.j + i))) {
                        emptyPosition = new Position(p.i, p.j + i);
                        break;
                    }
                    i++;
                }
                map.put(emptyPosition, 'O');
                map.replace(new Position(p.i, p.j + 1), '@');
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }

    }

    private static boolean canGoPartTwo(Map<Position, Character> map, char c) {
        Position p = map.entrySet().stream().filter(e -> e.getValue() == '@').findFirst().orElseThrow().getKey();
        Position pNext = p;
        switch (c) {
            case '^':
            case 'v':
                throw new NotImplementedException();
            case '<':
                while (map.containsKey(pNext)) {
                    if (map.get(pNext) == '#') {
                        return false;
                    }
                    pNext = new Position(pNext.i, pNext.j - 1);
                }
                return true;
            case '>':
                while (map.containsKey(pNext)) {
                    if (map.get(pNext) == '#') {
                        return false;
                    }
                    pNext = new Position(pNext.i, pNext.j + 1);
                }
                return true;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    private static boolean canGoPartOne(Map<Position, Character> map, char c) {
        Position p = map.entrySet().stream().filter(e -> e.getValue() == '@').findFirst().orElseThrow().getKey();
        Position pNext = p;
        switch (c) {
            case '^':
                while (map.containsKey(pNext)) {
                    if (map.get(pNext) == '#') {
                        return false;
                    }
                    pNext = new Position(pNext.i - 1, pNext.j);
                }
                return true;
            case 'v':
                while (map.containsKey(pNext)) {
                    if (map.get(pNext) == '#') {
                        return false;
                    }
                    pNext = new Position(pNext.i + 1, pNext.j);
                }
                return true;
            case '<':
                while (map.containsKey(pNext)) {
                    if (map.get(pNext) == '#') {
                        return false;
                    }
                    pNext = new Position(pNext.i, pNext.j - 1);
                }
                return true;
            case '>':
                while (map.containsKey(pNext)) {
                    if (map.get(pNext) == '#') {
                        return false;
                    }
                    pNext = new Position(pNext.i, pNext.j + 1);
                }
                return true;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
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