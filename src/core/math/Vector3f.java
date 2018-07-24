package core.math;

public class Vector3f {
	public float x, y, z;

	public static final Vector3f RIGHT = new Vector3f(1, 0, 0);
	public static final Vector3f LEFT = new Vector3f(-1, 0, 0);
	public static final Vector3f UP = new Vector3f(0, 1, 0);
	public static final Vector3f DOWN = new Vector3f(0, -1, 0);
	public static final Vector3f FORWARD = new Vector3f(0, 0, 1);
	public static final Vector3f BACKWARDS = new Vector3f(0, 0, -1);
	public static final Vector3f ZERO = new Vector3f(0, 0, 0);

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(double x, double y, double z) {
		this((float) x, (float) y, (float) z);
	}

	public Vector3f(float x, float y) {
		this(x, y, 0);
	}

	public Vector3f(double x, double y) {
		this(x, y, 0);
	}

	public Vector3f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public float dot(Vector3f vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}

	public Vector3f cross(Vector3f vec) {
		return new Vector3f(
				y * vec.z - z * vec.y,
				-x * vec.z + z * vec.x,
				x * vec.y - y * vec.x);
	}

	public Vector3f add(Vector3f vec) {
		return new Vector3f(x + vec.x, y + vec.y, z + vec.z);
	}

	public void addTo(Vector3f vec) {
		x += vec.x;
		y += vec.y;
		y += vec.z;
	}

	public Vector3f sub(Vector3f vec) {
		return new Vector3f(x - vec.x, y - vec.y, z - vec.z);
	}

	public void subFrom(Vector3f vec) {
		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
	}

	public Vector3f mul(float scalar) {
		return new Vector3f(scalar * x, scalar * y, scalar * z);
	}

	public Vector3f mul(double scalar) {
		return mul((float) scalar);
	}

	public void mulTo(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}

	public void mulTo(double scalar) {
		mulTo((float) scalar);
	}

	public Vector3f normalise() {
		float len = len();
		x /= len;
		y /= len;
		z /= len;
		return this;
	}

	public float len() {
		return (float) Math.sqrt(dot(this));
	}

	public float len2() {
		return dot(this);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	public Vector3f cpy() {
		return new Vector3f(x, y, z);
	}

	public Vector3f scl(Vector3f vec) {
		return new Vector3f(x * vec.x, y * vec.y, z * vec.z);
	}

	public Vector2f toVector2f() {
		return new Vector2f(x, y);
	}

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(Vector3f vec) {
		set(vec.x, vec.y, vec.z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector3f) {
			Vector3f temp = (Vector3f) obj;
			return temp.x == x && temp.y == y && temp.z == z;
		} else {
			return false;
		}
	}
}
