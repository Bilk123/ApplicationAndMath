package core.application;


public abstract class Application {

	protected Window window;
	public static float WIDTH, HEIGHT;

	public Application(int width, int height, String title) {
		window = new Window(width, height, title, this);
		window.start();
	}

	public void setup() {
		WIDTH = window.getWidth();
		HEIGHT = window.getHeight();
	}

	public void draw() {

	}

	public Window getWindow() {
		return window;
	}
}
