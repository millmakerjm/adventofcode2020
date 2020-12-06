package eu.molenmaker.adventofcode.day05;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day05Runner {
    public static void main(String[] args) throws IOException {
        processFile("data/day05/day_05_test1.txt");
        processFile("data/day05/day_05_input.txt");
    }
     private static void processFile(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        long high = 0;
        ArrayList<Long> seatIds = new ArrayList<>();
        for (String line: lines) {
            long seatId = parseLine(line);
            System.out.println(String.format("Seat ID: %s = %d", line, seatId));
            if (seatId > high) high = seatId;
            seatIds.add(seatId);
        }
        System.out.println(String.format("Highest Seat ID: %d", high));

        Collections.sort(seatIds);
        long last = seatIds.get(0) - 1L;
        for (Long id : seatIds) {
            if (id - 1 != last) {
                System.out.println(String.format("Missing Seat ID: %d", id-1));
            }
            System.out.println(String.format("        Seat ID: %d", id));


            last = id;
        }

    }

    private static long parseLine(String line) {
        String rowId = StringUtils.left(line, 7);
        rowId = StringUtils.replaceChars(rowId, 'F', '0');
        rowId = StringUtils.replaceChars(rowId, 'B', '1');
        long row = Long.parseLong(rowId, 2);

        String seatId = StringUtils.right(line, 3) ;
        seatId = StringUtils.replaceChars(seatId, 'L', '0');
        seatId = StringUtils.replaceChars(seatId, 'R', '1');
        long seat = Long.parseLong(seatId, 2);

        //System.out.println(String.format("Row: %d, Seat: %d, %s", row, seat, line));
        return row*8 + seat;
    }
}