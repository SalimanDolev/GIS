 package Algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import GUI.GuiEX3;
import packmenAndFruits.packmen;

public class CsvToGame {
	
	private BufferedReader reader;
	
	public CsvToGame() {
		try {
			reader = new BufferedReader(new FileReader("C:\\\\Users\\\\salim\\\\Desktop\\\\לימודים\\\\מונחה עצמים\\\\מטלה 3\\\\Ex3 (2)\\\\Ex3\\\\data\\\\game_1543693911932_a.csv"));
			
			GuiEX3 screen = new GuiEX3();
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run() {
		try {
			String str = this.reader.readLine();
			System.out.println(this.reader.readLine());
			String parsed[] = str.split(",");
			while (str != null) {
				if(parsed[0] == "P") {
					packmen p = new packmen();
					p.setPackmenPosition(Double.parseDouble(parsed[2]) ,Double.parseDouble(parsed[3]));
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
