package core.engine.components.physics2D;

import core.engine.components.Component;
import core.engine.gameObjects.GameObject;
import core.math.geometry.Shape;
import core.math.vector.Vector2f;

public abstract class Collider extends Component {
	private static final long serialVersionUID = -4135188588765310997L;

	protected Shape shape;

	public Collider(Shape shape) {
		super(true);
		this.shape = shape;
	}

	public abstract org.jbox2d.collision.shapes.Shape getBox2DShape();

}
