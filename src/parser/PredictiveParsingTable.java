package parser;

import scanner.TokenType;

import java.util.HashMap;
import java.util.Map;

public class PredictiveParsingTable {

    private final Map<TableRowHeader, Map<TokenType, String>> table;

    public PredictiveParsingTable() {
        table = new HashMap<>();

        for (TableRowHeader row : TableRowHeader.values()) {
            table.put(row, new HashMap<>());
        }
    }

    public void add(TableRowHeader row, TokenType column, String production) {
        table.get(row).put(column, production);
    }

    public String getProduction(TableRowHeader row, TokenType column) {
        return table.get(row).get(column);
    }
}

