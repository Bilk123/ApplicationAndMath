package core.engine.components.rendeables;

import core.application.IO.Input;
import core.math.matrix.Matrix3f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Sprite extends Renderable{
	private static final long serialVersionUID = 7135620203890319943L;

	private Image img;
	private Matrix3f scaler;

	public Sprite(String path){
		super();
		try {
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
			scaler = new Matrix3f(new float[][]{
					{1f / img.getWidth(null), 0, -0.5f},
					{0, 1f / img.getHeight(null), -0.5f},
					{0, 0, 1f}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImg(){
		return img;
	}

	public Matrix3f getScaler() {
		return scaler;
	}
}
