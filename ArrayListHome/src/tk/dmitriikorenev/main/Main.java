package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.ArrayListHomeUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> listFromFile = new ArrayList<>();
        String filePath = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayListHomeUtil.readToList(reader, listFromFile);
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + filePath + "\" not found. Check file path.");
            return;
        } catch (IOException e) {
            System.out.println("Can not read from file \"" + filePath + "\"");
            return;
        }

        listFromFile.forEach(System.out::println);
        System.out.println();

        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 3, 6, 5, 7, 8, 9, 9, 10));
        System.out.println(integerList);
        ArrayListHomeUtil.deleteEvenNumbersFromList(integerList);
        System.out.println(integerList);
        System.out.println();

        ArrayList<Integer> integerList2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        System.out.println(integerList2);
        List<Integer> uniqueList = ArrayListHomeUtil.copyUniqueNumbers(integerList2);
        System.out.println(uniqueList);
    }
}
