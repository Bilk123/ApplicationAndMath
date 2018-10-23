package core.math.geometry;

import core.math.vector.Vector2f;

import java.io.Serializable;

public abstract class Shape implements Serializable {
	private static final long serialVersionUID = -7378492523313836526L;

	protected Vector2f cent;

	public Shape(Vector2f cent){
		this.cent = cent;
	}

	public Shape(){
		this(new Vector2f());
	}

	public Vector2f center(){
		return cent;
	}
}
