package xyz.domknuddle.aoc.adventofcode2024.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day11Ugly implements Day11 {

    @Override
    public String answerOne() {
        return answerOne(input());
    }

    @Override
    public String answerTwo() {
        return answerTwo(input());
    }

    public static void main(String[] args) {
        Day11 day = new Day11Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<String> inputList = input.toList();
        if (inputList.size()!=1) {
            throw new RuntimeException();
        }
        String inputString = inputList.get(0);
        String[] splitted = inputString.split(" ");
        List<Long> list = new ArrayList<>();
        for (String s:splitted) {
            list.add(Long.parseLong(s));
        }

        for (int i = 1; i<=25; i++) {
            List<Long> newList = new ArrayList<>();
            for (Long stone : list) {
                if (stone == 0) {
                    newList.add(1L);
                }else if (Long.toString(stone).length()%2==0) {
                    String stoneString = Long.toString(stone);
                    long left = Long.parseLong(stoneString.substring(0, stoneString.length()/2));
                    newList.add(left);
                    long right = Long.parseLong(stoneString.substring(stoneString.length()/2));
                    newList.add(right);
                } else {
                    newList.add(2024*stone);
                }
            }
            list = newList;
            System.out.println("Nach " + i + ": Größe " + list.size());
        }
        return Integer.toString(list.size());
    }

    public static String answerTwo(Stream<String> input) {
        List<String> inputList = input.toList();
        if (inputList.size()!=1) {
            throw new RuntimeException();
        }
        String inputString = inputList.get(0);
        String[] splitted = inputString.split(" ");
        List<Long> list = new ArrayList<>();
        for (String s:splitted) {
            list.add(Long.parseLong(s));
        }

        for (int i = 1; i<=75; i++) {
            List<Long> newList = new ArrayList<>();
            for (Long stone : list) {
                if (stone == 0) {
                    newList.add(1L);
                }else if (Long.toString(stone).length()%2==0) {
                    String stoneString = Long.toString(stone);
                    long left = Long.parseLong(stoneString.substring(0, stoneString.length()/2));
                    newList.add(left);
                    long right = Long.parseLong(stoneString.substring(stoneString.length()/2));
                    newList.add(right);
                } else {
                    newList.add(2024*stone);
                }
            }
            list = newList;
            System.out.println("Nach " + i + ": Größe " + list.size());
        }
        return Integer.toString(list.size());
    }

}