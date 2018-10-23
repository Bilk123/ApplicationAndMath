package core.application.IO;

import core.math.vector.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	public static final int Q = VK_Q;
	public static final int W = VK_W;
	public static final int E = VK_E;
	public static final int R = VK_R;
	public static final int T = VK_T;
	public static final int Y = VK_Y;
	public static final int U = VK_U;
	public static final int I = VK_I;
	public static final int O = VK_O;
	public static final int P = VK_P;
	public static final int A = VK_A;
	public static final int S = VK_S;
	public static final int D = VK_D;
	public static final int F = VK_F;
	public static final int G = VK_G;
	public static final int H = VK_H;
	public static final int J = VK_J;
	public static final int K = VK_K;
	public static final int L = VK_L;
	public static final int Z = VK_Z;
	public static final int X = VK_X;
	public static final int C = VK_C;
	public static final int V = VK_V;
	public static final int B = VK_B;
	public static final int N = VK_N;
	public static final int M = VK_M;
	public static final int UP = VK_UP;
	public static final int DOWN = VK_DOWN;
	public static final int LEFT = VK_LEFT;
	public static final int RIGHT = VK_RIGHT;

	public static final int KEYS = 256;
	public static final int MOUSE_BUTTONS = 12;
	public static int mx = -1, my = -1;
	public static float dMx, dMy;
	public static Vector2f mouse = new Vector2f();
	public static Vector2f mouseChange = new Vector2f();

	public static Input instance;

	static {
		instance = new Input();
	}

	private static int scroll = 0;
	private static boolean scrolling = false;
	private static boolean[] keys = new boolean[KEYS];

	private static ArrayList<Integer> keysCurrent = new ArrayList<>();
	private static ArrayList<Integer> keysDown = new ArrayList<>();
	private static ArrayList<Integer> keysUp = new ArrayList<>();

	public static boolean[] mouseButtons = new boolean[MOUSE_BUTTONS];
	private static ArrayList<Integer> mouseButtonsDown = new ArrayList<>();
	private static ArrayList<Integer> currentButtons = new ArrayList<>();
	private static ArrayList<Integer> mouseButtonsUp = new ArrayList<>();

	private static float lX, lY;

	public static boolean getKey(int key) {
		return keys[key];
	}

	public static void update() {
		mouse.set(mx,my);
		if (!scrolling) scroll = 0;
		scrolling = false;

		keysUp.clear();
		for (int i = 0; i < KEYS; i++) {
			if (!getKey(i) && keysCurrent.contains(i))
				keysUp.add(i);
		}

		keysDown.clear();
		for (int i = 0; i < KEYS; i++) {
			if (getKey(i) && !keysCurrent.contains(i))
				keysDown.add(i);
		}

		keysCurrent.clear();
		for (int i = 0; i < KEYS; i++) {
			if (getKey(i))
				keysCurrent.add(i);
		}

		mouseButtonsUp.clear();
		for (int i = 0; i < MOUSE_BUTTONS; i++) {
			if (!getMouseButton(i) && currentButtons.contains(i))
				mouseButtonsUp.add(i);
		}

		mouseButtonsDown.clear();
		for (int i = 0; i < MOUSE_BUTTONS; i++) {
			if (getMouseButton(i) && !currentButtons.contains(i))
				mouseButtonsDown.add(i);
		}
		currentButtons.clear();
		for (int i = 0; i < MOUSE_BUTTONS; i++) {
			if (getMouseButton(i))
				currentButtons.add(i);
		}
		dMx = lX - mx;
		dMy = lY - my;
		mouseChange.set(dMx, dMy);
		lX = mx;
		lY = my;
	}

	public static boolean getKeyDown(int keyCode) {
		return keysDown.contains(keyCode);
	}

	public static boolean getKeyUp(int keyCode) {
		return keysUp.contains(keyCode);
	}

	public static int getScroll() {
		return scroll;
	}

	public static boolean isScrolling() {
		return scroll != 0;
	}

	public static boolean getMouseButtonDown(int button) {
		return mouseButtonsDown.contains(button);
	}

	public static boolean getMouseButtonUp(int button) {
		return mouseButtonsUp.contains(button);
	}

	public static boolean getMouseButton(int button) {
		return mouseButtons[button];
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButtons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButtons[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		mouse.set(mx, my);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		mouse.set(mx, my);

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scrolling = true;
		scroll = e.getWheelRotation();
	}
}
