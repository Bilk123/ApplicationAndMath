package core.math.rotation;

import core.math.matrix.Matrix4f;
import core.math.vector.Vector3f;

import java.io.Serializable;

import static java.lang.Math.*;

public class Quaternion4f implements Serializable {
	private static final long serialVersionUID = 4761316378906981113L;

	float w;
	Vector3f v;


	public Quaternion4f(Vector3f n, float a) {
		a = (float) toRadians(a);
		w = (float) cos(a / 2);
		v = n.mul(sin(a / 2));
	}

	public Quaternion4f() {
		w = 0;
		v = new Vector3f();
	}

	public float len2() {
		return w * w + v.dot(v);
	}

	public float len() {
		return (float) sqrt(len2());
	}

	public Quaternion4f normailise() {
		float len = len();
		w /= len;
		v = v.mul(1 / len);
		return this;
	}

	public Quaternion4f mul(Quaternion4f q) {
		/*(Wa*Wb - Vb*Va, Wb * Va + Wa * Vb + Va x Vb)*/
		Quaternion4f q1 = new Quaternion4f();
		q1.w = w * q.w - v.dot(q.v);
		q1.v = ((v.mul(q.w)).add(q.v.mul(w))).add(v.cross(q.v));
		return q1;
	}

	public Vector3f mul(Vector3f V) {
		Quaternion4f p = new Quaternion4f();
		p.w = 0;
		p.v = V;
		Vector3f vcV = v.cross(V);
		return (V.add((vcV.mul(2 * w)))).add(((v.cross(vcV)).mul(2)));
	}

	public Matrix4f toMatrix4F() {
		return new Matrix4f(new float[][]{
				{1 - 2 * v.z * v.z - 2 * v.y * v.y, -2 * v.z * w + 2 * v.y * v.x, 2 * v.y * w + 2 * v.z * v.x, 0},
				{2 * v.x * v.y + 2 * w * v.z, 1 - 2 * v.z * v.z - 2 * v.x * v.x, 2 * v.z * v.y - 2 * v.x * w, 0},
				{2 * v.x * v.z - 2 * w * v.y, 2 * v.y * v.z + 2 * w * v.x, 1 - 2 * v.y * v.y - 2 * v.x * v.x, 0},
				{0, 0, 0, 1}}
		);
	}

	public Quaternion4f inverse() {
		Quaternion4f q = new Quaternion4f();
		q.w = w;
		q.v = v.mul(-1);
		return q;
	}

	@Override
	public String toString() {
		return "[" + w + ", " + v.x + ", " + v.y + ", " + v.z + "]";
	}

	public Euler toEuler() {
		var sr_cp = 2 * (w * v.x + v.y * v.z);
		var cr_cp = 1 - 2 * (v.x * v.x + v.y * v.y);
		var roll = (float) toDegrees(atan2(sr_cp, cr_cp));
		var sp = 2 * (w * v.y - v.z * v.x);
		float pitch;
		if (Math.abs(sp) >= 1) {
			pitch = copySign((float) Math.PI / 2, sp);
		} else {
			pitch = (float) toDegrees(asin(sp));
		}
		var sy_cp = 2 * (w * v.z + v.x * v.y);
		var cy_cp = 1 - 2 * (v.y * v.y + v.z * v.z);
		var yaw = (float) toDegrees(atan2(sy_cp, cy_cp));
		return new Euler(roll, pitch, yaw);
	}
}
