package core.engine.components.physics2D;

import core.application.IO.Input;
import core.application.IO.Resources;
import core.application.renderers.Renderer2D;
import core.engine.gameObjects.Camera;
import core.engine.gameObjects.GameObject;
import core.engine.gameObjects.GameObjectTypes;
import core.math.vector.Vector2f;
import core.utils.Utils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import static core.engine.gameObjects.GameObjectTypes.*;

public class Environment implements Serializable {


	private static final long serialVersionUID = -8886588470235326108L;

	private transient World world;
	public static Environment instance;
	private ArrayList<GameObject> gameObjects;
	private Camera camera;
	private Renderer2D renderer;

	public Environment() {
		if (instance == null) {
			world = new World(new Vec2(0, 0));
			instance = this;
		}
		gameObjects = new ArrayList<>();
	}

	public void step() {
		world.step(1 / 60f, 8, 2);
	}

	public World getWorld() {
		return world;
	}


	public void setup(Renderer2D renderer2D) {
		renderer = renderer2D;
		camera = instantiate(Camera).set(5f, renderer2D.getWindow());
		instantiate(CameraController).setSpeed(2f);
	}

	public void draw() {
		for (GameObject obj : gameObjects) {
			obj.draw(renderer);
		}
		var pointer = camera.screenToWorld().mul(Input.mouse);

		if (Input.getMouseButtonDown(MouseEvent.BUTTON1))
			instantiate(Agent, pointer, 0);


		for (var a : findGameObjects(Agent)) {
			a.seek(pointer);
		}

	}


	public <T extends GameObject> T instantiate(Class<T> object, Vector2f position, float rotation) {
		var gameObject = Resources.find(object);
		if (gameObject.getFilePath() != null) {
			var obj = (T) Utils.deserialize(gameObject.getFilePath());
			obj.init();
			obj.transform.setTranslation(position);
			obj.transform.setRotation(rotation);
			gameObjects.add(obj);
			return obj;
		}
		return null;
	}

	public <T extends GameObject> T instantiate(Class<T> object) {
		return instantiate(object, new Vector2f(), 0);
	}

	public Camera getCamera() {
		return camera;
	}

	public <T extends GameObject> ArrayList<T> findGameObjects(Class<T> obj) {
		var temp = new ArrayList<T>();
		for (GameObject c : gameObjects) {
			if (obj.isInstance(c))
				temp.add((T) c);
		}
		return temp;
	}

}
