package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

import Coords.My_coords;
import Geom.Point3D;
import packmenAndFruits.fruits;
import packmenAndFruits.packmen;

public class ShortestPath {

	public ShortestPath() {

	}

	public void run(ArrayList<packmen> p , ArrayList<fruits> f) {
		// if there is only one packmen calculate his path
		ArrayList<packmen> temp_p = new ArrayList<packmen>();
		temp_p = p;
		ArrayList<fruits> temp_f = new ArrayList<fruits>();
		temp_f = f;
		if (p.size() == 1 ) {
			for (int i = 0; i < f.size(); i++) {
				temp_p.get(0).add3Dpath(temp_f.get(i).get3Dpoint());
				temp_p.get(0).addPixelPath(temp_f.get(i).getPixelPoint());
			}

		}
		else { // else if there is multiply packmens
			for (int i = 0; i < f.size(); i++) {
				int MinimalDistancePackmen = 0;
				for (int j = 0; j < p.size()-1; j++) {
					//calculate the time takes to every packmen to go to the next fruit, and takes the faster
					if ( (temp_p.get(j).get3Dpoint().distance3D(temp_f.get(i).get3Dpoint())) / (temp_p.get(j).getSpeed()) > 
						 (temp_p.get(j+1).get3Dpoint().distance3D(temp_f.get(i).get3Dpoint()) /(temp_p.get(j+1).getSpeed()) ) )  {
						MinimalDistancePackmen = j+1;
					}
				}
				//temp_p.get(MinimalDistancePackmen).setPackmenPosition(temp_f.get(i).get3Dpoint());// move the packmen to the fruit position.
				//temp_p.get(MinimalDistancePackmen).setPackmenPosition(temp_f.get(i).getPixelPoint());
				temp_p.get(MinimalDistancePackmen).add3Dpath(temp_f.get(i).get3Dpoint());// add the fruit position to the packmen data.
				temp_p.get(MinimalDistancePackmen).addPixelPath(temp_f.get(i).getPixelPoint());
			}

		}
	}
}

