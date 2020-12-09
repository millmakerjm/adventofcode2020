package eu.molenmaker.adventofcode.day08;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import eu.molenmaker.adventofcode.gameconsole.HandheldGameConsole;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08Runner {


    public static void main(String[] args) throws IOException {
        processFile("data/day08/day_08_test1.txt");
        processFile("data/day08/day_08_input.txt");
    }

    private static void processFile(String fileName) throws IOException {
        HandheldGameConsole game = HandheldGameConsole.parseProgram(fileName);
        game.run();

        game.runWithPatching();
    }
}