package core.application;

import java.awt.*;

public abstract class Application {
	private Window window;

	public Application(int width, int height, String title){
		window = new Window(width, height,  title, this);
		window.start();
	}

	public Window getWindow() {
		return window;
	}

	public void start(){}
	public void update(){}
	public void render(Graphics2D g2d){}
}
