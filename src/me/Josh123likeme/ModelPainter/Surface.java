package me.Josh123likeme.ModelPainter;

import me.Josh123likeme.Render3D4p0.Vector3D;

public abstract class Surface {

	protected int colour;
	
	public Surface(int colour) {
		
		this.colour = colour;
		
	}
	
	//returns -1 if non-hit
	public abstract double getHitLambda(Vector3D dir, Vector3D offset);
	
	public int getColour() {
		
		return colour;
		
	}
	
}
