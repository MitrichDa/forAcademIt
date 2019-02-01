package tk.dmitriikorenev.classes;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.coordinates = new double[n];
    }

    public Vector(Vector vector) {
        coordinates = vector.coordinates.clone();
    }

    public Vector(double[] coordinates) {
        this.coordinates = coordinates.clone();
    }

    public Vector(int n, double[] coordinates) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    public void addVector(Vector vector) {
        int newSize = Math.max(this.coordinates.length, vector.coordinates.length);
        double[] sum = new double[newSize];
        for (int i = 0; i < newSize; i++) {
            double temp1 = i >= this.coordinates.length ? 0 : this.coordinates[i];
            double temp2 = i >= vector.coordinates.length ? 0 : vector.coordinates[i];
            sum[i] = temp1 + temp2;
        }
        this.coordinates = sum;
    }

    public void rotate() {
        this.multiplyBy(-1);
    }

    public void multiplyBy(double number) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= number;
        }
    }

    public void subtractVector(Vector vector) {
        Vector negativeVector = new Vector(vector);
        negativeVector.rotate();
        this.addVector(negativeVector);
    }

    public double getLength() {
        double lengthSquare = 0;
        for (double e : coordinates) {
            lengthSquare += e * e;
        }
        return Math.sqrt(lengthSquare);
    }

    public double getCoordinateByIndex(int n) throws IllegalArgumentException {
        if (n >= coordinates.length || n < 0) {
            throw new IllegalArgumentException();
        }
        return coordinates[n];
    }

    public void setCoordinateByIndex(int n, double value) throws IllegalArgumentException {
        if (n >= coordinates.length || n < 0) {
            throw new IllegalArgumentException();
        }
        coordinates[n] = value;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.addVector(vector2);
        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtractVector(vector2);
        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        double result = 0;
        for (int i = 0; i < minSize; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Vector vector = (Vector) obj;
        return Arrays.equals(this.coordinates, vector.coordinates);
    }

    @Override
    public String toString() {
        if (coordinates.length == 0)
            return "{}";
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (int i = 0; ; i++) {
            builder.append(coordinates[i]);
            if (i == coordinates.length - 1)
                return builder.append('}').toString();
            builder.append(", ");
        }
    }
}
