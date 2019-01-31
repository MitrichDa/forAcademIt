package tk.dmitriikorenev.classes.comparators;

import tk.dmitriikorenev.classes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double difference = shape1.getArea() - shape2.getArea();
        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        }
        return 0;
    }
}
