package me.Josh123likeme.ModelPainter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.Josh123likeme.Render3D4p0.World.Model;
import me.Josh123likeme.Render3D4p0.World.Triangle;

public class DARCMWriter {
	
	public void writeDARCM(File file, Model model) {
		
		List<Byte> bytes = new ArrayList<Byte>();
		
		for (Triangle t : model.getTriangles()) {
			
			byte[] fourBytes;
			
			fourBytes = floatToByteArray((float) t.A.X);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			fourBytes = floatToByteArray((float) t.A.Y);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			fourBytes = floatToByteArray((float) t.A.Z);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			
			fourBytes = floatToByteArray((float) t.B.X);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			fourBytes = floatToByteArray((float) t.B.Y);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			fourBytes = floatToByteArray((float) t.B.Z);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			
			fourBytes = floatToByteArray((float) t.C.X);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			fourBytes = floatToByteArray((float) t.C.Y);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			fourBytes = floatToByteArray((float) t.C.Z);
			bytes.add(fourBytes[0]); bytes.add(fourBytes[1]); bytes.add(fourBytes[2]); bytes.add(fourBytes[3]);
			
			bytes.add((byte)((t.c >> 24) & 0xff)); bytes.add((byte)((t.c >> 16) & 0xff)); bytes.add((byte)((t.c >> 8) & 0xff)); bytes.add((byte)((t.c >> 0) & 0xff));
			
		}
		
		byte[] data = new byte[bytes.size()];
		
		for (int i = 0; i < bytes.size(); i++) {
			
			data[i] = bytes.get(i);
			
		}
		
		try 
		{
            FileOutputStream outputStream = new FileOutputStream(file);
 
            outputStream.write(data, 0, data.length);
	 
            outputStream.close();
            
	    } catch (IOException ex) {
            ex.printStackTrace();
	    }
		
	}
	
	private byte[] floatToByteArray(float value) {
		
		int bits = Float.floatToIntBits(value);
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(bits & 0xff);
		bytes[1] = (byte)((bits >> 8) & 0xff);
		bytes[2] = (byte)((bits >> 16) & 0xff);
		bytes[3] = (byte)((bits >> 24) & 0xff);
		
		return bytes;
		
	}
	
}
