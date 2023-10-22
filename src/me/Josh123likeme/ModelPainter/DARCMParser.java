package me.Josh123likeme.ModelPainter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import me.Josh123likeme.Render3D4p0.Vector3D;
import me.Josh123likeme.Render3D4p0.World.Model;
import me.Josh123likeme.Render3D4p0.World.Triangle;

public class DARCMParser {
	
	public Model parseDARCM(byte[] bytes) {
		
		List<Triangle> triangles = new ArrayList<Triangle>();
		
		int i = 0;
		
		while (i < bytes.length) {
			
			float f1;
			float f2;
			float f3;
			int colour;
			
			//vertex A
			f1 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			f2 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			f3 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			
			Vector3D a = new Vector3D(f1, f2, f3);
			
			//vertex B
			f1 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			f2 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			f3 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			
			Vector3D b = new Vector3D(f1, f2, f3);
			
			//vertex C
			f1 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			f2 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			f3 = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			i += 4;
			
			Vector3D c = new Vector3D(f1, f2, f3);
			
			//colour
			colour = ByteBuffer.wrap(new byte[] {bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]}).order(ByteOrder.BIG_ENDIAN).getInt();
			i += 4;
			
			triangles.add(new Triangle(a, b, c, colour));
			
		}
		
		return new Model(triangles);
		
	}
	
}
