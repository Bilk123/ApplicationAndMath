package core.math.vector;

import java.io.Serializable;

public class Vector2i implements Serializable {
	private static final long serialVersionUID = 753487315159088463L;

	public static final Vector2i RIGHT = new Vector2i(1, 0);
	public static final Vector2i LEFT = new Vector2i(-1, 0);
	public static final Vector2i UP = new Vector2i(0, 1);
	public static final Vector2i DOWN = new Vector2i(0, -1);
	public static final Vector2i ZERO = new Vector2i(0, 0);

	public int x, y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2i() {
		x = 0;
		y = 0;
	}

	public int dot(Vector2i vec) {
		return x * vec.x + y * vec.y;
	}

	public Vector2i add(Vector2i vec) {
		return new Vector2i(x + vec.x, y + vec.y);
	}

	public Vector2i addEq(Vector2i vec) {
		x += vec.x;
		y += vec.y;
		return this;
	}

	public Vector2i sub(Vector2i vec) {
		return new Vector2i(x - vec.x, y - vec.y);
	}

	public Vector2i subEq(Vector2i vec) {
		x -= vec.x;
		y -= vec.y;
		return this;
	}

	public Vector2i mul(int scalar) {
		return new Vector2i(scalar * x, scalar * y);
	}


	public Vector2i mulEq(int scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}

	public float len() {
		return (float) Math.sqrt(dot(this));
	}

	public int len2() {
		return dot(this);
	}

	public Vector2i cpy() {
		return new Vector2i(x, y);
	}

	public Vector2i scl(Vector2i vec) {
		return new Vector2i(x * vec.x, y * vec.y);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector2i) {
			Vector2i temp = (Vector2i) obj;
			return temp.x == x && temp.y == y;
		} else {
			return false;
		}
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2i vec) {
		set(vec.x, vec.y);
	}
}
