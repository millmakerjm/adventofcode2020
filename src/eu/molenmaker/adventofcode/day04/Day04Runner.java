package eu.molenmaker.adventofcode.day04;

import eu.molenmaker.adventofcode.travel.AreaMap;
import eu.molenmaker.adventofcode.travel.Passport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day04Runner {
    public static void main(String[] args) throws IOException {
        processFile("data/day04/day_04_test1.txt");
        processFile("data/day04/day_04_input.txt");
        processFile("data/day04/day_04_invalids.txt");
        processFile("data/day04/day_04_valids.txt");
    }

    private static void processFile(String fileName) throws IOException {
        ArrayList<Passport> passports = new ArrayList<>();

        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");

        Passport passport = null;
        for (String line:lines) {
            if (StringUtils.isEmpty(line)) {
                if (passport != null) {
                    passports.add(passport);
                    passport = null;
                }
            } else {
                if (passport == null) {
                    passport = new Passport();
                }
                passport.parseLine(line);
            }
        }
        if (passport != null) {
            passports.add(passport);
        }

        int validCount = 0;
        for (Passport p: passports) {
            if (p.isValid()) validCount++;
            //System.out.println(p);
        }

        System.out.println(String.format("%s: %d possible passports found. %d are valid", fileName, passports.size(), validCount));
     }
}
