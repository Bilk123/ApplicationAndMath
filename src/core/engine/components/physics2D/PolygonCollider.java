package core.engine.components.physics2D;

import core.engine.components.physics2D.Collider;
import core.math.geometry.Polygon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;

public class PolygonCollider extends Collider {
	private static final long serialVersionUID = 1627731707497414185L;

	public PolygonCollider(Polygon shape) {
		super(shape);
	}

	@Override
	public Shape getBox2DShape() {
		var ps = new PolygonShape();
		var vecs = ((Polygon)shape).toVec2();
		ps.set(vecs, vecs.length);
		return ps;
	}
}
