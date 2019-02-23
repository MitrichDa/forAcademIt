package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArrayListTest {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5};
        Integer[] integers2 = {6, 7, 8, 9};
        List<Integer> numbers = new MyArrayList<>(integers);
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(integers2));
        System.out.println(numbers.contains(3));
        System.out.println(numbers.contains(6));
        System.out.println(numbers.containsAll(numbers2));
        numbers.addAll(1, numbers2);
        numbers.add(1, 8);
        numbers.retainAll(numbers2);
        System.out.println(numbers.lastIndexOf(8));
        Integer[] array = numbers.toArray(new Integer[0]);

        for (Object e : array) {
            System.out.println(e);
        }
    }
}