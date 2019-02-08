package tk.dmitriikorenev.classes;

import java.io.PrintWriter;
import java.util.ArrayList;

public class HTMLWriter {
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

    private PrintWriter writer;

    public HTMLWriter(PrintWriter writer) {
        this.writer = writer;
    }

    private static void writeTableLine(String[] cells, PrintWriter writer) {
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

    public void writeDocument(ArrayList<String[]> lines, String title) {
        writer.write(DOCTYPE);
        writer.write(HTML_TAG[OPEN_TAG]);
        writer.write(HEAD_TAG[OPEN_TAG]);
        writer.write(TITLE_TAG[OPEN_TAG]);
        writer.write(title);
        writer.write(TITLE_TAG[CLOSE_TAG]);
        writer.write(META);
        writer.write(HEAD_TAG[CLOSE_TAG]);
        writer.write(BODY_TAG[OPEN_TAG]);
        writer.write(TABLE_TAG[OPEN_TAG]);

        lines.forEach(line -> writeTableLine(line, writer));

        writer.write(TABLE_TAG[CLOSE_TAG]);
        writer.write(BODY_TAG[CLOSE_TAG]);
        writer.write(HTML_TAG[CLOSE_TAG]);
    }
}
