package core.application;

import java.awt.event.*;
import java.util.ArrayList;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	public static final int KEYS = 256;
	public static final int MOUSE_BUTTONS =12;
	public static int mx = -1, my = -1;
	public  static float dMx, dMy;

	static Input instance;
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
		for(int i =0;i<MOUSE_BUTTONS;i++){
			if(!isMouseButtonDown(i) && currentButtons.contains(i))
				mouseButtonsUp.add(i);
		}

		mouseButtonsDown.clear();
		for(int i =0;i<MOUSE_BUTTONS;i++){
			if(isMouseButtonDown(i) && !currentButtons.contains(i))
				mouseButtonsDown.add(i);
		}
		currentButtons.clear();
		for(int i =0;i<MOUSE_BUTTONS;i++){
			if(isMouseButtonDown(i))
				currentButtons.add(i);
		}
		dMx = lX - mx;
		dMy = lY - my;
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

	public static boolean getMouseButtonDown(int button){
		return mouseButtonsDown.contains(button);
	}

	public static boolean getMouseButtonUp(int button){
		return mouseButtonsUp.contains(button);
	}

	public static boolean isMouseButtonDown(int button) {
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
		mouseButtons[e.getButton()]=false;
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

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scrolling = true;
		scroll = e.getWheelRotation();
	}
}
