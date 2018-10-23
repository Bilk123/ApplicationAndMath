package core.engine.components;

import core.engine.components.physics2D.PhysicsObject;
import core.engine.gameObjects.GameObject;
import core.math.matrix.Matrix;
import core.math.matrix.Matrix3f;
import core.math.vector.Vector2f;
import core.utils.Mathf;

public class Transform extends Component {
	private static final long serialVersionUID = 9208612061228836554L;

	private Vector2f translation;
	private float rotation;

	public Transform(Vector2f position, float rotation) {
		super(false);
		this.translation = position;
		this.rotation = rotation;
		updatePhysics();
	}


	public Transform(Vector2f position) {
		this(position, 0);
		updatePhysics();
	}

	public Transform() {
		this(new Vector2f());
	}

	public Matrix3f getMat() {
		float a = (float) Math.toRadians(rotation);
		float sn = Mathf.sin(a);
		float cs = Mathf.cos(a);
		return new Matrix3f(new float[][]{
				{cs, -sn, translation.x},
				{sn, cs, translation.y},
				{0, 0, 1}
		});
	}

	public Matrix3f getInverse() {
		return Matrix3f.initRotation(rotation).inverse().mul(Matrix3f.initTranslation(translation.x, translation.y).inverse());
	}

	public void translate(Vector2f translation) {
		this.translation.addEq(translation);
		updatePhysics();
	}

	public void translate(float x, float y) {
		translation.addEq(new Vector2f(x, y));
		updatePhysics();
	}

	public void rotate(float angle) {
		rotation += angle;
		updatePhysics();
	}

	public void rotateRad(float angle) {
		rotation += Math.toDegrees(angle);
		updatePhysics();
	}

	public void setTranslation(Vector2f translation) {
		this.translation = translation;
		updatePhysics();
	}

	public void setRotation(float ang) {
		rotation = ang;
		updatePhysics();
	}

	public void setRotationRad(float ang) {
		rotation = (float) Math.toDegrees(ang);
		updatePhysics();
	}

	public Vector2f getTranslation() {
		return translation;
	}

	public float getRotation() {
		return rotation;
	}

	private void updatePhysics() {
		if (gameObject == null) return;

		var body = gameObject.getComponent(PhysicsObject.class);
		if (body != null) {
			body.getBox2DBody().setTransform(Vector2f.convert(translation), rotation);
		}
	}
}
