package core.math.geometry;

import core.math.vector.Vector2f;

public class Rectangle extends Polygon {
	private static final long serialVersionUID = -8571122712764431507L;

	private float w, h;

	public Rectangle(Vector2f cent, float w, float h, float rotation) {
		super(new Vector2f[]{
				new Vector2f(-0.5f, -0.5f),
				new Vector2f(0.5f, -0.5f),
				new Vector2f(0.5f, 0.5f),
				new Vector2f(-0.5f, 0.5f)
		}, cent, rotation);
		this.w = w;
		this.h = h;
	}

	public Rectangle(float w, float h){
		this(new Vector2f(), w,h,0);
	}

	public float getWidth() {
		return w;
	}

	public float getHeight() {
		return h;
	}
}
