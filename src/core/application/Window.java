package core.application;


import core.application.IO.Input;
import core.math.matrix.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends Canvas implements Runnable {
	private static final int UPDATE_RATE = 60;

	private Graphics2D g2d;
	private JFrame frame;
	private boolean running = false;
	private Thread gameThread;
	private String title;
	private Application app;
	private boolean noLoop;
	private boolean clearBackground = true;
	private Colour backgroundColor = new Colour(0.25f, 0.25f, 0.25f);


	Window(int width, int height, String title, Application app) {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		this.title = title;
		this.app = app;
		addKeyListener(Input.instance);
		addMouseListener(Input.instance);
		addMouseMotionListener(Input.instance);
		addMouseWheelListener(Input.instance);
	}


	synchronized void start() {
		if (!running) {
			frame = new JFrame(title);
			frame.setResizable(false);
			frame.add(this);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.setVisible(true);
			System.out.println("Window loaded: " + title);
			System.out.println("Screen size: " + getWidth() + " : " + getHeight());
			running = true;
			gameThread = new Thread(this, title);
			gameThread.start();

		}
	}

	private synchronized void stop() {
		if (running) {
			running = false;
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		createBufferStrategy(3);
		g2d = (Graphics2D) (getBufferStrategy().getDrawGraphics());
		long lastTime = Time.getTime();
		final double ns = Time.SECOND_NANO / (double) UPDATE_RATE;
		double delta = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		requestFocus();
		app.setup();
		while (running) {
			if (!noLoop) {
				long now = Time.getTime();
				delta += (now - lastTime) / ns;
				Time.setDeltaTime((float) (delta / (float) UPDATE_RATE));
				lastTime = now;
				while (delta >= 1) {
					Input.update();
					render();
					delta--;
					updates++;
				}

				if (System.currentTimeMillis() - timer > Time.SECOND_MILLI) {
					timer += Time.SECOND_MILLI;
					frame.setTitle(title + " | " + updates + " fps");
					updates = 0;
				}
			} else {
				Time.setDeltaTime(0);
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		g2d = (Graphics2D) bs.getDrawGraphics();
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (clearBackground) {
			g2d.setColor(backgroundColor.toAWTColor());
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
		g2d.setColor(Color.WHITE);
		app.draw();
		g2d.dispose();
		bs.show();
	}

	void setBackgroundColor(Colour backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	void noLoop() {
		noLoop = true;
	}

	void enableLoop() {
		noLoop = false;
	}

	void setClearBackground(boolean flag) {
		clearBackground = flag;
	}

	public Graphics2D getGraphics() {
		return g2d;
	}

}
