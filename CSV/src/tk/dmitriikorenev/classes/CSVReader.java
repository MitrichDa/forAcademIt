package tk.dmitriikorenev.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVReader {
    private static final int OPEN_TAG = 0;
    private static final int CLOSE_TAG = 1;
    private static final String DOCTYPE = "<!DOCTYPE html>";
    private static final String META = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";
    private static final String[] HTML_TAG = {"<html>", "</html>"};
    private static final String[] HEAD_TAG = {"<head>", "</head>"};
    private static final String[] TITLE_TAG = {"<title>", "</title>"};
    private static final String[] BODY_TAG = {"<body>", "</body>"};
    private static final String[] TABLE_TAG = {"<table>", "</table>"};
    private static final String[] STRING_TAG = {"<tr>", "</tr>"};
    private static final String[] CELL_TAG = {"<td>", "</td>"};
    private static final String BREAK_TAG = "<br/>";
    private static final String GREATER_SYMBOL = "&gt;";
    private static final String LESSER_SYMBOL = "&lt;";
    private static final String AMPERSAND_SYMBOL = "&amp;";

    public static ArrayList<String> readLine(BufferedReader reader) throws IOException {
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
        return cells;
    }

    public static void writeHTMLLine(ArrayList<String> cells, PrintWriter writer) {
        writer.write(STRING_TAG[OPEN_TAG]);
        for (String e : cells) {
            e = e.replaceAll("&", AMPERSAND_SYMBOL);
            e = e.replaceAll(">", GREATER_SYMBOL);
            e = e.replaceAll("<", LESSER_SYMBOL);
            e = e.replaceAll("\n", BREAK_TAG);
            writer.write(CELL_TAG[OPEN_TAG]);
            writer.write(e);
            writer.write(CELL_TAG[CLOSE_TAG]);
        }
        writer.write(STRING_TAG[CLOSE_TAG]);
    }

    public static void main(String[] args) {
        String startUpErrorMessage = "Ошибка при запуске программы." + System.lineSeparator() +
                "Для корректной работы укажите 2 аргумента:" + System.lineSeparator() +
                "1 - путь к исходному файлу CSV" + System.lineSeparator() +
                "2 - путь к файлу для записи в формате HTML";
        if (args.length != 2) {
            System.out.println(startUpErrorMessage);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            writer.write(DOCTYPE);
            writer.write(HTML_TAG[OPEN_TAG]);
            writer.write(HEAD_TAG[OPEN_TAG]);
            writer.write(TITLE_TAG[OPEN_TAG]);
            writer.write(args[1]);
            writer.write(TITLE_TAG[CLOSE_TAG]);
            writer.write(META);
            writer.write(HEAD_TAG[CLOSE_TAG]);
            writer.write(BODY_TAG[OPEN_TAG]);
            writer.write(TABLE_TAG[OPEN_TAG]);

            int rowSize = 0;
            while (reader.ready()) {
                ArrayList<String> cells = readLine(reader);
                if (cells == null || (cells.size() != rowSize && rowSize != 0 && cells.size() != 0)) {
                    System.out.println("Ошибка при чтении файла, проверьте корректность данных в исходном файле.");
                    return;
                } else {
                    if (cells.size() != 0) {
                        rowSize = cells.size();
                        writeHTMLLine(cells, writer);
                    }
                }
            }

            writer.write(TABLE_TAG[CLOSE_TAG]);
            writer.write(BODY_TAG[CLOSE_TAG]);
            writer.write(HTML_TAG[CLOSE_TAG]);
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка.");
        }
    }
}
