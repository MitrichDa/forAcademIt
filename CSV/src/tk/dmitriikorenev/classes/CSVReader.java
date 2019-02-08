package tk.dmitriikorenev.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    private BufferedReader reader;

    public CSVReader(BufferedReader reader) {
        this.reader = reader;
    }

    private static String[] readLine(BufferedReader reader) throws IOException {
        boolean isQuoteOpen = false;
        boolean isCellStart = true;
        ArrayList<String> cells = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();

        while (reader.ready()) {
            int nextChar = reader.read();
            while (nextChar == '\r') {
                nextChar = reader.read();
            }

            if (isQuoteOpen) {
                if (nextChar == '\"' && reader.ready()) {
                    reader.mark(1);
                    nextChar = reader.read();

                    if (nextChar == '\"') {
                        currentValue.append((char) nextChar);
                    } else {
                        isQuoteOpen = false;
                        reader.reset();
                    }
                } else {
                    currentValue.append((char) nextChar);
                }
            } else {
                if (nextChar == '\"' && isCellStart) {
                    isQuoteOpen = true;
                    isCellStart = false;
                } else {
                    if (nextChar == '\"') {
                        return null;
                    } else if (nextChar == ',') {
                        cells.add(currentValue.toString());
                        isCellStart = true;
                        currentValue = new StringBuilder();
                    } else if (nextChar == '\n') {
                        break;
                    } else {
                        currentValue.append((char) nextChar);
                        isCellStart = false;
                    }
                }
            }
        }

        if (currentValue.length() > 0 || cells.size() > 0) {
            cells.add(currentValue.toString());
        }
        return cells.toArray(new String[0]);
    }

    public ArrayList<String[]> readAllLines() throws IOException {
        ArrayList<String[]> lines = new ArrayList<>();
        int rowSize = 0;
        while (reader.ready()) {
            String[] cells = CSVReader.readLine(reader);
            if (cells == null || (cells.length != rowSize && rowSize != 0 && cells.length != 0)) {
                return null;
            } else {
                if (cells.length != 0) {
                    rowSize = cells.length;
                    lines.add(cells);
                }
            }
        }
        return lines;
    }
}
