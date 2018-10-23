package core.engine.components.rendeables;

import core.application.Colour;
import core.math.geometry.Polygon;
import core.math.geometry.Shape;

public class RenderableShape extends Renderable {
	private static final long serialVersionUID = -5201142540939595340L;

	private Shape shape;
	private Colour colour;
	private boolean fill;

	public RenderableShape(Shape shape) {
		super();
		this.shape = shape;
		colour = new Colour();
		fill = true;
		transform.setTranslation(shape.center());
		if(shape instanceof Polygon){
			transform.setRotation(((Polygon) shape).getRotation());
		}
	}

	public Shape getShape() {
		return shape;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean mustFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}
}
