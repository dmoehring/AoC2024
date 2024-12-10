package xyz.domknuddle.aoc.adventofcode2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day09Ugly implements Day09 {

    public static void main(String[] args) {
        Day09 day = new Day09Ugly();

        System.out.println("Answer 1: " + day.answerOne());
        System.out.println("Answer 2: " + day.answerTwo());
    }

    public static String answerOne(Stream<String> input) {
        return partOne(input.toList().get(0));
    }

    public static String answerTwo(Stream<String> input) {
        List<String> inputList = input.toList();
        if (inputList.size()!=1) {
            throw new RuntimeException();
        }
        String inputString = inputList.get(0);
        
        List<AbstractAmphipod> list = new ArrayList<>();
        for (int index = 0; index < inputString.length(); index++) {
            char ch = inputString.charAt(index);
            if (index % 2 == 0) {
                list.add(new AmphipodFile( Integer.parseInt(ch + ""), index / 2));
            } else {
                list.add(new AmphipodFreeSpace(Integer.parseInt(ch + "")));
            }
        }
//        System.out.println(inputString);
//        System.out.println(lengthOfListToString(list));
//        System.out.println(visualList(list));

        int maxId = list.stream().filter(AmphipodFile.class::isInstance).mapToInt(e -> ((AmphipodFile) e).id).max().getAsInt();
        for (int idPointer = maxId; idPointer > 0; idPointer--) {
            int pos = getLastPosition(list, idPointer);
            AbstractAmphipod item = list.get(pos);

//            System.out.println(((AmphipodFile) item).id + " mit der Länge " + item.length + " an Position " + pos);
            for (int i = 0; i < pos; i++) {
                if (list.get(i) instanceof AmphipodFreeSpace && list.get(i).length >= item.length) {
                    list.get(i).setLength(list.get(i).length - item.length);
                    list.add(i, ((AmphipodFile) item).cloneFile());
                    list.set(getLastPosition(list, idPointer), new AmphipodFreeSpace(item.length));
                    break;
                }
            }
//            System.out.println(visualList(list));
        }

        return Long.toString(calculatePartTwo(list));
    }

    @Override
    public String answerOne() {
        return answerOne(input());
    }

    @Override
    public String answerTwo() {
        return answerTwo(input());
    }

    private static String partOne(String input) {
        List<AbstractAmphipod> amphipods = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            int counting = Integer.parseInt(input.charAt(i) + "");
            if (i % 2 == 0) {
                int id = i / 2;
                for (int j = 0; j < counting; j++) {
                    amphipods.add(new AmphipodFile(1, id));
                }
            } else {
                for (int j = 0; j < counting; j++) {
                    amphipods.add(new AmphipodFreeSpace(1));
                }
            }
        }

        amphipods = reorderPartOne(amphipods);

        return Long.toString(calculatePartOne(amphipods));
    }
    
    private static List<AbstractAmphipod> reorderPartOne(List<AbstractAmphipod> amphipods) {
        for (int i = 0; i < amphipods.size() - 1; i++) {
            if (amphipods.get(i) instanceof AmphipodFreeSpace) {
                for (int j = amphipods.size() - 1; j >= 0; j--) {
                    if (amphipods.get(j) instanceof AmphipodFile) {
                        if (i < j) {
                            amphipods.set(i, amphipods.get(j));
                            amphipods.set(j, new AmphipodFreeSpace(1));
                            break;
                        } else {
                            return amphipods;
                        }
                    }
                }
            }
        }
        return amphipods;
    }

    private static long calculatePartOne(List<AbstractAmphipod> amphipods) {
        long sum = 0;
        for (int i = 0; i < amphipods.size() - 1; i++) {
            if (amphipods.get(i) instanceof AmphipodFile) {
                sum += i * ((AmphipodFile) amphipods.get(i)).getId() * 1L;
            }
        }
        return sum;
    }

    private static long calculatePartTwo(List<AbstractAmphipod> list) {
        long sum = 0;
        long pos = 0;
        for (AbstractAmphipod item : list) {
            for (int i = 0; i < item.length; i++) {
                if (item instanceof AmphipodFile) {
                    sum += pos * ((AmphipodFile) item).id;
                }
                pos++;
            }
        }
        return sum;
    }


    private static int getLastPosition(List<AbstractAmphipod> list, int idPointer) {
        for (int pos = list.size()-1; pos >= 0; pos--) {
            if (list.get(pos) instanceof AmphipodFile && ((AmphipodFile) list.get(pos)).id == idPointer) {
                return pos;
            }
        }
        throw new RuntimeException("No such file or directory");
    }


    private static String visualList(List<AbstractAmphipod> list) {
        StringBuilder sb = new StringBuilder();
        for (AbstractAmphipod item : list) {

            for (int i = 0; i < item.length; i++) {
                sb.append(item.getValue());
            }
        }
        return sb.toString();
    }


    private static String lengthOfListToString(List<AbstractAmphipod> list) {
        StringBuilder sb = new StringBuilder();
        for (AbstractAmphipod item : list) {
            sb.append(item.getLength());
        }
        return sb.toString();
    }



}

abstract class AbstractAmphipod {
    public int length;
    public AbstractAmphipod(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    abstract String getValue();
}

class AmphipodFile extends AbstractAmphipod {
    int id;

    public AmphipodFile(int length, int id) {
        super(length);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AmphipodFile cloneFile() {
        return new AmphipodFile(length, id);
    }


    @Override
    String getValue() {
        return Integer.toString(id);
    }
}

class AmphipodFreeSpace extends AbstractAmphipod {

    public AmphipodFreeSpace(int length) {
        super(length);
    }

    @Override
    String getValue() {
        return ".";
    }
}
