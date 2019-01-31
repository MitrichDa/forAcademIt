package tk.dmitriikorenev.classes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
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
}
