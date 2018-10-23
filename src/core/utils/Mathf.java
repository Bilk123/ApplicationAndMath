package core.utils;

public class Mathf {

	public static float map(float val, float x0, float x1, float y0, float y1) {
		return (y1 - y0) / (x1 - x0) * (val - x0) + y0;
	}

	public static float sin(float a) {
		return (float) Math.sin(a);
	}

	public static float sin(double a) {
		return (float) Math.sin(a);
	}

	public static float cos(float a) {
		return (float) Math.cos(a);
	}

	public static float cos(double a) {
		return (float) Math.cos(a);
	}


	public static float tan(float a) {
		return (float) Math.tan(a);
	}

	public static float limit(float val, float lim) {
		if (Math.abs(val) > lim) {
			return Math.copySign(lim, val);
		}
		return val;
	}

	public static float clamp(float val, float min, float max) {
		if (val < min) return min;
		else if (val > max) return max;
		else return val;
	}
}
