package tk.dmitriikorenev.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListHomeUtil {
    public static void readToList(BufferedReader reader, List<String> list) throws IOException {
        while (reader.ready()) {
            list.add(reader.readLine());
        }
    }

    public static void deleteEvenNumbersFromList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }
    }

    public static List<Integer> copyUniqueNumbers(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
