package tk.dmitriikorenev.classes;

import tk.dmitriikorenev.classes.comparators.exceptions.BadInputDataException;

public class Square implements Shape {
    private static final String MESSAGE = "Сторона квадрата меньше или равен 0";
    private double side;

    public Square(double side) throws BadInputDataException {
        if (side < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) throws BadInputDataException {
        if (side < Shape.PRECISION_LIMIT) {
            throw new BadInputDataException(MESSAGE);
        }
        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Квадрат со стороной " + side + System.lineSeparator() +
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
        Square square = (Square) o;
        return side == square.side;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(side);
    }
}
