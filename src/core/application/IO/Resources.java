package core.application.IO;

import core.engine.gameObjects.*;
import core.utils.Utils;

import java.util.HashMap;

public class Resources {
	private static HashMap<Class<? extends GameObject>, GameObject> prefabs;
	private static int id = 0;

	static {
		prefabs = new HashMap<>();
		add(createPrefab(new Agent()));
		add(createPrefab(new Path()));
		add(createPrefab(new Camera()));
		add(createPrefab(new CameraController()));
	}

	public static void add(GameObject gameObject) {
		prefabs.put(gameObject.getClass(), gameObject);
	}

	public static <T extends GameObject> T find(Class<T> name) {
		return (T) prefabs.get(name);
	}

	private static GameObject createPrefab(GameObject object) {
		String filePath = "data/" + object.toString() + (id++) + ".gbj";
		System.out.println(filePath);
		Utils.serialize(object, filePath);
		object.setFilePath(filePath);
		return object;
	}
}
