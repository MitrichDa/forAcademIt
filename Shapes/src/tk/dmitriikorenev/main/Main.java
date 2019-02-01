package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.*;
import tk.dmitriikorenev.classes.comparators.ShapeAreaComparator;
import tk.dmitriikorenev.classes.comparators.ShapePerimeterComparator;
import tk.dmitriikorenev.classes.exceptions.BadInputDataException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws BadInputDataException {
        Shape[] shapes = new Shape[]{
                new Circle(2),
                new Circle(3),
                new Square(3),
                new Square(2.5),
                new Rectangle(3, 1),
                new Rectangle(2, 2.3),
                new Triangle(0, 10, 10, 0, 0, 0),
                new Triangle(-4, 0, 1, 1, 3, 5)
        };

        Arrays.sort(shapes, new ShapeAreaComparator());
        System.out.println("Фигура наибольшая по площади:");
        System.out.println(shapes[shapes.length - 1]);
        System.out.println();

        Arrays.sort(shapes, new ShapePerimeterComparator());
        System.out.println("Фигура вторая по величине периметра:");
        System.out.println(shapes[shapes.length - 2]);
    }
}
