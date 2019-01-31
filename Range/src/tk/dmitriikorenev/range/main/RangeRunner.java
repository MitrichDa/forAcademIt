package tk.dmitriikorenev.range.main;

import tk.dmitriikorenev.range.classes.Range;

import java.util.Arrays;

public class RangeRunner {
    public static void main(String[] args) {
        Range range1 = new Range(1, 10);
        Range range2 = new Range(9, 11);
        double point = 3;
        System.out.println("Длина отрезка" + range1 + ": " + range1.getLength());
        System.out.println("Принадлежит ли точка " + point + " к отрезку " + range1 + ":" + range1.isInside(point));
        System.out.println("Результат пересечения" + range1 + " и " + range2 + ": " + range1.getIntersection(range2));
        System.out.println("Результат объединения" + range1 + " и " + range2 + ": " + Arrays.toString(range1.getUnion(range2)));
        System.out.println("Разность " + range1 + " и " + range2 + ": " + Arrays.toString(range1.getDifference(range2)));
    }
}
