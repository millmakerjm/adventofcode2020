package eu.molenmaker.adventofcode.day01;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Day01Runner {
    public static void main(String[] args) throws IOException {
        findExpensePair("data/day01/day_01_test1.txt");
        findExpensePair("data/day01/day_01_input.txt");

        findExpenseTrio("data/day01/day_01_test1.txt");
        findExpenseTrio("data/day01/day_01_input.txt");
    }

    private static void findExpensePair(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        List<Integer> expenses = Lists.transform(lines, Integer::parseInt);

        for (int i = 0; i < expenses.size(); i++) {
            for (int x = i; x < expenses.size(); x++) {
                int one = expenses.get(i);
                int two = expenses.get(x);
                if (one + two == 2020) {
                    System.out.println(String.format("Found %d and %d == %d", one, two, (one * two)));

                }
            }
        }
    }

    private static void findExpenseTrio(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        List<Integer> expenses = Lists.transform(lines, Integer::parseInt);

        for (int i = 0; i < expenses.size(); i++) {
            for (int x = i; x < expenses.size(); x++) {
                for (int y = x; y < expenses.size(); y++) {
                    int one = expenses.get(i);
                    int two = expenses.get(x);
                    int three = expenses.get(y);

                    if (one + two + three == 2020) {
                        System.out.println(String.format("Found trio %d and %d and %d == %d", one, two, three, (one * two * three)));
                        }
                    }
            }
        }
    }
}
