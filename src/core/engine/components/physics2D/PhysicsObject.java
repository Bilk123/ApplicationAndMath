package core.engine.components.physics2D;

import core.debug.NoColliderException;
import core.engine.components.Component;
import core.math.vector.Vector2f;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import java.io.Serializable;
import java.util.ArrayList;

public class PhysicsObject extends Component {
	private static final long serialVersionUID = 5484785163378318962L;

	private transient Body body;

	public void applyForce(Vector2f force) {
		body.applyForceToCenter(Vector2f.convert(force));
	}

	public enum Type implements Serializable {
		STATIC, KINEMATIC, DYNAMIC
	}

	private Type type;

	public PhysicsObject(Type type) {
		super(false);
		this.type = type;
	}

	@Override
	public void init() {
		try {
			ArrayList<Collider> colliders = gameObject.getComponents(Collider.class);
			if (colliders.size() == 0) {
				throw new NoColliderException(gameObject);
			}
			BodyType bt;
			switch (type) {
				case STATIC:
					bt = BodyType.STATIC;
					break;
				case DYNAMIC:
					bt = BodyType.DYNAMIC;
					break;
				case KINEMATIC:
					bt = BodyType.KINEMATIC;
					break;
				default:
					bt = BodyType.DYNAMIC;
					break;
			}

			createBody(bt);
			for (Collider collider : colliders) {
				body.createFixture(collider.getBox2DShape(), 1f);
			}
		} catch (NoColliderException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		gameObject.transform.setTranslation(getPosition());
		gameObject.transform.setRotation(getRotation());
	}

	private float getRotation() {
		return (float) Math.toDegrees(body.getAngle());
	}

	protected void createBody(BodyType type) {
		var def = new BodyDef();
		def.type = type;
		body = Environment.instance.getWorld().createBody(def);
	}


	public boolean contains(Vector2f vec) {
		var a = body.getFixtureList();
		if (a != null)
			return a.testPoint(Vector2f.convert(vec));
		else return false;
	}

	public Vector2f getVelocity() {
		return Vector2f.convert(body.getLinearVelocity());
	}

	public Vector2f getPosition() {
		return Vector2f.convert(body.getPosition());
	}

	public Body getBox2DBody() {
		return body;
	}
}
