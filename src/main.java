import Geom.Point3D;
import Map.MapOptimizer;
import Pixel.PointPixel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.jmx.snmp.Timestamp;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
	public static void main (String [] args) throws ParseException {

		Point3D p = new Point3D(32.104262361370715,35.20617349546406);
		My_coords m = new My_coords();
		m.AddAzimuthAndVector(p, 180, 200);
		System.out.println(p.x() + " , " +p.y());
		  Date now = new Date(0);
	      System.out.println("toString(): " + now);  // dow mon dd hh:mm:ss zzz yyyy
	      
	      // SimpleDateFormat can be used to control the date/time display format:
	      //   E (day of week): 3E or fewer (in text xxx), >3E (in full text)
	      //   M (month): M (in number), MM (in number with leading zero)
	      //              3M: (in text xxx), >3M: (in full text full)
	      //   h (hour): h, hh (with leading zero)
	      //   m (minute)
	      //   s (second)
	      //   a (AM/PM)
	      //   H (hour in 0 to 23)
	      //   z (time zone)
	      SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z");
	      System.out.println("Format 1:   " + dateFormatter.format(now));

	      dateFormatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	      System.out.println("Format 2:   " + dateFormatter.format(now));
	      
	      dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
	      System.out.println("Format 3:   " + dateFormatter.format(now));
	}
}

