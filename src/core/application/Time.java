package core.application;

public class Time {
	public static final int SECOND_NANO = 1000000000;//1 second in nano-seconds
	public static final int SECOND_MICRO = 1000000;
	public static final int SECOND_MILLI = 1000;
	private static double deltaTime = 0;//the time to update and render a frame

	public static long getTime() {
		return System.nanoTime();
	}

	public static double getDeltaTime() {
		return deltaTime;
	}

	public static void setDeltaTime(double deltaTime) {
		Time.deltaTime = deltaTime;
		//System.out.println("core.Time.setDeltaTime: " + deltaTime);
	}
}
