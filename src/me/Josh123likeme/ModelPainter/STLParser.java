package me.Josh123likeme.ModelPainter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import me.Josh123likeme.Render3D4p0.Vector3D;
import me.Josh123likeme.Render3D4p0.World.Model;
import me.Josh123likeme.Render3D4p0.World.Triangle;

public class STLParser {
	
	public Model parseSTL(File file) {
		
		byte[] bytes = new byte[(int) file.length()];

		FileChannel channel = null;
		try {
			channel = new FileInputStream(file).getChannel();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			buffer.get(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Triangle> triangles = new ArrayList<Triangle>();
		
		//this starts at 84 to skip the metadata
		int i = 84;
		
		while (i < bytes.length) {
			
			float f1;
			float f2;
			float f3;
			
			//normal not needed
			i += 4*3;
			
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
			
			//spacer
			i += 2;
			
			triangles.add(new Triangle(a, b, c, 0xFFD0D0D0));
			
		}
		
		return new Model(triangles);
		
	}
	
}
