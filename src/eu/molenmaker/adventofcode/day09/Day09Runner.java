package eu.molenmaker.adventofcode.day09;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day09Runner {
    HashSet<Long> rainbowTable = new HashSet<>();

    void validateFile(List<Long> numbers, int preambleSize) {
        int size = numbers.size() - preambleSize;

        for (int validationIx = 0; validationIx < size; validationIx++) {
            long invalidNr =  validateNr(numbers, preambleSize, validationIx);
            if (invalidNr > 0) {
                System.out.println(String.format("Found invalid nr: %d", invalidNr));
                findWeakness(numbers, invalidNr);
                return;
            }
        }
    }


    void findWeakness(List<Long> numbers, long sumToFind) {

        for (int setStart = 0; setStart < numbers.size(); setStart++) {
            long smallest = Long.MAX_VALUE;
            long largest = 0;
            long setSum = 0;
            for (int setEnd = setStart; setSum < sumToFind; setEnd++) {
                smallest = Long.min(smallest, numbers.get(setEnd));
                largest = Long.max(largest, numbers.get(setEnd));
                setSum += numbers.get(setEnd);
                if (setSum == sumToFind) {
                    System.out.println(String.format("Contiguous set found: %d + %d = %d", smallest, largest, (smallest + largest)));
                    return;
                }
            }
        }
    }

    private long validateNr(List<Long> numbers, int preambleSize, int validationIx) {
        long nrToCheck = numbers.get(validationIx + preambleSize);

        for (int i=validationIx; i < (validationIx + preambleSize); i++) {
            for (int x= i+1; x < (validationIx + preambleSize); x++) {
                long sum = numbers.get(i) + numbers.get(x);
                if (sum == nrToCheck) {
                    return -1;
                }
            }
        }
        return nrToCheck;
    }

    public static void main(String[] args) throws IOException {
        processFile("data/day09/day_09_test1.txt", 5);
        processFile("data/day09/day_09_input.txt", 25);
    }

    private static void processFile(String fileName, int preambleSize) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        ArrayList<Long> nrs = new ArrayList<>();
        for (String s : lines) {
            nrs.add(Long.valueOf(s));
        }
        new Day09Runner().validateFile(nrs, preambleSize);
    }
}