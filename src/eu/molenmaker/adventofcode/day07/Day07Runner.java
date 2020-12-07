package eu.molenmaker.adventofcode.day07;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07Runner {

    private Pattern rulePattern = Pattern.compile("([\\w\\s]+)\\sbags contain\\s(.*).");
    private Pattern bagContentPattern = Pattern.compile("([0-9]+)\\s([\\w\\s]+)\\sbag[s]?");
    private MutableValueGraph<String, Integer> bagsGraph = ValueGraphBuilder.directed().build();
    private HashSet<String> uniqueParents;

    /**
     * Parse each line and build a <a href="https://en.wikipedia.org/wiki/Graph_(abstract_data_type)">graph</a>
     * of all bags containing other bags. The weight is the number of bags of one type contained.
     *
     * @param lines
     */
    private void processFile(List<String> lines) {
        for (String line : lines) {
            // Parse the first part of the line to get the type of the container bag
            Matcher match = rulePattern.matcher(line);
            if (match.find()) {
                String containerBag = match.group(1);
                bagsGraph.addNode(containerBag);

                // Parse the second part of the line to find the content of the container bag
                Matcher contentMatch = bagContentPattern.matcher(match.group(2));
                while (contentMatch.find()) {
                    String contents = contentMatch.group(2);
                    Integer count = Integer.valueOf(contentMatch.group(1));
                    bagsGraph.putEdgeValue(containerBag, contents, count);
                }
            }

        }
        uniqueParents = new HashSet<>();
        walkUp("shiny gold");
        System.out.println(String.format("The answer is: %d", uniqueParents.size()));

        long total =  walkDown("shiny gold");
        System.out.println(String.format("The answer of part 2 is: %d", total));
    }

    private void walkUp(String bag) {
        Set<String> list = bagsGraph.predecessors(bag);
        for (String s: list) {
            uniqueParents.add(s);
            walkUp(s);
        }
    }

    private long walkDown(String bag) {
        long total = 0;
        Set<String> list = bagsGraph.successors(bag);

        for (String s: list) {
            Optional<Integer> count = bagsGraph.edgeValue(bag, s);
            total = total + count.get() + (walkDown(s) * count.get());
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        processFile("data/day07/day_07_test1.txt");
        processFile("data/day07/day_07_input.txt");
    }

    private static void processFile(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        new Day07Runner().processFile(lines);
    }
}