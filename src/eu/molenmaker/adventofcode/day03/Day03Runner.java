package eu.molenmaker.adventofcode.day03;

import eu.molenmaker.adventofcode.day02.PasswordPolicy;
import eu.molenmaker.adventofcode.travel.AreaMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Day03Runner {
    public static void main(String[] args) throws IOException {
        walkMap("data/day03/day_03_test1.txt");
        walkMap("data/day03/day_03_input.txt");
    }

    private static void walkMap(String fileName) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        AreaMap map = AreaMap.parseMap(lines);

        long treeCount1 = getTreeCount(map, 1, 1);
        long treeCount2 = getTreeCount(map, 3, 1);
        long treeCount3 = getTreeCount(map, 5, 1);
        long treeCount4 = getTreeCount(map, 7, 1);
        long treeCount5 = getTreeCount(map, 1, 2);



        System.out.println(String.format("Treecounts: (%d, %d, %d, %d, %d) = %d", treeCount1, treeCount2, treeCount3, treeCount4, treeCount5, (treeCount1 * treeCount2 * treeCount3 * treeCount4 * treeCount5)));
    }

    private static int getTreeCount(AreaMap map, int deltaX, int deltaY) {
        int rowCount = map.rowCount();
        int xc = 0, yc = 0;
        int treeCount =0;

        while (yc < rowCount-1) {
            xc += deltaX;
            yc += deltaY;
            if (map.getSymbol(xc, yc) == AreaMap.MapSymbol.TREE) {
                treeCount++;
            }
        }
        return treeCount;
    }
}
