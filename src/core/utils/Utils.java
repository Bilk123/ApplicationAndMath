package core.utils;

import java.io.*;

public class Utils {

	private Utils() {
	}



	public static void serialize(Object obj, String fileName) {
		try (var oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object deserialize(String fileName) {
		try (var ois = new ObjectInputStream(new FileInputStream(fileName))) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
