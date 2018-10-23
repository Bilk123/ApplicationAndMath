package core.math.rotation;

import core.math.matrix.Matrix3f;
import core.math.vector.Vector3f;

import java.io.Serializable;

public class Euler implements Serializable {
	private static final long serialVersionUID = -1329400002703595915L;

	private float pitch, roll, yaw;

	public Euler(float roll, float pitch, float yaw) {
		this.pitch = (float)Math.toRadians(pitch);
		this.roll = (float)Math.toRadians(roll);
		this.yaw = (float)Math.toRadians(yaw);
	}

	public Vector3f mul(Vector3f vec) {
		var s1 = (float)Math.sin(pitch);
		var c1 = (float)Math.cos(pitch);
		var s2 = (float)Math.sin(roll);
		var c2 = (float)Math.cos(roll);
		var s3 = (float)Math.sin(yaw);
		var c3 = (float)Math.cos(yaw);

		return new Matrix3f(new float[][]{
				{c2, -c3 * s2, s2 * s3},
				{c1 * s2, c1 * c2 * c3 - s1 * s3, -c3 * s1 - c1 * c2 * s3},
				{s1 * s2, c1 * s3 + c2 * c3 * s1, c1 * c3 - c2 * s1 * s3}
		}).mul(vec);
	}

	public Quaternion4f toQuaterion(){
		var sp = (float)Math.sin(pitch);
		var cp = (float)Math.cos(pitch);
		var sr = (float)Math.sin(roll);
		var cr = (float)Math.cos(roll);
		var sy = (float)Math.sin(yaw);
		var cy = (float)Math.cos(yaw);
		var q = new Quaternion4f();
		q.w = cy * cr * cp + sy * sr * sp;
		q.v.x = cy * sr * cp - sy * cr * sp;
		q.v.y = cy * cr * sp + sy * sr * cp;
		q.v.z = sy * cr * cp - cy * sr * sp;
		return q;
	}

	@Override
	public String toString() {
		return "[ r: "+roll+", p: "+pitch+", y: "+yaw+"]";
	}
}
