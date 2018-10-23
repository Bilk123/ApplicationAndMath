package core.debug;

import core.engine.gameObjects.GameObject;

public class NoColliderException extends Exception {
	private static final long serialVersionUID = -3349283625272182432L;

	private GameObject obj;

	public NoColliderException(GameObject obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return obj.toString()+" has a physics body, but no collider";
	}
}
