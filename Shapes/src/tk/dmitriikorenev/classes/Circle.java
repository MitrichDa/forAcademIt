package tk.dmitriikorenev.classes;

import tk.dmitriikorenev.classes.exceptions.BadInputDataException;

public class Circle implements Shape {
    private static final String MESSAGE = "Радиус круга меньше или равен 0";
    private double radius;

    public Circle(double radius) throws BadInputDataException {
        if (radius < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) throws BadInputDataException {
        if (radius < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Круг с радиусом " + radius + System.lineSeparator() +
                "Диаметр: " + 2 * radius + System.lineSeparator() +
                "Площадь: " + getArea() + System.lineSeparator() +
                "Длина окружности: " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Circle circle = (Circle) o;
        return radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}
