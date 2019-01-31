package tk.dmitriikorenev.classes;

import java.util.Arrays;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double[] xCoordinates = new double[]{x1, x2, x3};
        Arrays.sort(xCoordinates);
        return xCoordinates[2] - xCoordinates[0];
    }

    @Override
    public double getHeight() {
        double[] xCoordinates = new double[]{y1, y2, y3};
        Arrays.sort(xCoordinates);
        return xCoordinates[2] - xCoordinates[0];
    }

    @Override
    public double getArea() {
        return Math.abs((x2 - x1) * (y3 - y1) + (x3 - x1) * (y2 - y1)) / 2;
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) + Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2)) + Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
    }

    @Override
    public String toString() {
        return "Треугольник с координатами " + "(" + x1 + ", " + y1 + ")" + ", " + "(" + x2 + ", " + y2 + ")" + ", " + "(" + x3 + ", " + y3 + ")" + System.lineSeparator() +
                "Ширина: " + getWidth() + System.lineSeparator() +
                "Высота: " + getHeight() + System.lineSeparator() +
                "Площадь: " + getArea() + System.lineSeparator() +
                "Периметр: " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 &&
                x2 == triangle.x2 &&
                x3 == triangle.x3 &&
                y1 == triangle.y1 &&
                y2 == triangle.y2 &&
                y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}
