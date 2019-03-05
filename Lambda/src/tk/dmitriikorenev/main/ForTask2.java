package tk.dmitriikorenev.main;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class ForTask2 {
    public static void main(String[] args) {
        System.out.println("Вычисление квадратных корней.");
        System.out.println("Введите количество чисел для вывода:");
        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();
        DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(limit)
                .forEach(System.out::println);

        System.out.println("Числа Фибоначчи.");
        System.out.println("Введите количество чисел для вывода:");
        int fibonacciLimit = scanner.nextInt();
        Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(fibonacciLimit)
                .forEach(x -> System.out.println(x[1]));
    }
}
