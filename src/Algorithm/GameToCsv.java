package Algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import packmenAndFruits.packmen;

public class GameToCsv {
	private BufferedWriter writer;

	public GameToCsv() throws IOException {
		writer = new BufferedWriter(new FileWriter("test.csv"));
	}


	public GameToCsv(ArrayList<packmen> p ) {
		try {
			writer = new BufferedWriter(new FileWriter("test.csv"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(',');
		sb.append("Lat");
		sb.append(',');
		sb.append("Lon");
		sb.append(',');
		sb.append("Alt");
		sb.append(',');
		sb.append("Speed/Weight");
		sb.append(',');
		sb.append("Radius");
		sb.append(',');
		sb.append('\n');
		for (int i = 0; i < p.size(); i++) {
			if(p.get(i).getfullPathSize() > 0) {
				System.out.println(p.size());
				sb.append("P");
				sb.append(',');
				sb.append(i);
				sb.append(',');
				sb.append(p.get(i).getfullStep(0).x());
				sb.append(',');
				sb.append(p.get(i).getfullStep(0).y());
				sb.append(',');
				sb.append(p.get(i).getfullStep(0).z());
				sb.append(',');
				sb.append(p.get(i).getSpeed());
				sb.append(',');
				sb.append(p.get(i).getRadius());
				sb.append(',');
				sb.append('\n');
				for (int j = 1; j < p.get(i).getfullPathSize(); j++) {
					sb.append("P");
					sb.append(',');
					sb.append(i);
					sb.append(',');
					sb.append(p.get(i).getfullStep(j).x());
					sb.append(',');
					sb.append(p.get(i).getfullStep(j).y());
					sb.append(',');
					sb.append(p.get(i).getfullStep(j).z());
					sb.append(',');
					sb.append(p.get(i).getSpeed());
					sb.append(',');
					sb.append(p.get(i).getRadius());
					sb.append(',');
					sb.append('\n');
					System.out.println(j);
					System.out.println(sb.toString());
				} 	
			}
		}
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done!");

	}
}
