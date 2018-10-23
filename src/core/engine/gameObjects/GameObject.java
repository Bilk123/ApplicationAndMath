package core.engine.gameObjects;

import core.application.renderers.Renderer2D;
import core.debug.Debug;
import core.engine.components.Component;
import core.engine.components.rendeables.Renderable;
import core.engine.components.Transform;

import java.io.Serializable;
import java.util.ArrayList;

public class GameObject implements Serializable {
	private static final long serialVersionUID = 8513210973593499208L;

	public interface Initializer {
		void init(GameObject gameObject);
	}

	private ArrayList<Component> components;
	private String name;
	private String filePath;

	public Transform transform;

	public GameObject() {
		components = new ArrayList<>();

	}

	public <T extends Component> T getComponent(Class<T> component) {
		for (int i = 0; i < components.size(); i++) {
			var c = components.get(i);
			if (component.isInstance(c)) {
				return (T) c;
			}
		}
		return null;
	}

	public <T extends Component> ArrayList<T> getComponents(Class<T> component) {
		var list = new ArrayList<T>();
		for (int i = 0; i < components.size(); i++) {
			var c = components.get(i);
			if (component.isInstance(c))
				list.add((T) c);
		}
		return list;
	}

	public void addComponent(Component component) {
		if (component.multipleAllowed() || !hasComponent(component.getClass())) {
			component.setGameObject(this);
			components.add(component);
		} else {
			Debug.warning("GameObject has component already");
		}
	}

	public boolean hasComponent(Class<? extends Component> componentClass) {
		for (Component c : components) {
			if (componentClass.isInstance(c)) return true;
		}
		return false;
	}

	public void init() {
		transform = new Transform();
		addComponent(transform);
	}

	protected void update() {
		for (Component c : components) {
			c.update();
		}
	}

	public void draw(Renderer2D renderer2D) {
		update();
		for (Renderable r : getComponents(Renderable.class)) {
			r.draw(renderer2D);
		}
	}

	@Override
	public String toString() {
		return name == null ? getClass().getSimpleName() : name;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	protected void setupComponents() {
		for (Component c : components) {
			c.init();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
