package core.engine.gameObjects;

import core.application.Application;
import core.application.IO.Input;
import core.application.Time;
import core.engine.components.physics2D.Environment;
import core.math.vector.Vector2f;

import java.awt.event.MouseEvent;

public class CameraController extends GameObject {
	private static final long serialVersionUID = 8148282000840114893L;

	private float speed;
	private Camera camera;

	public CameraController() {
		super();
	}

	public CameraController attach(float speed, Camera camera) {
		this.camera = camera;
		this.speed = speed;
		return this;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void init() {
		super.init();
		speed = 1f;
		camera = Environment.instance.getCamera();
	}

	@Override
	protected void update() {

		if (Input.isScrolling())
			zoom();

		if (Input.getMouseButton(MouseEvent.BUTTON2))
			move();

		if (Input.getMouseButton(MouseEvent.BUTTON3))
			rotateWithMouse();


	}

	private void rotateWithMouse() {
		var cent = new Vector2f(Application.WIDTH / 2, Application.HEIGHT / 2);
		var mouse = new Vector2f(Input.mx, Input.my);
		var next = mouse.add(new Vector2f(Input.dMx, Input.dMy));
		var d1 = mouse.sub(cent).dir();
		var d2 = next.sub(cent).dir();
		camera.transform.rotate((float) (Math.toDegrees(d1 - d2)));
	}

	private void move(){
		var vec = new Vector2f(Input.dMx, -Input.dMy).mul(camera.scalarScreenToWorld(1f));
		camera.transform.translate(vec.rotate(camera.transform.getRotation()));
	}

	private void zoom(){
		camera.setSize(camera.getSize() + Input.getScroll() * Time.getDeltaTime() * (10 * speed));
	}


}
