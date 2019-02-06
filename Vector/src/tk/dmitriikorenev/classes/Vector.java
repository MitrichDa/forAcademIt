package tk.dmitriikorenev.classes;

import java.util.Arrays;
import java.util.StringJoiner;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of coordinates in vector must be > 0");
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
            throw new IllegalArgumentException("Number of coordinates in vector must be > 0");
        }
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public Vector(int n, Vector vector) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of coordinates in vector must be > 0");
        }
        this.coordinates = Arrays.copyOf(vector.coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    public void addVector(Vector vector) {
        int secondVectorSize = vector.coordinates.length;
        if (this.coordinates.length < secondVectorSize) {
            this.coordinates = Arrays.copyOf(this.coordinates, secondVectorSize);
        }
        for (int i = 0; i < secondVectorSize; i++) {
            this.coordinates[i] += vector.coordinates[i];
        }
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
        int secondVectorSize = vector.coordinates.length;
        if (this.coordinates.length < secondVectorSize) {
            this.coordinates = Arrays.copyOf(this.coordinates, secondVectorSize);
        }
        for (int i = 0; i < secondVectorSize; i++) {
            this.coordinates[i] -= vector.coordinates[i];
        }
    }

    public double getLength() {
        double lengthSquare = 0;
        for (double e : coordinates) {
            lengthSquare += e * e;
        }
        return Math.sqrt(lengthSquare);
    }

    public double getCoordinateByIndex(int n) {
        if (n >= coordinates.length || n < 0) {
            throw new IndexOutOfBoundsException("This vector don`t have index " + n);
        }
        return coordinates[n];
    }

    public void setCoordinateByIndex(int n, double value) {
        if (n >= coordinates.length || n < 0) {
            throw new IndexOutOfBoundsException("This vector don`t have index " + n);
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
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");
        for (double coordinate : coordinates) {
            stringJoiner.add(Double.toString(coordinate));
        }
        return stringJoiner.toString();
    }
}
