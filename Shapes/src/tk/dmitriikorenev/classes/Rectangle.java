package tk.dmitriikorenev.classes;

import tk.dmitriikorenev.classes.exceptions.BadInputDataException;

public class Rectangle implements Shape {
    private static final String MESSAGE = "Сторона прямоугольника меньше или равна 0";
    private double width;
    private double height;

    public Rectangle(double width, double height) throws BadInputDataException {
        if (width < Shape.PRECISION_LIMIT || height < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.width = width;
        this.height = height;
    }

    public void setWidth(double width) throws BadInputDataException {
        if (width < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.width = width;
    }

    public void setHeight(double height) throws BadInputDataException {
        if (width < Shape.PRECISION_LIMIT || height < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Прямоугольник с шириной " + width + " и высотой " + height + System.lineSeparator() +
                "Площадь: " + getArea() + System.lineSeparator() +
                "Периметр: " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}
