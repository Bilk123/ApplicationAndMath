package core.math.geometry;

import core.engine.components.Transform;
import core.math.vector.Vector2f;
import org.jbox2d.common.Vec2;

public class Polygon extends Shape {
	private static final long serialVersionUID = -8644724298516118962L;

	private Vector2f[] points;

	private float rotation;

	public Polygon(Vector2f[] points, Vector2f cent, float rotation) {
		this.points = points;
		this.cent = cent;
		this.rotation = rotation;
	}

	public Polygon(Vector2f[] points, Vector2f cent) {
		this(points, cent, 0);
	}

	public Polygon(Vector2f[] points) {
		this(points, new Vector2f());
	}

	public Vec2[] toVec2() {
		var temp = new Vec2[points.length];
		for (int i = 0; i < points.length; i++) {
			temp[i] = Vector2f.convert(points[i]);
		}
		return temp;
	}


	public float getRotation() {
		return rotation;
	}

	public Vector2f[] getPoints() {
		return points;
	}
}
