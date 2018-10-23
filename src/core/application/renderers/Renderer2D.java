package core.application.renderers;

import core.application.Window;
import core.engine.components.rendeables.Renderable;
import core.engine.components.Transform;
import core.engine.gameObjects.Camera;

import java.util.ArrayDeque;
import java.util.Stack;

public abstract class Renderer2D {
	ArrayDeque<Renderable> renderables;
	Window window;

	Renderer2D(Window window) {
		this.window = window;
		renderables = new ArrayDeque<>();
	}

	public void push(Renderable renderable) {
		renderables.addLast(renderable);
	}

	public abstract void draw(Camera camera);


	public Window getWindow() {
		return window;
	}
}
