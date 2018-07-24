package core.application;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends Canvas implements Runnable{
	public static final int UPDATE_RATE = 60;

	private Graphics2D g2d;
	private JFrame frame;
	private boolean running = false;
	private Thread gameThread;
	private String title;
	private Application app;


	Window(int width, int height, String title, Application app){
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		this.title=title;
		this.app = app;

		addKeyListener(Input.instance);
		addMouseListener(Input.instance);
		addMouseMotionListener(Input.instance);
		addMouseWheelListener(Input.instance);
	}


	synchronized void start() {
		if(!running) {
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

	public synchronized void stop() {
		if(running) {
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
		long lastTime = Time.getTime();
		final double ns = Time.SECOND_NANO / (double)UPDATE_RATE;
		double delta = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		requestFocus();
		app.start();
		while (running) {
			long now = Time.getTime();
			delta += (now - lastTime) / ns;
			Time.setDeltaTime(delta / (double)UPDATE_RATE);
			lastTime = now;
			while (delta >= 1) {
				Input.update();
				app.update();

				delta--;
				updates++;
			}
			render();
			if (System.currentTimeMillis() - timer > Time.SECOND_MILLI) {
				timer += Time.SECOND_MILLI;
				frame.setTitle(title + " | " + updates + " fps");
				updates = 0;
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
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		app.render(g2d);
		g2d.dispose();
		bs.show();
	}


}
