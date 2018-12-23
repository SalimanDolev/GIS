package Algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.jmx.snmp.Timestamp;

public class PathToKML {
	
	private final BufferedWriter writer;

	private final BufferedReader reader;





	 public PathToKML(String input, String output) throws IOException{
		reader = new BufferedReader(new FileReader(input));
		writer = new BufferedWriter(new FileWriter(output));
	}
	private void writeStart() throws IOException {
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">"
				+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon>"
				+ "</IconStyle></Style><Style id=\"yellow\"><IconStyle>"
				+ "<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon>"
				+ "</IconStyle></Style><Style id=\"green\"><IconStyle>"
				+ "<Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon>"
				+ "</IconStyle></Style>"
				+ "<Style id=\"blue\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/kml/pushpin/purple-pushpin.png</href></Icon></IconStyle></Style>\r\n" + 
				"<Style id=\"pink\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/kml/pushpin/pink-pushpin.png</href></Icon></IconStyle></Style><Folder>"
				+ "<name>Wifi Networks</name>\n");
	}


	public void run() throws IOException {
		try {
			writeStart();
			reader.readLine();
			String str = reader.readLine();
			String[] parsed = str.split(",");
			System.out.println(str);
			System.out.println(parsed[0]);
			int i = 0;
			Timestamp p = new Timestamp();
			while (str != null) {
				if (parsed[0].equals("P")) {
				writer.write("<Placemark>\n");
				writer.write(" <TimeSpan>\r\n" + 
			
						"<begin>"+  i +"</begin>\r\n" + 
						 "<end>" + (i+1) +"</end>"+
						"</TimeSpan>\r\n" + 
						"<styleUrl>#paddle-a</styleUrl>");
				writer.write("<Point>\r\n" + 
						"<coordinates>" + parsed[3]+","+parsed[2]+"</coordinates></Point>\n");
				writer.write("</Placemark>\r\n");
				System.out.println(str);
				i = i+1;
				str = reader.readLine();
				if (str != null)parsed = str.split(",");
				}
			}

			
			writer.write("</Folder>\n"
					+ "</Document></kml>");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			} else {
				if (writer != null) {
					writer.close();
				}
			}
		}
	}


}
