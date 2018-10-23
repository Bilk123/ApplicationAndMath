package core.math.vector;

import java.io.Serializable;

public class Vector3i implements Serializable {
	private static final long serialVersionUID = 1598115984313154143L;

	public static final Vector3i RIGHT = new Vector3i(1, 0, 0);
	public static final Vector3i LEFT = new Vector3i(-1, 0, 0);
	public static final Vector3i UP = new Vector3i(0, 1, 0);
	public static final Vector3i DOWN = new Vector3i(0, -1, 0);
	public static final Vector3i FORWARD = new Vector3i(0, 0, 1);
	public static final Vector3i BACKWARDS = new Vector3i(0, 0, -1);
	public static final Vector3i ZERO = new Vector3i(0, 0, 0);

	public int x, y, z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i(int x, int y) {
		this(x, y, 0);
	}

	public Vector3i() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public int dot(Vector3i vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}

	public Vector3i cross(Vector3i vec) {
		return new Vector3i(
				y * vec.z - z * vec.y,
				-x * vec.z + z * vec.x,
				x * vec.y - y * vec.x);
	}

	public Vector3i add(Vector3i vec) {
		return new Vector3i(x + vec.x, y + vec.y, z + vec.z);
	}

	public Vector3i addEq(Vector3i vec) {
		x += vec.x;
		y += vec.y;
		y += vec.z;
		return this;
	}

	public Vector3i sub(Vector3i vec) {
		return new Vector3i(x - vec.x, y - vec.y, z - vec.z);
	}

	public Vector3i subEq(Vector3i vec) {
		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
		return this;
	}

	public Vector3i mul(int scalar) {
		return new Vector3i(scalar * x, scalar * y, scalar * z);
	}

	public Vector3i mulEq(int scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	public float len() {
		return (float)Math.sqrt(dot(this));
	}

	public int len2() {
		return dot(this);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	public Vector3i cpy() {
		return new Vector3i(x, y, z);
	}

	public Vector3i scl(Vector3i vec) {
		return new Vector3i(x * vec.x, y * vec.y, z * vec.z);
	}

	public Vector2f toVector2f() {
		return new Vector2f(x, y);
	}

	public void set(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(Vector3i vec) {
		set(vec.x, vec.y, vec.z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector3i) {
			Vector3i temp = (Vector3i) obj;
			return temp.x == x && temp.y == y && temp.z == z;
		} else {
			return false;
		}
	}
}
