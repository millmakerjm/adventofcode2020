package eu.molenmaker.adventofcode.day10;

import com.google.common.primitives.Longs;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Day10Runner {
    private long[] jolts;
    private long maxJolt;
    private int chainsFound;

    public static void main(String[] args) throws IOException {
        processFile("data/day10/day_10_test1.txt");
        processFile("data/day10/day_10_test2.txt");

        processFile("data/day10/day_10_input.txt");
    }

    private static void processFile(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        ArrayList<Long> jolts = new ArrayList<>();
        for (String s : lines) {
            jolts.add(Long.valueOf(s));
        }
        Collections.sort(jolts);

        long maxJolt = new Day10Runner().solvePuzzlePart1(jolts);
        new Day10Runner().solvePuzzlePart2(jolts, maxJolt);

    }

    private long solvePuzzlePart1(ArrayList<Long> jolts) {
        int[] diffs = new int[4];

        long last = 0;
        for (Long jolt : jolts) {
            int diff = (int) (jolt - last);
            if (diff < 1 || diff > 3) {
                throw new IllegalStateException(String.format("Unexpected diff %d - %d = %d", jolt, last, diff));
            }
            diffs[diff]++;
            last = jolt;
        }
        diffs[3]++;
        System.out.println(String.format("1 jolt diff: %d, 2 jolt diff: %d, 3 jolt diff: %d", diffs[1], diffs[2], diffs[3]));
        System.out.println(String.format("Puzzle part1 answer: %d", diffs[1] * diffs[3]));
        return last + 3;
    }


    private void solvePuzzlePart2(ArrayList<Long> jolts, long maxJolt) {
        System.out.println(String.format("Finding chain to: %d", maxJolt));

        //this.jolts = jolts;
        this.maxJolt = maxJolt - 3;
        this.chainsFound = 0;
        this.jolts = Longs.toArray(jolts);

        walkChain(0, 0);
        System.out.println(String.format("Puzzle part2 answer: %d", chainsFound));
    }

    private void walkChain(int ix, long prevAdapter) {
         for (int i=0; i<3; i++) {
             if (ix + i < jolts.length) {
                 long adapter = jolts[ix+i];
                 if (adapter > maxJolt || (adapter - prevAdapter <= 3 && adapter - prevAdapter >= 1)) {
                     if (adapter == maxJolt) {
                         chainsFound++;
                     } else {
                         walkChain(ix + i + 1, adapter);
                     }
                 }
             }
        }
    }
}