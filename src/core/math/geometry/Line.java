package core.math.geometry;

import core.math.vector.Vector2f;

public class Line {
	public Vector2f p1, p2;

	public Line(Vector2f p1, Vector2f p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Vector2f getNormal(Vector2f point){
		var a = point.sub(p1);
		var b = p2.sub(p1).normalise();
		var n = p1.add(b.mul(a.dot(b)));
		return n;
	}

	public boolean containsPoint(Vector2f point){
		var AB = p2.sub(p1);
		var AC = point.sub(p1);
		var aligned = Math.abs(AB.cross(AC)) < 0.001f;
		if(aligned){
			var Kac  = AB.dot(AC);
			var Kab =  AB.dot(AB);
			var r = 0 < Kac && Kac < Kab;
			return r;
		}else
			return false;
	}

	@Override
	public String toString() {
		return p1+" -> "+p2;
	}
}
