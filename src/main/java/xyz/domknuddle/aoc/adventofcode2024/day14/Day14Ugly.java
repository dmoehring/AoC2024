package xyz.domknuddle.aoc.adventofcode2024.day14;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day14Ugly implements Day14 {

    public static int width = 101;
    public static int height = 103;

    public static void main(String[] args) {
        Day14 day = new Day14Ugly();

//        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<Robot> robots = input.map(Robot::new).toList();
        printRobots(robots);
        System.out.println();
        for (int i = 1; i <= 100; i++) {
            robots.forEach(e -> e.go(width, height));
            System.out.println("After " + i + " seconds:");
            printRobots(robots);
            System.out.println();
        }
        long[] arr = new long[4];
        arr[0] = robots.stream().filter(e -> e.p.numberOfTilesFromTheLeftWall < width / 2 && e.p.numberOfTilesFromTheTopWall < height / 2).count();
        arr[1] = robots.stream().filter(e -> e.p.numberOfTilesFromTheLeftWall > width / 2 && e.p.numberOfTilesFromTheTopWall < height / 2).count();
        arr[2] = robots.stream().filter(e -> e.p.numberOfTilesFromTheLeftWall < width / 2 && e.p.numberOfTilesFromTheTopWall > height / 2).count();
        arr[3] = robots.stream().filter(e -> e.p.numberOfTilesFromTheLeftWall > width / 2 && e.p.numberOfTilesFromTheTopWall > height / 2).count();
        return Long.toString(arr[0] * arr[1] * arr[2] * arr[3]);
    }

    public static String answerTwo(Stream<String> input) {
        List<Robot> robots = input.map(Robot::new).toList();
        printRobots(robots);
        System.out.println();
        int i = 7708;
        boolean isATree = false;
        do {
            i++;
            final int I = i;
            Set<Position> positions = robots.stream().map(e -> e.getAfterISteps(I, width, height)).collect(Collectors.toSet());
            System.out.println("After " + i + " seconds:");
            printPosition(positions);
            System.out.println();
            if (isAFrame(positions)) {
                isATree = true;
            }
        } while (!isATree);
        return Integer.toString(i);
    }

    private static boolean isAFrame(Set<Position> collect) {
        for (Position po : collect) {
            Position pointer = po;
            while (collect.contains(new Position(pointer.numberOfTilesFromTheLeftWall + 1, pointer.numberOfTilesFromTheTopWall))) {
                pointer = new Position(pointer.numberOfTilesFromTheLeftWall + 1, pointer.numberOfTilesFromTheTopWall);
            }
            while (collect.contains(new Position(pointer.numberOfTilesFromTheLeftWall, pointer.numberOfTilesFromTheTopWall + 1))) {
                pointer = new Position(pointer.numberOfTilesFromTheLeftWall, pointer.numberOfTilesFromTheTopWall + 1);
            }
            while (collect.contains(new Position(pointer.numberOfTilesFromTheLeftWall - 1, pointer.numberOfTilesFromTheTopWall))) {
                pointer = new Position(pointer.numberOfTilesFromTheLeftWall - 1, pointer.numberOfTilesFromTheTopWall);
            }
            while (collect.contains(new Position(pointer.numberOfTilesFromTheLeftWall, pointer.numberOfTilesFromTheTopWall - 1))) {
                pointer = new Position(pointer.numberOfTilesFromTheLeftWall, pointer.numberOfTilesFromTheTopWall - 1);
            }
            while (collect.contains(new Position(pointer.numberOfTilesFromTheLeftWall + 1, pointer.numberOfTilesFromTheTopWall))) {
                pointer = new Position(pointer.numberOfTilesFromTheLeftWall + 1, pointer.numberOfTilesFromTheTopWall);
                if (pointer == po) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printRobots(Collection<Robot> robots) {
        printPosition(robots.stream().map(e -> e.p).collect(Collectors.toSet()));
    }

    private static void printPosition(Set<Position> robots) {
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                final int I = i;
                final int J = j;
                long l = robots.stream().filter(e -> e.numberOfTilesFromTheTopWall == I && e.numberOfTilesFromTheLeftWall == J).count();
                if (l == 0) {
                    System.out.print(".");
                } else if (l <= 9) {
                    System.out.print(l);
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
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

class Robot {
    public Position p;
    public int movingRight;
    public int movingDown;

    public Robot(String input) {
        String regexString = "p=(\\d+),(\\d+)\\s+v=(-?\\d+),(-?\\d+)";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            p = new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            movingRight = Integer.parseInt(matcher.group(3));
            movingDown = Integer.parseInt(matcher.group(4));
        } else {
            throw new RuntimeException();
        }
    }

    public Position getAfterISteps(final int i, final int WIDTH, final int HEIGHT) {
        return new Position(((p.numberOfTilesFromTheTopWall + i % HEIGHT * movingDown) % HEIGHT + HEIGHT) % HEIGHT,
                ((p.numberOfTilesFromTheLeftWall + i % WIDTH * movingRight) % WIDTH + WIDTH) % WIDTH);
    }

    public void go(final int WIDTH, final int HEIGHT) {
        int newNumberOfTilesFromTheTopWall = (p.numberOfTilesFromTheTopWall + movingDown + HEIGHT) % HEIGHT;
        int newNumberOfTilesFromTheLeftWall = (p.numberOfTilesFromTheLeftWall + movingRight + WIDTH) % WIDTH;
        Position newP = new Position(newNumberOfTilesFromTheLeftWall, newNumberOfTilesFromTheTopWall);
//        System.out.println(p + " --- (" + movingRight + "," + movingDown + ") ---> " + newP);
        p = newP;
    }
}

class Position {
    public int numberOfTilesFromTheLeftWall;
    public int numberOfTilesFromTheTopWall;

    public Position(int x, int y) {
        this.numberOfTilesFromTheLeftWall = x;
        this.numberOfTilesFromTheTopWall = y;
    }

    @Override
    public String toString() {
        return numberOfTilesFromTheLeftWall + " " + numberOfTilesFromTheTopWall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return numberOfTilesFromTheLeftWall == position.numberOfTilesFromTheLeftWall && numberOfTilesFromTheTopWall == position.numberOfTilesFromTheTopWall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfTilesFromTheLeftWall, numberOfTilesFromTheTopWall);
    }
}

