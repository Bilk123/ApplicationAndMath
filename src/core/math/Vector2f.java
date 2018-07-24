package core.math;

public class Vector2f {

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

	public Vector2f add(Vector2f vec) {
		return new Vector2f(x + vec.x, y + vec.y);
	}

	public void addTo(Vector2f vec) {
		x += vec.x;
		y += vec.y;
	}

	public Vector2f sub(Vector2f vec) {
		return new Vector2f(x - vec.x, y - vec.y);
	}

	public void subFrom(Vector2f vec) {
		x -= vec.x;
		y -= vec.y;
	}

	public Vector2f mul(float scalar) {
		return new Vector2f(scalar * x, scalar * y);
	}

	public Vector2f mul(double scalar) {
		return mul((float) scalar);
	}

	public void mulTo(float scalar) {
		x *= scalar;
		y *= scalar;
	}

	public void mulTo(double scalar) {
		mulTo((float) scalar);
	}

	public Vector2f normalise() {
		float len = len();
		x /= len;
		y /= len;
		return this;
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
		return "[" + x + ", " + y + "]";
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

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2f vec) {
		set(vec.x, vec.y);
	}
}
