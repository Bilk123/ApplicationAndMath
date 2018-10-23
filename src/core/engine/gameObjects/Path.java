package core.engine.gameObjects;

import core.math.geometry.Line;
import core.math.vector.Vector2f;


public class Path extends GameObject {
	private static final long serialVersionUID = -8300803830683012429L;
	private Vector2f[] points;
	float radius;

	public Path() {
		super();
	}

	public Path create(Vector2f[] points, float radius) {

		this.points = points;
		this.radius = radius;

		return this;
	}

	public Line nearestSegment(Vector2f pos) {

		Line nearest = null;

		var d = 1000000f;
		for (int i = 0; i < points.length - 1; i++) {
			var l = new Line(points[i], points[i + 1]);
			var n = l.getNormal(pos);
			var c = l.containsPoint(n);
			var len = pos.sub(n).len();
			if (len < d) {
				if (c) {
					d = len;
					nearest = l;
				}
			}
		}
		return nearest;
	}

	public Vector2f nearestPoint(Vector2f pos) {
		var d = 100000f;
		Vector2f nearest = null;
		for (Vector2f point : points) {
			var a = pos.sub(point);
			var len = a.len();
			if (len < d) {
				d = len;
				nearest = point;
			}
		}
		return nearest;
	}
}
