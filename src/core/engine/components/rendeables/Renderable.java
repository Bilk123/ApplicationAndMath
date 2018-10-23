package core.engine.components.rendeables;

import core.application.renderers.Renderer2D;
import core.engine.components.Component;
import core.engine.components.Transform;
import core.engine.gameObjects.GameObject;
import core.math.matrix.Matrix3f;
import core.math.vector.Vector2f;

public abstract class Renderable extends Component {
	private static final long serialVersionUID = -6256636622203943012L;

	public Transform transform;

	public Renderable() {
		super(true);
		transform = new Transform();
	}

	public Transform getTransform() {
		return transform;
	}

	public void draw(Renderer2D renderer2D){
		renderer2D.push(this);
	}
}
