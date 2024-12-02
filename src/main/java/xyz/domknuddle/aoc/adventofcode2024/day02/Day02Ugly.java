package xyz.domknuddle.aoc.adventofcode2024.day02;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day02Ugly implements Day02 {

    public static void main(String[] args) {
        Day02 day = new Day02Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        return Integer.toString(input.mapToInt(inputLevel -> {
            String[] s = inputLevel.split(" ");
            int[] arr = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
            return safeLevel(arr);
        }).sum());
    }

    public static String answerTwo(Stream<String> input) {
        return Integer.toString(input.mapToInt(
                inputLevel -> {
                    String[] s = inputLevel.split(" ");
                    int[] arr = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
                    if (safeLevel(arr) == 1) {
                        return 1;
                    }
                    for (int i = 0; i < arr.length; i++) {
                        int[] subArr = subArray(arr, i);
                        if (safeLevel(subArr) == 1) {
                            return 1;
                        }
                    }
                    return 0;
                }
        ).sum());
    }


    private static int[] subArray(int[] arr, int index) {
        int[] subArr = new int[arr.length - 1];
        for (int j = 0; j < arr.length; j++) {
            int i = j;
            if (i == index) {

            } else {
                if (i > index) {
                    i = i - 1;
                }
                subArr[i] = arr[j];
            }
        }
        return subArr;
    }

    private static int safeLevel(int[] arr) {
        int[] diffArr = initializeDiffArray(arr);
        for (int j : diffArr) {
            if (j == 0 || Math.abs(j) > 3) {
                return 0;
            }
        }
        int sum = Arrays.stream(diffArr).sum();
        int absSum = Arrays.stream(diffArr).map(Math::abs).sum();
        if (Math.abs(sum) == absSum) {
            return 1;
        }
        return 0;
    }

    private static int [] initializeDiffArray(int[] arr) {
        int[] diffArr = new int[arr.length-1];
        for (int i = 0; i < arr.length - 1; i++) {
            diffArr[i] = arr[i] - arr[i + 1];
        }
        return diffArr;
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