package core.math.matrix;


import core.debug.MatrixInvalidException;
import core.debug.MatrixMismatchException;
import core.math.vector.Vector3f;

import java.io.Serializable;
import java.util.Arrays;

public class Matrix implements Serializable {
    private static final long serialVersionUID = -4813024282438495350L;
		public static int mults=0;


    float[][] mat;
    int columns, rows;

    public Matrix(int columns, int rows) {
        mat = initZero(columns, rows);
        this.columns = columns;
        this.rows = rows;
    }

    public Matrix(float[][] mat) {
        rows = mat.length;
        columns = mat[0].length;
        for (int i = 1; i < rows; i++) {
            if (columns != mat[i].length)
                try {
                    throw new MatrixInvalidException(mat);
                } catch (MatrixInvalidException e) {
                    e.printStackTrace();
                }
        }
        this.mat = mat;
        mults++;
    }

    public Matrix mul(Matrix m){
        try {
            return new Matrix(multiply(this,m));
        } catch (MatrixMismatchException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Matrix add(Matrix m){
        try {
            return new Matrix(add(this, m));
        } catch (MatrixMismatchException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setMat(float[][] mat) {
        this.mat = mat;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return writeMatrix(mat);
    }

    public static String writeMatrix(float[][] mat) {
        StringBuilder string = new StringBuilder();
        for (float[] aMat : mat) {
            string.append(Arrays.toString(aMat)).append("\n");
        }
        return string.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Matrix a;
        if (obj instanceof Matrix) {
            a = (Matrix) obj;
        } else return false;

        if (rows == a.rows && columns == a.columns) {
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++) {
                    if (mat[i][j] != a.mat[i][j]) return false;
                }
        } else return false;
        return true;
    }

    public float[][] rawMat(){
        return mat;
    }

    private static float[][] initZero(int columns, int rows) {
        return new float[columns][rows];
    }

    static float[][] add(Matrix a, Matrix b) throws MatrixMismatchException {
        if (a.columns != b.columns && a.rows != b.rows) {
            throw new MatrixMismatchException(MatrixMismatchException.Function.addition);
        }
        float[][] newMat = new float[a.columns][a.rows];
        for (int y = 0; y < a.rows; y++) {
            for (int x = 0; x < a.columns; x++) {
                newMat[x][y] = a.mat[x][y] + b.mat[x][y];
            }
        }
        return newMat;
    }

    static float[][] multiply(Matrix a, Matrix b) throws MatrixMismatchException {
        if (a.columns != b.rows)
            throw new MatrixMismatchException(MatrixMismatchException.Function.multiplication);

        float[][] matrix = new float[a.rows][b.columns];
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.columns; j++) {
                float sum = 0;
                for (int k = 0; k < a.columns; k++)
                    sum += a.mat[i][k] * b.mat[k][j];
                matrix[i][j] = sum;
            }
        }

        return matrix;
    }

    static Vector3f multiply(Vector3f v, Matrix m) throws MatrixMismatchException {
        if(!(m.rows == 1 && m.columns ==3)) throw new MatrixMismatchException(MatrixMismatchException.Function.multiplication);

        return new Vector3f(v.x*m.mat[0][0],v.y*m.mat[0][1],v.z*m.mat[0][2]);
    }
}
