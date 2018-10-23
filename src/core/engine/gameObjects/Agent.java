package core.engine.gameObjects;

import core.application.Colour;
import core.engine.components.physics2D.PhysicsObject.Type;
import core.engine.components.physics2D.PolygonCollider;
import core.engine.components.rendeables.RenderableShape;
import core.utils.Mathf;
import core.math.geometry.Polygon;
import core.math.vector.Vector2f;
import core.engine.components.physics2D.PhysicsObject;

import java.util.ArrayList;


public class Agent extends GameObject {
	private static final long serialVersionUID = 8658189918805754089L;

	protected float maxSpeed;
	protected float maxForce;
	private PhysicsObject body;

	public Agent() {
		super();
		this.maxSpeed = 10f;
		this.maxForce = 10f;
	}

	public Agent set(float maxForce, float maxSpeed) {

		this.maxSpeed = maxSpeed;
		this.maxForce = maxForce;
		return this;
	}

	@Override
	public void init() {
		super.init();
		var shape = new Polygon(new Vector2f[]{
				new Vector2f(0.3f, 0),
				new Vector2f(-0.3f, -0.2f),
				new Vector2f(-0.3f, 0.2f)});

		var rend = new RenderableShape(shape);
		rend.setColour(new Colour(0.5f, 0.5f, 0.5f));
		addComponent(rend);
		addComponent(new PolygonCollider(shape));
		addComponent(new PhysicsObject(Type.DYNAMIC));
		setupComponents();

		body = getComponent(PhysicsObject.class);
	}

	protected Vector2f steer(Vector2f desired) {
		var force =
				desired.sub(body.getVelocity());
		force.limit(maxForce);
		body.applyForce(force);
		transform.setRotation((float)Math.toDegrees(body.getVelocity().dir()));
		return force;
	}

	public Vector2f seek(Vector2f target) {
		var pos = body.getPosition();
		var desired = target.sub(pos);
		var d = desired.len();
		var r = 2f;
		if (d < r)
			desired.setMag(Mathf.map(d, 0, r, 0, maxSpeed));
		else
			desired.setMag(maxSpeed);
		return steer(desired);
	}

	public Vector2f seek(PhysicsObject obj) {
		return seek(obj.getPosition());
	}

	public Vector2f followPath(Path path) {
		var pos = body.getPosition();
		var vel = body.getVelocity();
		var predict = pos.add(vel.setMag(2));
		var nearest = path.nearestSegment(predict);
		Vector2f target;
		if (nearest == null) {
			var p = path.nearestPoint(predict);
			var l = p.sub(predict).len();
			if (l > path.radius) {
				target = p;
			} else {
				target = predict;

			}
		} else {
			target = nearest.getNormal(predict);
			if (target.sub(predict).len() < path.radius) {
				target = predict;
			}

		}
		return seek(target);
	}

	public Vector2f align(ArrayList<Agent> agents, float dist) {
		Vector2f desired = new Vector2f();
		int count = 0;
		for (Agent e : agents) {
			if (e != this) {
				if (e.body.getPosition().sub(body.getPosition()).len() < dist) {
					desired.addEq(e.body.getVelocity()).setMag(maxSpeed);
					count++;
				}
			}
		}
		if (count > 0) {
			desired.mulEq(1f / count);
			return steer(desired);
		}
		return new Vector2f();
	}

	public Vector2f separate(ArrayList<Agent> agents, float dist) {
		Vector2f desired = new Vector2f();
		int count = 0;
		for (Agent e : agents) {
			if (e != this) {
				var d = body.getPosition().sub(e.body.getPosition());
				if (d.len() < dist) {
					d.setMag(1 / d.len());
					desired.addEq(d);
					count++;
				}
			}
		}
		if (count > 0) {
			desired.mulEq(1f / count);
			desired.setMag(maxSpeed);
			return steer(desired);
		}
		return new Vector2f();
	}

	@Override
	protected void update() {
		super.update();

	}
}
