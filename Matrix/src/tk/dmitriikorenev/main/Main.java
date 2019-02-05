package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.Matrix;
import tk.dmitriikorenev.classes.Vector;
import tk.dmitriikorenev.classes.exceptions.WrongMatrixSizeException;

public class Main {
    public static void main(String[] args) throws WrongMatrixSizeException {
        double[][] doubleArray = new double[][]{{32, 2, 1}, {1, 2, 25}, {2, 4, 5}};
        Matrix matrix1 = new Matrix(doubleArray);
        Vector[] vectorArray = new Vector[]{new Vector(new double[]{1, 2, 3, 4}), new Vector(new double[]{1, 2, 3, 4}), new Vector(new double[]{-3, 23, 3, 4})};
        Matrix matrix2 = new Matrix(vectorArray);
        Matrix workMatrix = new Matrix(3, 3);

        System.out.println("Работаем с матрицей: " + workMatrix);
        System.out.println("Размерность матрицы: " + "(" + workMatrix.getRowsCount() + ", " + workMatrix.getColumnsCount() + ")");
        System.out.println();

        Vector vector = new Vector(new double[]{1, 2, 3});
        System.out.println("Ставим вектор " + vector + "  во 2 ряд");
        workMatrix.setRowByIndex(1, vector);
        System.out.println("Теперь матрицв: " + workMatrix);
        System.out.println("2 ряд: " + workMatrix.getRowByIndex(1));
        System.out.println("2 колонка: " + workMatrix.getColumnByIndex(1));
        workMatrix.transpose();
        System.out.println("Транаспонированная матрица: " + workMatrix);
        System.out.println();

        System.out.print(workMatrix + " + " + matrix1 + " = ");
        workMatrix.addMatrix(matrix1);
        System.out.println(workMatrix);
        System.out.print(workMatrix + " - " + matrix1 + " = ");
        workMatrix.subtractMatrix(matrix1);
        System.out.println(workMatrix);
        System.out.println("Сумма и разность статическими методами :");
        System.out.println(workMatrix + " + " + matrix1 + " = " + Matrix.getSum(workMatrix, matrix1));
        System.out.println(workMatrix + " - " + matrix1 + " = " + Matrix.getDifference(workMatrix, matrix1));
        System.out.print(workMatrix + " * " + 2 + " = ");
        workMatrix.multiplyByNumber(2);
        System.out.println(workMatrix);

        System.out.println("Определитель " + matrix1 + " = " + matrix1.getDeterminant());
        System.out.println();

        System.out.println(workMatrix + " * " + vector + " = " + workMatrix.multiplyByVector(vector));
        System.out.println(workMatrix + " * " + matrix2 + " = " + Matrix.multiplyByMatrix(workMatrix, matrix2));
    }
}
