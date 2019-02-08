package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.CSVReader;
import tk.dmitriikorenev.classes.HTMLWriter;

import java.io.*;
import java.util.ArrayList;

public class CsvToHtml {
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
            CSVReader csvReader = new CSVReader(reader);
            ArrayList<String[]> lines = csvReader.readAllLines();
            if (lines == null || lines.size() == 0) {
                System.out.println("Ошибка при чтении файла, проверьте корректность данных в исходном файле.");
                return;
            }

            HTMLWriter htmlWriter = new HTMLWriter(writer);
            htmlWriter.writeDocument(lines, args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + "\"" + args[0] + "\" не найден.");
        } catch (IOException e) {
            System.out.println("Неизвестная ошибка чтения/записи.");
        }
    }
}
