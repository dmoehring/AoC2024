package xyz.domknuddle.aoc.adventofcode2024.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day13Ugly implements Day13 {

    public static void main(String[] args) {
        Day13 day = new Day13Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        List<String> inputList = input.toList();
        List<ClawMachine> clawMachines = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i = i + 4) {
            List<String> clawMachinesStr = new ArrayList<>();
            clawMachinesStr.add(inputList.get(i));
            clawMachinesStr.add(inputList.get(i + 1));
            clawMachinesStr.add(inputList.get(i + 2));
            ClawMachine clawMachine = new ClawMachine(clawMachinesStr);
            clawMachines.add(clawMachine);
        }

        return Long.toString(clawMachines.stream().filter(ClawMachine::isVaid).mapToLong(e->e.getBestButtenA()*3 + e.getBestButtenB()*1).sum());
    }

    public static String answerTwo(Stream<String> input) {
        List<String> inputList = input.toList();
        List<ClawMachine> clawMachines = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i = i + 4) {
            List<String> clawMachinesStr = new ArrayList<>();
            clawMachinesStr.add(inputList.get(i));
            clawMachinesStr.add(inputList.get(i + 1));
            clawMachinesStr.add(inputList.get(i + 2));
            ClawMachine clawMachine = new ClawMachine(clawMachinesStr);
            clawMachine.updateForPartTwo();
            clawMachines.add(clawMachine);
        }

        return Long.toString(clawMachines.stream().filter(ClawMachine::isVaid).mapToLong(e->e.getBestButtenA()*3 + e.getBestButtenB()*1).sum());
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

class ClawMachine {
    long x1;
    long y1;
    long x2;
    long y2;
    long x;
    long y;

    public ClawMachine(List<String> input) {
        // Regex für Buttons
        Pattern buttonPattern = Pattern.compile("Button (\\w+): X\\+(\\d+), Y\\+(\\d+)");
        Matcher buttonMatcherA = buttonPattern.matcher(input.get(0));
        Matcher buttonMatcherB = buttonPattern.matcher(input.get(1));

        // Regex für Prize
        Pattern prizePattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
        Matcher prizeMatcher = prizePattern.matcher(input.get(2));

        // Buttons extrahieren
        while (buttonMatcherA.find()) {
            x1 = Long.parseLong(buttonMatcherA.group(2));
            y1 = Long.parseLong(buttonMatcherA.group(3));
        }

        // Buttons extrahieren
        while (buttonMatcherB.find()) {
            x2 = Long.parseLong(buttonMatcherB.group(2));
            y2 = Long.parseLong(buttonMatcherB.group(3));
        }

        // Prize extrahieren
        if (prizeMatcher.find()) {
            x = Long.parseLong(prizeMatcher.group(1));
            y = Long.parseLong(prizeMatcher.group(2));
        }
    }

    public void updateForPartTwo() {
        this.x = x+10000000000000L;
        this.y = y+10000000000000L;
    }

    public long getBestButtenA() {
        return (x - (x * y1 - y * x1) / (x2 * y1 - x1 * y2) * x2) / x1;
    }

    public long getBestButtenB() {
        return (x * y1 - y * x1) / (x2 * y1 - x1 * y2);
    }

    public boolean isVaid() {
        long bestButtenA = this.getBestButtenA();
        long bestButtenB = this.getBestButtenB();
        return (bestButtenA * x1 + bestButtenB * x2 == x && bestButtenA * y1 + bestButtenB * y2 == y);
    }

}