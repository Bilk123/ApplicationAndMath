package core.engine.components.physics2D;

import core.math.geometry.Rectangle;
import core.math.vector.Vector2f;
import org.jbox2d.collision.shapes.PolygonShape;


public class BoxCollider extends Collider {

	private static final long serialVersionUID = 6343993663127232363L;

	public BoxCollider() {
		super(new Rectangle(1f,1f));
	}

	@Override
	public org.jbox2d.collision.shapes.Shape getBox2DShape() {
		var poly = new PolygonShape();
		var r = (Rectangle)shape;
		poly.setAsBox(r.getWidth(), r.getHeight(), Vector2f.convert(r.center()),
				(float)Math.toRadians(r.getRotation()));
		return poly;
	}
}
