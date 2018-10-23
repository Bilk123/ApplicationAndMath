package core.debug;

public class Debug {

	public static void log(String msg) {
		System.out.println(getStarter("LOG") + msg);
	}

	public static void warning(String msg) {
		System.out.println(getStarter("WARNING") + msg);
	}

	public static void error(String msg) {
		System.err.println(getStarter("ERROR") + msg);
	}

	private static String getStarter(String start) {
		StackTraceElement l = new Exception().getStackTrace()[2];

		return "[" + start + "]" + l.getClassName() + ":" + l.getMethodName() + ":" + l.getLineNumber() + ": ";
	}


}
