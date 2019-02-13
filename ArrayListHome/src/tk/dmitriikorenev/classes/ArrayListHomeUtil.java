package tk.dmitriikorenev.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListHomeUtil {
    public static void readToList(BufferedReader reader, List<String> list) throws IOException {
        while (reader.ready()) {
            list.add(reader.readLine());
        }
    }

    public static void deleteEvenNumbersFromList(List<Integer> list) {
        list.removeIf(x -> x % 2 == 0);
    }

    public static List<Integer> copyUniqueNumbers(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
}
