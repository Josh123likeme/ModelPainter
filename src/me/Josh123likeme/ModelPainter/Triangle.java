package me.Josh123likeme.ModelPainter;

import me.Josh123likeme.Render3D4p0.Vector3D;

public class Triangle extends Surface {

	private Vector3D normal;
	private double offset;
	
	private Vector3D a;
	private Vector3D b;
	private Vector3D c;
	
	public Triangle(Vector3D p1, Vector3D p2, Vector3D p3) {
		
		super(0xFF00FF00);
		
		a = p1.clone();
		b = p2.clone();
		c = p3.clone();
		
		Vector3D p1c = p1.clone();
		Vector3D p2c = p2.clone();
		
		p1c.subtract(p3);
		p2c.subtract(p3);
		
		normal = p1c.cross(p2c);
		
		offset = normal.X*p3.X + normal.Y*p3.Y + normal.Z*p3.Z;
		
	}
	
	@Override
	public double getHitLambda(Vector3D dir, Vector3D offset) {
		
		double numerator = this.offset - normal.X*offset.X - normal.Y*offset.Y - normal.Z*offset.Z;

		double denominator = normal.X*dir.X + normal.Y*dir.Y + normal.Z*dir.Z;
		
		double lambda = numerator / denominator;
		
		if (lambda < 0) return -1;
		
		Vector3D p = dir.clone().scale(lambda).add(offset);

		Vector3D x1 = b.clone().subtract(a).cross(p.clone().subtract(a));
		Vector3D x2 = c.clone().subtract(b).cross(p.clone().subtract(b));
		Vector3D x3 = a.clone().subtract(c).cross(p.clone().subtract(c));

		if (!x1.normalise().sameAs(x2.normalise()) || !x1.normalise().sameAs(x3.normalise())) return -1;
		
		return lambda;
		
	}

}
