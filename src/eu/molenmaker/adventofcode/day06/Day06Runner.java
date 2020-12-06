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
                uniqueAnswers.add(c);
            }
        }
        System.out.println(String.format("Group with %d answers", uniqueAnswers.size()));
        return uniqueAnswers.size();
    }

    private static long processGroup(ArrayList<String> groupAnswers) {
        HashSet<Character> commonAnswers = new HashSet<>();

        // Assume the first row contains all common answers
        for (char c:groupAnswers.get(0).toCharArray()) {
            commonAnswers.add(c);
        }

        // Loop through all answers per person in the group and if their answers are found in the list with
        // common answers add it to a new set of common answers and make that set the new list of common answers
        for (String personalAnswers: groupAnswers) {
            HashSet<Character> newCommonAnswers = new HashSet<>();
            for (char c:personalAnswers.toCharArray()) {
                if (commonAnswers.contains(c)) {
                    newCommonAnswers.add(c);
                }
            }
            // What remains in new common answers are only the answes common to the set and the answers of
            // the current person
            commonAnswers = newCommonAnswers;
        }
        System.out.println(String.format("Group with %d common answers", commonAnswers.size()));
        return commonAnswers.size();
    }

}