package core.utils;

import core.math.vector.Vector2f;
import core.math.vector.Vector3f;
import core.math.vector.Vector4f;

public class Random {
	private static java.util.Random gen = new java.util.Random();

	private Random() {
	}

	public static float gaussian(float mean, float std) {
		return (float) (gen.nextGaussian() * std + mean);
	}

	public static int range(int lowerBound, int upperBound) {
		return gen.nextInt(upperBound - lowerBound) + lowerBound;
	}

	public static int range(int bound) {
		return gen.nextInt(bound);
	}

	public static float range(float lowerBound, float upperBound) {
		return (upperBound - lowerBound) * gen.nextFloat() + lowerBound;
	}

	public static float range(float bound) {
		return range(0, bound);
	}

	public static boolean chance(float probability) {
		return gen.nextFloat() < probability;
	}

	public static float randomFloat() {
		return gen.nextFloat();
	}


	public static Vector2f randomVec2D() {
		return new Vector2f(range(-1f, 1f), range(-1f, 1f));
	}

	public static Vector2f randomDir2D() {
		return randomVec2D().normalise();
	}

	public static Vector3f randomVec3D() {
		return new Vector3f(range(-1f, 1f), range(-1f, 1f), range(-1f, 1f));
	}

	public static Vector3f randomDir3D() {
		return randomVec3D().normalise();
	}

	public static Vector4f randomVec4D() {
		return new Vector4f(range(-1f, 1f), range(-1f, 1f), range(-1f, 1f), range(-1f, 1f));
	}

	public static Vector4f randomDir4D() {
		return randomVec4D().normalise();
	}
}
