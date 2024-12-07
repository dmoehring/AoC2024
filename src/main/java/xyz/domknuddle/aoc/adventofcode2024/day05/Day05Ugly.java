package xyz.domknuddle.aoc.adventofcode2024.day05;

import xyz.domknuddle.aoc.adventofcode2024.util.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05Ugly implements Day05 {

    @Override
    public String answerOne() {
        return answerOne(input());
    }

    @Override
    public String answerTwo() {
        return answerTwo(input());
    }

    public static void main(String[] args) {
        Day05 day = new Day05Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<PageOrderingRule> pageOrderingRules = new ArrayList<>();
        List<Update> updates = new ArrayList<>();
        input.forEach(e -> {
                    if (e.contains("|")) {
                        String[] split = e.split("\\|");
                        pageOrderingRules.add(new PageOrderingRule(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    } else if (e.isEmpty()) {

                    } else {
                        String[] split = e.split(",");
                        List<Integer> collect = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
                        Update update = new Update(collect);
                        if (update.isCorrect(pageOrderingRules)) {
                            updates.add(update);
                        }
                    }
                }
        );
        int sum = updates.stream().mapToInt(Update::getMiddle).sum();
        return Integer.toString(sum);

    }

    public static String answerTwo(Stream<String> input) {
        List<PageOrderingRule> pageOrderingRules = new ArrayList<>();
        List<Update> updates = new ArrayList<>();
        input.forEach(e -> {
                    if (e.contains("|")) {
                        String[] split = e.split("\\|");
                        pageOrderingRules.add(new PageOrderingRule(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    } else if (e.isEmpty()) {

                    } else {
                        String[] split = e.split(",");
                        List<Integer> collect = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
                        Update update = new Update(collect);
                        if (!update.isCorrect(pageOrderingRules)) {
                            updates.add(update.reorder(pageOrderingRules));
                        }
                    }
                }
        );
        int sum = updates.stream().mapToInt(Update::getMiddle).sum();
        return Integer.toString(sum);
    }

}

class PageOrderingRule {
    private int left;
    private int right;

    public PageOrderingRule(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

}

class Update {
    private List<Integer> pageNumbers;

    public Update(List<Integer> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public boolean isCorrect(List<PageOrderingRule> pageOrderingRules) {
        for (int i = 0; i < pageNumbers.size(); i++) {
            int left = pageNumbers.get(i);
            List<PageOrderingRule> filtered = pageOrderingRules.stream().filter(r -> r.getLeft() == left).collect(Collectors.toList());
            for (int j = i + 1; j < pageNumbers.size(); j++) {
                int right = pageNumbers.get(j);
                if (filtered.stream().noneMatch(e -> e.getRight() == right)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getMiddle() {
        return pageNumbers.get(pageNumbers.size() / 2);
    }

    public Update reorder(List<PageOrderingRule> pageOrderingRules) {
        for (int i = 0; i < pageNumbers.size(); i++) {
            int left = pageNumbers.get(i);
            for (int j = i + 1; j < pageNumbers.size(); j++) {
                int right = pageNumbers.get(j);
                if (pageOrderingRules.stream().noneMatch(e -> e.getLeft() == left && e.getRight() == right)) {
                    pageNumbers.set(i, right);
                    pageNumbers.set(j, left);
                    return reorder(pageOrderingRules);
                }
            }
        }
        return this;
    }
}
