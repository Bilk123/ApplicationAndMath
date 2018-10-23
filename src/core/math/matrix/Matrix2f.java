package core.math.matrix;


import core.debug.MatrixMismatchException;
import core.math.vector.Vector2f;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Matrix2f extends SquareMatrix {
    private static final long serialVersionUID = -4714665710477799236L;

    public Matrix2f() {
        super(2);
    }

    public Matrix2f(float[][] mat) {
        super(mat);
    }

	public static Matrix2f initRotation(float a) {
        a= (float)toRadians(a);
        return new Matrix2f(new float[][]{
                {(float)cos(a), (float)-sin(a)},
                {(float)sin(a), (float)cos(a)}
        });
	}

	public Matrix2f add(Matrix2f m) {
        try {
            return new Matrix2f(Matrix.add(this, m));
        } catch (MatrixMismatchException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Matrix2f mul(Matrix2f m) {
        try {
            return new Matrix2f(Matrix.multiply(this, m));
        } catch (MatrixMismatchException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector2f mul(Vector2f vec){
        return new Vector2f(
                vec.x*mat[0][0]+vec.y*mat[0][1],
                vec.x*mat[1][0]+vec.y*mat[1][1]
                );
    }

    public Matrix2f transpose() {
        return new Matrix2f(SquareMatrix.transpose(this));
    }

    public Matrix2f inverse() {
        return new Matrix2f(SquareMatrix.inverse(mat));
    }

    public float determinant() {
        return SquareMatrix.determinant(mat);
    }
}
