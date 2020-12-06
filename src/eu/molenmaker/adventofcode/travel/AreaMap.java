package eu.molenmaker.adventofcode.travel;

import java.util.ArrayList;
import java.util.List;

public class AreaMap {
    ArrayList<AreaMapRow> rows = new ArrayList<>();

    private void parseRow(String srow) {
        rows.add(AreaMapRow.parseRow(srow));
    }

    public static AreaMap parseMap(List<String> map) {
        AreaMap areaMap = new AreaMap();
        for (String srow : map) {
            areaMap.parseRow(srow);
        }
        return areaMap;
    }

    public int rowCount() {
        return rows.size();
    }

    public int columnCount() {
        return rows.get(0).columnCount();
    }

    public MapSymbol getSymbol(int x, int y) {
        return rows.get(y).getSymbol(x);
    }

    public enum MapSymbol {EMPTY, TREE, UNKNOWN};

    private static class AreaMapRow {
        ArrayList<MapSymbol> columns = new ArrayList<>();

        public static AreaMapRow parseRow(String srow) {
            AreaMapRow row = new AreaMapRow();
            row.localParseRow(srow);
            return row;
        }

        private void localParseRow(String row) {
            for(int i=0; i<row.length(); i++) {
                MapSymbol s;
                switch (row.charAt(i)) {
                    case '#':
                        s = MapSymbol.TREE;
                        break;
                    case '.':
                        s = MapSymbol.EMPTY;
                        break;
                    default:
                        s = MapSymbol.UNKNOWN;
                        break;
                }
                columns.add(s);
            }
        }

        int columnCount() {
            return columns.size();
        }

        MapSymbol getSymbol(int x) {
            return columns.get(x % columns.size());
        }
    }
}
