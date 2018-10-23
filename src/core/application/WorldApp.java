package core.application;

import core.application.renderers.Renderer2D;
import core.application.renderers.SwingRenderer;
import core.engine.components.physics2D.Environment;


public class WorldApp extends Application {
	private Environment world;
	private Renderer2D renderer;
	public WorldApp() {
		super(1200, 720, "World game");
		renderer = new SwingRenderer(window);
	}

	@Override
	public void setup() {
		super.setup();
		world = new Environment();
		world.setup(renderer);
	}

	@Override
	public void draw() {
		world.draw();
		world.step();
		renderer.draw(world.getCamera());
	}
}
