package eu.molenmaker.adventofcode.day02;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class Day02Runner {
    public static void main(String[] args) throws IOException {
        processPassword("data/day02/day_02_test1.txt");
        processPassword("data/day02/day_02_input.txt");
    }

    private static void processPassword(String fileName) throws IOException {
        int validPasswordCount = 0;
        int validPasswordCountPolicy2 = 0;
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        for (String line: lines) {
            String[] parts = line.split(":");
            PasswordPolicy policy = PasswordPolicy.parsePolicy(parts[0]);
            String password = StringUtils.deleteWhitespace(parts[1]);
            if (policy.testPassword(password)) {
                validPasswordCount++;
            }
            if (policy.testPasswordPolicyPart2(password)) {
                validPasswordCountPolicy2++;
            }
        }

        System.out.println(String.format("Found %d valid passwords and %d policyII valid passwords.", validPasswordCount, validPasswordCountPolicy2));
    }
}
