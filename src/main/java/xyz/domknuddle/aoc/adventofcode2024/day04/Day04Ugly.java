package xyz.domknuddle.aoc.adventofcode2024.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Day04Ugly implements Day04 {

    public static void main(String[] args) {
        Day04 day = new Day04Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<List<Character>> list = toList(input);
        long counter = 0L;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                counter += isAX(list, i, j);
            }
        }
        return Long.toString(counter);
    }

    public static String answerTwo(Stream<String> input) {
        List<List<Character>> list = toList(input);
        long counter = 0L;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                counter += isAA(list, i, j);
            }
        }
        return Long.toString(counter);
    }


    private static int isAX(List<List<Character>> list, int i, int j) {
        if (list.get(i).get(j) == 'X') {
            return isAXMas(list, i, j);
        }
        return 0;
    }

    private static int isAXMas(List<List<Character>> list, int i, int j) {
        int counter = 0;
        try {
            if (list.get(i - 1).get(j) == 'M'
                    && list.get(i - 2).get(j) == 'A'
                    && list.get(i - 3).get(j) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i + 1).get(j) == 'M'
                    && list.get(i + 2).get(j) == 'A'
                    && list.get(i + 3).get(j) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i).get(j + 1) == 'M'
                    && list.get(i).get(j + 2) == 'A'
                    && list.get(i).get(j + 3) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i).get(j - 1) == 'M'
                    && list.get(i).get(j - 2) == 'A'
                    && list.get(i).get(j - 3) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i + 1).get(j - 1) == 'M'
                    && list.get(i + 2).get(j - 2) == 'A'
                    && list.get(i + 3).get(j - 3) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i - 1).get(j - 1) == 'M'
                    && list.get(i - 2).get(j - 2) == 'A'
                    && list.get(i - 3).get(j - 3) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i + 1).get(j + 1) == 'M'
                    && list.get(i + 2).get(j + 2) == 'A'
                    && list.get(i + 3).get(j + 3) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        try {
            if (list.get(i - 1).get(j + 1) == 'M'
                    && list.get(i - 2).get(j + 2) == 'A'
                    && list.get(i - 3).get(j + 3) == 'S') {
                counter++;
            }
        } catch (Exception e) {
        }
        return counter;

    }

    private static long isAA(List<List<Character>> list, int i, int j) {
        if (list.get(i).get(j) == 'A') {
            return isAX_MAS(list, i, j);
        }
        return 0;
    }

    private static long isAX_MAS(List<List<Character>> list, int i, int j) {
        if ((diagIsMas(list, i, j, 1) || diagIsMas(list, i, j, 3)) && (diagIsMas(list, i, j, 2) || diagIsMas(list, i, j, 4))) {
            return 1;
        }
        return 0;
    }

    private static boolean diagIsMas(List<List<Character>> list, int i, int j, int type) {
        try {
            switch (type) {
                case 1:
                    return list.get(i + 1).get(j + 1) == 'M' && list.get(i - 1).get(j - 1) == 'S';
                case 2:
                    return list.get(i + 1).get(j - 1) == 'M' && list.get(i - 1).get(j + 1) == 'S';
                case 3:
                    return list.get(i - 1).get(j - 1) == 'M' && list.get(i + 1).get(j + 1) == 'S';
                case 4:
                    return list.get(i - 1).get(j + 1) == 'M' && list.get(i + 1).get(j - 1) == 'S';
                default:
                    return false;

            }
        } catch (Exception e) {
            return false;
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

    private static List<List<Character>> toList(Stream<String> input) {
        List<List<Character>> list = new ArrayList<>();
        input.forEach(s -> {
            List<Character> innerList = new ArrayList<>();
            for (char c : s.toCharArray()) {
                innerList.add(c);
            }
            list.add(innerList);
        });
        return list;
    }

}