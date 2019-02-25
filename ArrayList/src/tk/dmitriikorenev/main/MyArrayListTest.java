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
        numbers.addAll(1, numbers2);
        System.out.println(numbers.containsAll(numbers2));
        numbers.retainAll(numbers2);
        System.out.println(numbers.lastIndexOf(8));
        System.out.println("size " + numbers.size());
        numbers.remove(Integer.valueOf(8));
        System.out.println("size " + numbers.size());
        for (Object e : numbers) {
            System.out.println(e);
        }

        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        MyArrayList<String> strings2 = new MyArrayList<>(strings);
        strings2.add("1");
        System.out.println(strings2);
        strings2.ensureCapacity(20);
        System.out.println(strings2.addAll(strings));
        System.out.println(strings2);
        System.out.println(strings2.removeAll(strings));
        strings2.trimToSize();
        System.out.println(strings2);
    }
}