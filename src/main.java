import Geom.Point3D;
import Map.MapOptimizer;
import Pixel.PointPixel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.jmx.snmp.Timestamp;

import Algorithm.CsvToGame;
import Algorithm.Game_CSVToKML;
import Algorithm.Multy_CSV;
import Coords.*;
import GIS.My_GIS_Project;

public class main {
	
	private double earthrhRadius = 6371000;
	
	public void AddAzimuthAndVector(Point3D p,double azimuth,double distance) {
		double azimuth2 = Math.toRadians(azimuth);
		double lat1 = Math.toRadians(p.x());
		double lon1 = Math.toRadians(p.y());
		
		double new_X = Math.asin((Math.sin(lat1) * Math.cos(distance/this.earthrhRadius))  +  
					             (Math.cos(lat1) * Math.sin(distance/this.earthrhRadius)*Math.cos(azimuth2)));
		double new_Y = lon1 + Math.atan2(Math.sin(azimuth2)*Math.sin(distance/this.earthrhRadius)*Math.cos(lat1)
				,Math.cos(distance/this.earthrhRadius)-Math.sin(p.y())*Math.sin(new_X) );
		
		new_X = Math.toDegrees(new_X);
		new_Y = Math.toDegrees(new_Y);

		p.setPoint(new_X, new_Y);
	}
	public static void main (String [] args) {

		Point3D p = new Point3D(32.104262361370715,35.20617349546406);
		My_coords m = new My_coords();
		m.AddAzimuthAndVector(p, 180, 200);
		System.out.println(p.x() + " , " +p.y());
		
	}
}

