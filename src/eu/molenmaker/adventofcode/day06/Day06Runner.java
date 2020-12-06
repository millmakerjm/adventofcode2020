package eu.molenmaker.adventofcode.day06;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Day06Runner {
    public static void main(String[] args) throws IOException {
        processFile("data/day06/day_06_test1.txt");
        processFile("data/day06/day_06_input.txt");
    }
     private static void processFile(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        ArrayList<Long> seatIds = new ArrayList<>();
        ArrayList<String> groupAnswers = new ArrayList<>();
        long answerCount = 0;
        for (String line: lines) {
            if (StringUtils.isEmpty(line)) {
                answerCount += processGroup(groupAnswers);
                groupAnswers = new ArrayList<>();
            } else {
                groupAnswers.add(line);
            }
        }
        answerCount += processGroup(groupAnswers);
        System.out.println(String.format("Answer %d", answerCount));


//        System.out.println(String.format("Highest Seat ID: %d", high));
    }

    private static long processGroupPart1(ArrayList<String> groupAnswers) {
        HashSet<Character> uniqueAnswers = new HashSet<>();
        for (String line: groupAnswers) {
            for (char c:line.toCharArray()) {
                if (!uniqueAnswers.contains(c)) {
                    uniqueAnswers.add(c);
                }
            }
        }
        System.out.println(String.format("Group with %d answers", uniqueAnswers.size()));
        return uniqueAnswers.size();
    }

    private static long processGroup(ArrayList<String> groupAnswers) {
        HashSet<Character> allAnswers = new HashSet<>();
        boolean firstLine = true;

        for (char c:groupAnswers.get(0).toCharArray()) {
            allAnswers.add(c);
        }
        for (String line: groupAnswers) {
            HashSet<Character> newAllAnswers = new HashSet<>();
            for (char c:line.toCharArray()) {
                if (allAnswers.contains(c)) {
                    newAllAnswers.add(c);
                }
            }
            allAnswers = newAllAnswers;
        }
        System.out.println(String.format("Group with %d common answers", allAnswers.size()));
        return allAnswers.size();
    }

}