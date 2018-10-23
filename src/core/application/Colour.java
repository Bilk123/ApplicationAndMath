package core.application;

import core.utils.Mathf;
import core.math.vector.Vector4f;
import core.math.vector.Vector4i;

import java.awt.*;
import java.io.Serializable;

public class Colour implements Serializable {
	private static final long serialVersionUID = 3148019264660444815L;
	private Vector4f rgba;

	public Colour() {
		rgba = new Vector4f();
	}

	public Colour(float r, float g, float b, float a) {
		rgba = test(r, g, b, a);
	}

	public Colour(float r, float g, float b) {
		this(r, g, b, 1f);
	}

	public Colour(int r, int g, int b, int a) {
		this(r / 255f, g / 255f, b / 255f, a / 255f);
	}

	public Colour(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public Colour(int gs) {
		this(gs, gs, gs);
	}

	public Colour(float gs) {
		this(gs, gs, gs);
	}

	public Colour(Vector4f col) {
		this(col.x, col.y, col.z, col.w);
	}

	public Colour(Vector4i col) {
		this(col.x, col.y, col.z, col.w);
	}

	public static Colour add(Colour c1, Colour c2) {
		return new Colour(keepInRange(c1.rgba.add(c2.rgba)));
	}

	public static Colour mul(Colour c1, Colour c2) {
		return new Colour(keepInRange(c1.rgba.scl(c2.rgba)));
	}

	public Color toAWTColor() {
		return new Color(rgba.x, rgba.y, rgba.z, rgba.w);
	}

	public float red() {
		return rgba.x;
	}

	public float green() {
		return rgba.y;
	}

	public float blue() {
		return rgba.z;
	}

	public float alpha() {
		return rgba.w;
	}

	private static Vector4f keepInRange(Vector4f vec) {
		vec.x = Mathf.clamp(vec.x, 0f, 1f);
		vec.y = Mathf.clamp(vec.y, 0f, 1f);
		vec.z = Mathf.clamp(vec.z, 0f, 1f);
		vec.w = Mathf.clamp(vec.w, 0f, 1f);
		return vec;
	}

	private static Vector4f test(float r, float g, float b, float a) {
		try {
			String msg = "Colour engine out of range:\n\t";
			boolean isBad = false;
			if (r < 0 || r > 1) {
				msg += "r: " + r + "\n\t";
				isBad = true;
			}
			if (g < 0 || g > 1) {
				msg += "g: " + g + "\n\t";
				isBad = true;
			}
			if (b < 0 || b > 1) {
				msg += "b: " + b + "\n\t";
				isBad = true;
			}
			if (a < 0 || a > 1) {
				msg += "a: " + a + "\n\t";
				isBad = true;
			}
			if (isBad) throw new Exception(msg);
			return new Vector4f(r, g, b, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Vector4f();
	}
}
