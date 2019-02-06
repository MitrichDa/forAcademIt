package tk.dmitriikorenev.classes;

import tk.dmitriikorenev.classes.exceptions.WrongMatrixSizeException;

import java.util.Arrays;
import java.util.StringJoiner;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be > 0");
        }
        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.rows.length;
        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        int rowsCount = array.length;
        if (rowsCount == 0) {
            throw new IllegalArgumentException("Matrix dimensions must be > 0");
        }
        int columnsCount = 0;
        for (double[] row : array) {
            if (columnsCount < row.length) {
                columnsCount = row.length;
            }
        }
        if (columnsCount == 0) {
            throw new IllegalArgumentException("Matrix dimensions must be > 0");
        }

        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int rowsCount = vectorsArray.length;
        if (rowsCount == 0) {
            throw new IllegalArgumentException("Matrix dimensions must be > 0");
        }
        int columnsCount = 0;
        for (Vector row : vectorsArray) {
            if (columnsCount < row.getSize()) {
                columnsCount = row.getSize();
            }
        }
        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount, vectorsArray[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("This matrix don`t have row with index " + index);
        }
        return new Vector(rows[index]);
    }

    public void setRowByIndex(int index, Vector row) throws WrongMatrixSizeException {
        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("This matrix don`t have row with index " + index);
        }
        if (getColumnsCount() != row.getSize()) {
            throw new WrongMatrixSizeException("Vector size doesn`t match matrix row size");
        }
        rows[index] = new Vector(row);
    }

    public Vector getColumnByIndex(int index) {
        if (index >= getColumnsCount() || index < 0) {
            throw new IndexOutOfBoundsException("This matrix don`t have index " + index);
        }
        int columnSize = rows.length;
        Vector column = new Vector(columnSize);
        for (int i = 0; i < columnSize; i++) {
            column.setCoordinateByIndex(i, rows[i].getCoordinateByIndex(index));
        }
        return column;
    }

    public void transpose() {
        Vector[] vectorArray = new Vector[getColumnsCount()];
        Arrays.setAll(vectorArray, this::getColumnByIndex);
        rows = vectorArray;
    }

    public void multiplyByNumber(double number) {
        for (Vector row : rows) {
            row.multiplyBy(number);
        }
    }

    public double getDeterminant() throws WrongMatrixSizeException {
        if (rows.length != getColumnsCount()) {
            throw new WrongMatrixSizeException("Matrix must be square to have determinant");
        }
        if (rows.length == 1) {
            return rows[0].getCoordinateByIndex(0);
        }

        Matrix copyMatrix = new Matrix(this);
        int matrixSize = copyMatrix.rows.length;

        double determinant = 1;
        for (int i = 0; i < matrixSize; i++) {
            int j = i;
            while (copyMatrix.rows[i].getCoordinateByIndex(i) == 0 && j < matrixSize - 1) {
                ++j;
                if (copyMatrix.rows[j].getCoordinateByIndex(i) != 0) {
                    Vector temp = (copyMatrix.rows[i]);
                    copyMatrix.rows[i] = copyMatrix.rows[j];
                    copyMatrix.rows[j] = temp;
                    determinant *= -1;
                }
            }

            if (copyMatrix.rows[i].getCoordinateByIndex(i) == 0) {
                return 0;
            }
            ++j;


            for (; j < matrixSize; j++) {
                Vector temp = new Vector(copyMatrix.rows[i]);
                double multiplier = copyMatrix.rows[j].getCoordinateByIndex(i) / copyMatrix.rows[i].getCoordinateByIndex(i);
                temp.multiplyBy(multiplier);
                copyMatrix.rows[j].subtractVector(temp);
            }
            determinant *= copyMatrix.rows[i].getCoordinateByIndex(i);
        }
        return determinant;
    }

    public Vector multiplyByVector(Vector vector) throws WrongMatrixSizeException {
        int vectorLength = vector.getSize();
        int matrixColumnLength = this.rows.length;
        if (vectorLength != this.getColumnsCount()) {
            throw new WrongMatrixSizeException("Vector size must match matrix row size");
        }

        double[] array = new double[matrixColumnLength];
        for (int i = 0; i < matrixColumnLength; i++) {
            array[i] = Vector.getScalarProduct(this.rows[i], vector);
        }
        return new Vector(array);
    }

    public void addMatrix(Matrix matrix) throws WrongMatrixSizeException {
        if (this.rows.length != matrix.rows.length || this.getColumnsCount() != matrix.getColumnsCount()) {
            throw new WrongMatrixSizeException("Matrices sizes must match");
        }

        for (int i = 0; i < this.rows.length; i++) {
            rows[i].addVector(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) throws WrongMatrixSizeException {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new WrongMatrixSizeException("Matrices sizes must match");
        }

        Matrix result = new Matrix(matrix1);
        result.addMatrix(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) throws WrongMatrixSizeException {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new WrongMatrixSizeException("Matrices sizes must match");
        }

        Matrix result = new Matrix(matrix1);
        result.subtractMatrix(matrix2);
        return result;
    }

    public void subtractMatrix(Matrix matrix) throws WrongMatrixSizeException {
        if (this.rows.length != matrix.rows.length || this.getColumnsCount() != matrix.getColumnsCount()) {
            throw new WrongMatrixSizeException("Matrices sizes must match");
        }

        for (int i = 0; i < this.rows.length; i++) {
            rows[i].subtractVector(matrix.rows[i]);
        }
    }

    public static Matrix multiplyByMatrix(Matrix matrix1, Matrix matrix2) throws WrongMatrixSizeException {
        if (matrix2.rows.length != matrix1.getColumnsCount()) {
            throw new WrongMatrixSizeException("matrix1 row size must match matrix2 column size");
        }

        Matrix result = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());
        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                result.rows[i].setCoordinateByIndex(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumnByIndex(j)));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");
        for (Vector row : rows) {
            stringJoiner.add(row.toString());
        }
        return stringJoiner.toString();
    }
}
