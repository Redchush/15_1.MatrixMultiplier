package root.model.matrix;

import java.util.Arrays;


public class Matrix {

    private int[][] body;

    private Matrix(int degree) {
        body = new int[degree][degree];
    }

    private Matrix(int[][] matrix) {
        this.body = matrix;
    }

    public Matrix setElement(int i, int j, int value) {
        body[i][j] = value;
        return this;
    }

    public int getDegree() {
        return body.length;
    }

    public int getElement(int i, int j) {
        return body[i][j];
    }

    public int[][] getBody() {
        return body;
    }

    public String toString() {
        String s = "\nMatrix : " + "degree "  + body.length + "\n";
        for (int i = 0; i < body.length; i++) {
            for (int j = 0; j < body.length; j++) {
                s += body[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(body, matrix.body);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(body);
    }

    public static class MatrixBuilder {

        public static Matrix createRandomMatrix(int n) {
            Matrix matrix = new Matrix(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix.setElement(i, j, (int) (Math.random() * 10));
                }
            }
            return matrix;
        }

        public static Matrix createMatrixWithBody(int[][] matrix) {
            return new Matrix(matrix);
        }

        public static Matrix createEmptyMatrix(int n){
            return new Matrix(n);
        }
    }
}
