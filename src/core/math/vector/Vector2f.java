package core.math.vector;

import core.utils.Mathf;
import org.jbox2d.common.Vec2;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Vector2f implements Serializable {
	private static final long serialVersionUID = -8246577062847992390L;

	public float x, y;
	public static final Vector2f RIGHT = new Vector2f(1, 0);
	public static final Vector2f LEFT = new Vector2f(-1, 0);
	public static final Vector2f UP = new Vector2f(0, 1);
	public static final Vector2f DOWN = new Vector2f(0, -1);
	public static final Vector2f ZERO = new Vector2f(0, 0);


	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(double x, double y) {
		this((float) x, (float) y);
	}

	public Vector2f() {
		x = 0;
		y = 0;
	}

	public float dot(Vector2f vec) {
		return x * vec.x + y * vec.y;
	}

	public Vector2f rotate(float ang) {
		ang = (float) Math.toRadians(ang);
		var sn = Mathf.sin(ang);
		var cs = Mathf.cos(ang);
		return new Vector2f(x * cs - y * sn, x * sn + y * cs);
	}

	public Vector2f add(Vector2f vec) {
		return new Vector2f(x + vec.x, y + vec.y);
	}

	public Vector2f addEq(Vector2f vec) {
		x += vec.x;
		y += vec.y;
		return this;
	}

	public Vector2f sub(Vector2f vec) {
		return new Vector2f(x - vec.x, y - vec.y);
	}

	public Vector2f subEq(Vector2f vec) {
		x -= vec.x;
		y -= vec.y;
		return this;
	}

	public Vector2f mul(float scalar) {
		return new Vector2f(scalar * x, scalar * y);
	}

	public Vector2f mul(double scalar) {
		return mul((float) scalar);
	}

	public Vector2f mulEq(float scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}

	public Vector2f mulEq(double scalar) {
		return mulEq((float) scalar);
	}

	public Vector2f normalise() {
		float len = len();
		if (len() < 0.000001f) {
			x = 0;
			y = 0;
			return this;
		}

		x /= len;
		y /= len;
		return this;
	}

	public Vector2f limit(float val) {
		if (len() > val) return setMag(val);
		return this;
	}

	public Vector2f setMag(float val) {
		return normalise().mulEq(val);

	}

	public float len() {
		return (float) Math.sqrt(dot(this));
	}

	public float len2() {
		return dot(this);
	}

	public Vector2f cpy() {
		return new Vector2f(x, y);
	}

	public Vector2f scl(Vector2f vec) {
		return new Vector2f(x * vec.x, y * vec.y);
	}

	@Override
	public String toString() {
		var df = new DecimalFormat("0.0000");

		return "[" + df.format(x) + "; " + df.format(y) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector2f) {
			Vector2f temp = (Vector2f) obj;
			return temp.x == x && temp.y == y;
		} else {
			return false;
		}
	}

	public static Vector2f convert(Vec2 vec) {
		return new Vector2f(vec.x, vec.y);
	}

	public static Vec2 convert(Vector2f vec) {
		return new Vec2(vec.x, vec.y);
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2f vec) {
		set(vec.x, vec.y);
	}

	public float dir() {
		return (float) Math.atan2(y, x);
	}

	public static float angleBetween(Vector2f v1, Vector2f v2) {
		return (float) Math.acos(v1.dot(v2) / (v1.len() * v2.len()));
	}

	public float cross(Vector2f vec) {
		return x * vec.y - y * vec.x;
	}
}