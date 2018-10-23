package core.engine.gameObjects;

import core.application.Window;
import core.math.matrix.Matrix3f;
import core.utils.Mathf;

public class Camera extends GameObject {
	private static final long serialVersionUID = 4410574102626493696L;

	private float size;
	private float ar;
	private float width, height;
	private Matrix3f projection, windowScale;
	private Matrix3f invWindowScale, invProjection;


	public Camera() {
		super();
	}

	public Camera set(float size, Window window) {
		this.size = size;
		this.width = window.getWidth();
		this.height = window.getHeight();
		this.ar = width / height;
		setProjection();
		windowScale = new Matrix3f(new float[][]{
				{width / 2, 0, width / 2},
				{0, -height / 2, height / 2},
				{0, 0, 1}
		});
		invWindowScale = windowScale.inverse();
		return this;
	}

	private void setProjection() {
		var r = size * ar;
		var l = -size * ar;
		var t = size;
		var b = -size;
		projection = new Matrix3f(new float[][]{
				{2 / (r - l), 0, -(r + l) / (r - l)},
				{0, 2 / (t - b), -(t + b) / (t - b)},
				{0, 0, 1}
		});
		invProjection = projection.inverse();
	}

	public void setSize(float size) {
		this.size = Mathf.clamp(size,1f,30f);
		setProjection();
	}

	public float getSize() {
		return size;
	}

	public Matrix3f worldToScreen() {
		return windowScale.mul(projection).mul(transform.getInverse());
	}

	public Matrix3f screenToWorld() {
		return transform.getMat().mul(invProjection.mul(invWindowScale));
	}

	public float scalarWorldToScreen(float val) {
		return val * height / (2 * size);
	}

	public float scalarScreenToWorld(float val) {
		return val * (2 * size) / height;
	}

	public float getAspectRatio() {
		return ar;
	}
}
