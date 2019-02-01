package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.Vector;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        Vector vector2 = new Vector(8);
        Vector vector3 = new Vector(5, new double[]{1, 1, 1});
        Vector vector4 = new Vector(2, new double[]{1, 2, 3});
        Vector vector5 = new Vector(vector1);

        System.out.println("Проверка конструкторов");
        System.out.println("Вектор 1:" + vector1 + ", размерность вектора 1: " + vector1.getSize());
        System.out.println("Вектор 2:" + vector2 + ", размерность вектора 2: " + vector2.getSize());
        System.out.println("Вектор 3:" + vector3 + ", размерность вектора 3: " + vector3.getSize());
        System.out.println("Вектор 4:" + vector4 + ", размерность вектора 4: " + vector4.getSize());
        System.out.println("Вектор 5:" + vector5 + ", размерность вектора 5: " + vector5.getSize());
        Vector vector6;
        try {
            vector6 = new Vector(0);
            System.out.println(vector6);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании вектора.");
        }
        System.out.println();

        double multiplier = 3;
        System.out.print(vector1 + " * " + multiplier + " = ");
        vector1.multiplyBy(multiplier);
        System.out.println(vector1);

        System.out.print(vector1 + " + " + vector3 + " = ");
        Vector sum = Vector.getSum(vector1, vector3);
        System.out.println(sum);
        vector1.addVector(vector3);
        System.out.println("Результат не статичным методом: " + vector1);

        System.out.print(vector1 + " - " + vector4 + " = ");
        Vector difference = Vector.getDifference(vector1, vector4);
        System.out.println(difference);
        vector1.subtractVector(vector4);
        System.out.println("Результат не статичным методом: " + vector1);

        System.out.print(vector1 + " * " + vector4 + " = ");
        System.out.println(Vector.getScalarProduct(vector1, vector4));

        System.out.print("|" + vector1 + "| = ");
        System.out.println(vector1.getLength());

        System.out.print("-" + vector1 + " = ");
        vector1.rotate();
        System.out.println(vector1);

        try {
            int index = 15;
            double value = 15;
            System.out.print(index + "-ая координата вектора " + vector1 + ": ");
            System.out.println(vector1.getCoordinateByIndex(index));
            System.out.println("Заменим это значение на " + value);
            vector1.setCoordinateByIndex(index, value);
            System.out.println("Вектор теперь: " + vector1);
        } catch (IllegalArgumentException e) {
            System.out.println("Нет такой координаты");
        }
    }
}
