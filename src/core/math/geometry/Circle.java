package core.math.geometry;

import core.math.vector.Vector2f;

public class Circle extends Shape {
	private static final long serialVersionUID = -4359696455132395909L;

	private float radius;

	public Circle(Vector2f vec, float radius) {
		super(vec);
		this.radius = radius;
	}

	public Circle(float radius) {
		super();
		this.radius = radius;
	}


	public float getRadius(){
		return radius;
	}
}
