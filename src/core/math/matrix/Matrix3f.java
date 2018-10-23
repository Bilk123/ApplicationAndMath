package core.math.matrix;


import core.debug.MatrixMismatchException;
import core.math.vector.Vector2f;
import core.math.vector.Vector3f;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Matrix3f extends SquareMatrix {
	private static final long serialVersionUID = 368929315760785185L;

	public Matrix3f() {
		super(3);
	}

	public Matrix3f(float[][] mat) {
		super(mat);
	}

	public Matrix3f add(Matrix3f m) {
		try {
			return new Matrix3f(Matrix.add(this, m));
		} catch (MatrixMismatchException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Matrix3f mul(Matrix3f m) {
		try {
			return new Matrix3f(Matrix.multiply(this, m));
		} catch (MatrixMismatchException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Matrix3f transpose() {
		return new Matrix3f(SquareMatrix.transpose(this));
	}

	public float determinant() {

		return SquareMatrix.determinant(mat);

	}
	public Matrix3f inverse() {

		return new Matrix3f(SquareMatrix.inverse(mat));

	}

	public Vector2f mul(Vector2f v) {
		float x = mat[0][0] * v.x + mat[0][1] * v.y + mat[0][2];
		float y = mat[1][0] * v.x + mat[1][1] * v.y + mat[1][2];
		return new Vector2f(x, y);
	}

	public Vector3f mul(Vector3f v) {
		float x = mat[0][0] * v.x + mat[0][1] * v.y + mat[0][2] * v.z;
		float y = mat[1][0] * v.x + mat[1][1] * v.y + mat[1][2] * v.z;
		float z = mat[2][0] * v.x + mat[2][1] * v.y + mat[2][2] * v.z;
		return new Vector3f(x, y, z);
	}

	public static Matrix3f initRotation(float a) {
		a = (float)toRadians(a);
		return new Matrix3f(new float[][]{
				{(float)cos(a), (float)-sin(a), 0},
				{(float)sin(a), (float)cos(a), 0},
				{0, 0, 1}
		});
	}

	public static Matrix3f initScale(float sx, float sy) {
		return new Matrix3f(new float[][]{
				{sx,  0, 0},
				{ 0, sy, 0},
				{ 0,  0, 1}
		});
	}

	public static Matrix3f initTranslation(float x, float y) {
		return new Matrix3f(new float[][]{
				{1, 0, x},
				{0, 1, y},
				{0, 0, 1}
		});
	}
}
