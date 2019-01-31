package tk.dmitriikorenev.classes;

public interface Shape {
    double PRECISION_LIMIT = 1e-7;

    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();
}
