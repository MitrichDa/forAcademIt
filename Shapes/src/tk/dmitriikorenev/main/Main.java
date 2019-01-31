package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];
        shapes[0] = new Circle(2);
        shapes[1] = new Circle(3);
        shapes[2] = new Square(3);
        shapes[3] = new Square(2.5);
        shapes[4] = new Rectangle(3, 1);
        shapes[5] = new Rectangle(2, 2.3);
        shapes[6] = new Triangle(0, 2, 0, 0, 0, 5);
        shapes[7] = new Triangle(-1, 2, 0, 0, 0, 4);

        Arrays.sort(shapes, Comparator.comparing(Shape::getArea));
        System.out.println("Фигура наибольшая по площади:");
        System.out.println(shapes[shapes.length - 1]);
        System.out.println("Фигура вторая по площади:");
        System.out.println(shapes[shapes.length - 2]);
    }
}
