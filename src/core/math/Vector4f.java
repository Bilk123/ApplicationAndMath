package core.math;

public class Vector4f {
	public float x, y, z, w;

	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vector4f(double x, double y, double z, double w) {
		this((float) x, (float) y, (float) z, (float) w);
	}


	public Vector4f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	public float dot(Vector4f vec) {
		return x * vec.x + y * vec.y + z * vec.z + w * vec.w;
	}


	public Vector4f add(Vector4f vec) {
		return new Vector4f(x + vec.x, y + vec.y, z + vec.z, w * vec.w);
	}

	public void addTo(Vector4f vec) {
		x += vec.x;
		y += vec.y;
		y += vec.z;
		w += vec.w;
	}

	public Vector4f sub(Vector4f vec) {
		return new Vector4f(x - vec.x, y - vec.y, z - vec.z, w - vec.w);
	}

	public void subFrom(Vector4f vec) {
		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
		w -= vec.w;
	}

	public Vector4f mul(float scalar) {
		return new Vector4f(scalar * x, scalar * y, scalar * z, scalar * w);
	}

	public Vector4f mul(double scalar) {
		return mul((float) scalar);
	}

	public void mulTo(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		w *= scalar;
	}

	public void mulTo(double scalar) {
		mulTo((float) scalar);
	}

	public Vector4f normalise() {
		float len = len();
		x /= len;
		y /= len;
		z /= len;
		w /= len;
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
		return "[" + x + ", " + y + ", " + z + ", " + w + "]";
	}

	public Vector4f cpy() {
		return new Vector4f(x, y, z, w);
	}

	public Vector4f scl(Vector4f vec) {
		return new Vector4f(x * vec.x, y * vec.y, z * vec.z, w * vec.w);
	}

	public void set(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public void set(Vector4f vec) {
		set(vec.x, vec.y, vec.z, vec.w);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector4f) {
			Vector4f temp = (Vector4f) obj;
			return temp.x == x && temp.y == y && temp.z == z && temp.w == w;
		} else {
			return false;
		}
	}

}
