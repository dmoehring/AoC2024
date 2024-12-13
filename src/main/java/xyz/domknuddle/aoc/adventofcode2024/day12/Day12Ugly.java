package xyz.domknuddle.aoc.adventofcode2024.day12;

import xyz.domknuddle.aoc.adventofcode2024.util.Position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12Ugly implements Day12 {

    public static void main(String[] args) {
        Day12 day = new Day12Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        Map<Position, Character> map = Position.inputToMap(input);
        List<SortedSet<Position>> clusters = new ArrayList<>();
        SortedSet<Character> characters = new TreeSet<>(map.values());
        for (Character character : characters) {
            NavigableSet<Position> set = map.entrySet().stream().filter(e -> Objects.equals(e.getValue(), character)).map(Map.Entry::getKey).collect(Collectors.toCollection(TreeSet::new));
            while (!set.isEmpty()) {
                Position position = set.first();
                set.remove(position);

                SortedSet<Position> cluster = new TreeSet<>();
                cluster.add(position);
                int size;
                do {
                    size = cluster.size();
                    Set<Position> newSet = new HashSet<>(cluster);
                    for (Position p : cluster) {
                        removeAndAddIfExists(set, new Position(p.i - 1, p.j), newSet);
                        removeAndAddIfExists(set, new Position(p.i + 1, p.j), newSet);
                        removeAndAddIfExists(set, new Position(p.i, p.j + 1), newSet);
                        removeAndAddIfExists(set, new Position(p.i, p.j - 1), newSet);
                    }
                    cluster.addAll(newSet);
                } while (cluster.size() > size);
                clusters.add(cluster);
            }
        }
        long sum = clusters.stream().mapToLong(e -> (long) calculateArea(e) * calculatePerimeter(e)).sum();
        return Long.toString(sum);
    }


    public static String answerTwo(Stream<String> input) {
        Map<Position, Character> map = Position.inputToMap(input);
        List<SortedSet<Position>> clusters = new ArrayList<>();
        SortedSet<Character> characters = new TreeSet<>(map.values());
        for (Character character : characters) {
            NavigableSet<Position> set = map.entrySet().stream().filter(e -> Objects.equals(e.getValue(), character)).map(Map.Entry::getKey).collect(Collectors.toCollection(TreeSet::new));
            while (!set.isEmpty()) {
                Position position = set.first();
                set.remove(position);

                SortedSet<Position> cluster = new TreeSet<>();
                cluster.add(position);
                int size;
                do {
                    size = cluster.size();
                    Set<Position> newSet = new HashSet<>(cluster);
                    for (Position p : cluster) {
                        removeAndAddIfExists(set, new Position(p.i - 1, p.j), newSet);
                        removeAndAddIfExists(set, new Position(p.i + 1, p.j), newSet);
                        removeAndAddIfExists(set, new Position(p.i, p.j + 1), newSet);
                        removeAndAddIfExists(set, new Position(p.i, p.j - 1), newSet);
                    }
                    cluster.addAll(newSet);
                } while (cluster.size() > size);
                clusters.add(cluster);
            }
        }
        long sum = clusters.stream().mapToLong(e -> (long) calculateArea(e) * calcualteSides(e)).sum();
        return Long.toString(sum);
    }


    private static int calculatePerimeter(Set<Position> cluster) {
        int perimeter = 0;
        for (Position p : cluster) {
            if (!cluster.contains(new Position(p.i - 1, p.j))) {
                perimeter++;
            }
            if (!cluster.contains(new Position(p.i + 1, p.j))) {
                perimeter++;
            }
            if (!cluster.contains(new Position(p.i, p.j + 1))) {
                perimeter++;
            }
            if (!cluster.contains(new Position(p.i, p.j - 1))) {
                perimeter++;
            }
        }
        return perimeter;
    }

    private static int calculateArea(Set<Position> cluster) {
        return cluster.size();
    }

    private static int calcualteSides(SortedSet<Position> cluster) {
        return 0;
    }

    private static void removeAndAddIfExists(NavigableSet<Position> set, Position newPos, Set<Position> newSet) {
        if (set.remove(newPos)) {
            newSet.add(newPos);
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