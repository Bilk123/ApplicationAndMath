package core.application.renderers;

import core.application.IO.Input;
import core.application.Window;
import core.engine.components.rendeables.RenderableShape;
import core.engine.components.rendeables.Sprite;
import core.engine.gameObjects.Camera;
import core.math.geometry.Circle;
import core.math.matrix.Matrix3f;
import core.math.vector.Vector2f;
import core.math.vector.Vector2i;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SwingRenderer extends Renderer2D {

	public SwingRenderer(Window window) {
		super(window);
	}

	private Camera camera;
	private Matrix3f worldToScreen, screenToWorld;

	@Override
	public void draw(Camera camera) {


		this.camera = camera;
		worldToScreen = camera.worldToScreen();
		screenToWorld = camera.screenToWorld();
		drawGrid();


		while (renderables.size() > 0) {
			var r = renderables.removeFirst();
			if (r instanceof Sprite) {
				drawSprite((Sprite) r);
			} else if (r instanceof RenderableShape)
				drawPrimitive((RenderableShape) r);

		}
	}

	private void drawGrid() {

		var p = camera.getSize() * camera.getAspectRatio();
		p *= 1.2f;
		var a = camera.transform.getMat().mul(new Vector2f());
		int x = (int) (a.x);
		int y = (int) (a.y);

		for (int i = (int) -p; i < (int) p; i++) {
			for (int j = (int) -p; j < (int) p; j++) {
				var t1 = worldToScreen.mul(new Vector2f(j + x, i + y));
				var t2 = worldToScreen.mul(new Vector2f(j + 1 + x, i + y));
				var t3 = worldToScreen.mul(new Vector2f(j + x, i + 1 + y));
				setColour(x + j);
				window.getGraphics().drawLine((int) t1.x, (int) t1.y, (int) t3.x, (int) t3.y);

				setColour(y + i);
				window.getGraphics().drawLine((int) t1.x, (int) t1.y, (int) t2.x, (int) t2.y);


			}
		}
	}

	private void setColour(int i) {

		if (i % 10 == 0) window.getGraphics().setColor(new Color(0.65f, 0.65f, 0.65f));
		else if (i % 5 == 0) window.getGraphics().setColor(new Color(0.5f, 0.5f, 0.5f));
		else window.getGraphics().setColor(new Color(0.35f, 0.35f, 0.35f));
	}

	private void drawSprite(Sprite s) {
		var af = new AffineTransform();
		var prep = s.getScaler();
		prep = s.transform.getMat().mul(prep);
		if (s.gameObject != null)
			prep = s.gameObject.transform.getMat().mul(prep);
		prep = worldToScreen.mul(prep);
		var r = prep.rawMat();
		af.setTransform(r[0][0], r[1][0], r[0][1], r[1][1], r[0][2], r[1][2]);
		window.getGraphics().drawImage(s.getImg(), af, null);
	}

	private void drawPrimitive(RenderableShape pr) {
		window.getGraphics().setColor(pr.getColour().toAWTColor());
		var t1 = pr.transform.getMat();
		var t2 = pr.gameObject.transform.getMat();
		var t3 = worldToScreen;
		var t4 = t3.mul(t2).mul(t1);
		if (pr.getShape() instanceof core.math.geometry.Polygon) {
			var shape = (core.math.geometry.Polygon) pr.getShape();
			var points = shape.getPoints();
			int[] xs = new int[points.length];
			int[] ys = new int[points.length];

			for (int i = 0; i < points.length; i++) {
				var t = t4.mul(points[i]);
				xs[i] = (int) t.x;
				ys[i] = (int) t.y;
			}

			if (pr.mustFill()) {
				window.getGraphics().fillPolygon(xs, ys, points.length);
			} else {
				window.getGraphics().drawPolygon(xs, ys, points.length);
			}
		} else if (pr.getShape() instanceof Circle) {
			var shape = (Circle) pr.getShape();

			var t = t4.mul(shape.center());
			var r = (int) camera.scalarWorldToScreen(shape.getRadius());
			if (pr.mustFill()) {
				window.getGraphics().fillOval((int) t.x - r, (int) t.y - r, r * 2, r * 2);
			} else {
				window.getGraphics().drawOval((int) t.x - r, (int) t.y - r, r * 2, r * 2);
			}

		}

	}
}
