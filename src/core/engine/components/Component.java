package core.engine.components;

import core.debug.Debug;
import core.engine.gameObjects.GameObject;

import java.io.Serializable;

public abstract class Component implements Serializable {
	private static final long serialVersionUID = 6076079524112645575L;

	public transient GameObject gameObject;
	protected boolean multipleAllowed;

	public Component(boolean multipleAllowed) {
		this.multipleAllowed = multipleAllowed;
	}

	public void update() {

	}

	public void init() {

	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}


	public boolean multipleAllowed() {
		return multipleAllowed;
	}
}
