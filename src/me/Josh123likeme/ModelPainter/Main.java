package me.Josh123likeme.ModelPainter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import me.Josh123likeme.Render3D4p0.World.Model;

public class Main {

	public static Model model;
	
	public static void main(String[] args) throws IOException {
		
		if (args.length == 0) throw new IllegalArgumentException("You need to give a file path");
		
		if (args[0].endsWith(".stl")) {
			
			STLParser stlp = new STLParser();
			model = stlp.parseSTL(new File(args[0]));
		}
		else if (args[0].endsWith(".darcm")) {
			
			File file = new File(args[0]);
			
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
			
			DARCMParser darcmp = new DARCMParser();
			model = darcmp.parseDARCM(bytes);
		}

		Game game = new Game();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("enter new hex colours here");
		
		DARCMWriter darcmw = new DARCMWriter();
		
		while (true) {
			
			String colour = reader.readLine();
			
			if (colour.equals("")) {
				
				darcmw.writeDARCM(new File("output.darcm"), model);
				
			}
			else {
				
				game.r = Integer.parseInt(colour.substring(0, 2), 16);
				game.g = Integer.parseInt(colour.substring(2, 4), 16);
				game.b = Integer.parseInt(colour.substring(4, 6), 16);
				
			}
			
		}
		
	}
	
}
