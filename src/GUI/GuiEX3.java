package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithm.GameToCsv;
import Algorithm.Game_CSVToKML;
import Algorithm.PathToKML;
import Algorithm.ShortestPath;
import Coords.My_coords;
import Coords.coordsToPixel;
import Geom.Point3D;
import packmenAndFruits.fruits;
import packmenAndFruits.packmen;

@SuppressWarnings("serial")
public class GuiEX3 extends JFrame implements MouseListener,ActionListener,ItemListener {


	public BufferedImage myImage;
	public ArrayList<packmen> listPackmen = new ArrayList<>();
	public ArrayList<fruits> listFruits = new ArrayList<>();
	MenuItem item1;
	MenuItem item2;
	Menu menu;
	MenuBar menuBar;
	Menu game;
	MenuItem path;
	MenuItem UPload;
	MenuItem Convert_toCSV;
	MenuItem Play;

	BufferedReader reader;

	private boolean addFruit = false;
	private boolean addPackmen = false;
	private boolean Start_Draw_Path = false;
	private boolean upload = false;
	private boolean play = false;
	Image fruitIcon;
	Image packmenIcon;

	public GuiEX3(){
		initGUI();
		this.addMouseListener(this);
	}
	private void initGUI() 
	{	
		fruitIcon = Toolkit.getDefaultToolkit().getImage("C:\\Pictures_For_Ex3\\FRUIT.png");
		packmenIcon = Toolkit.getDefaultToolkit().getImage("C:\\Pictures_For_Ex3\\packman-png-8.png");
		this.menuBar = new MenuBar();
		//add packemns and fruit to the menu
		this.menu = new Menu("Add"); 
		this.item1 = new MenuItem("Pakcmen");
		this.item1.addActionListener(this);
		this.item2 = new MenuItem("Fruit");
		this.item2.addActionListener(this);

		// add functuallity to the game such as calculate path and saving to csv file
		this.game = new Menu("Game");
		this.path = new MenuItem("Calculte path");
		this.path.addActionListener(this);

		// Upload a game
		this.UPload = new MenuItem("Upload");
		this.UPload.addActionListener(this);

		//convert to Csv file from the screen
		this.Convert_toCSV = new MenuItem("Convert to KML file");
		this.Convert_toCSV.addActionListener(this);

		// add play button to the menu
		this.Play = new MenuItem("Play game");
		this.Play.addActionListener(this);

		menuBar.add(game);
		this.game.add(path);
		menuBar.add(menu);
		this.menu.add(item1);
		this.menu.add(item2);
		this.setMenuBar(menuBar);
		this.game.add(UPload);
		this.game.add(Convert_toCSV);
		this.game.add(Play);

		try {
			this.myImage = ImageIO.read(new File("C:\\Users\\salim\\Desktop\\μιξεγιν\\Projects\\GIS_Ex02\\Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	int x = 2;
	int y = 2;

	public void paint(Graphics g)
	{

		g.drawImage(myImage, 0, 0, this);
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
		if(!listFruits.isEmpty())
		{
			for(int i=0 ; i<listFruits.size() ; i++)
			{
				int x_fruit =(int) listFruits.get(i).GetX();
				int y_fruit = (int)listFruits.get(i).GetY();
				g.drawImage(fruitIcon,x_fruit, y_fruit, 25, 25,this);
			}

		}

		if(!listPackmen.isEmpty())
		{
			for(int i=0 ; i<listPackmen.size() ;i++)
			{
				int x_pacman =(int) listPackmen.get(i).getPixelPoint().GetX();
				System.out.println("1");
				int y_pacman = (int)listPackmen.get(i).getPixelPoint().GetY();
				System.out.println("2");
				g.drawImage(packmenIcon,x_pacman, y_pacman, 25, 25,this);
			}
		}	
		if(play) {
			try {
				for (int j = 0; j < this.listPackmen.size(); j++) {
					for (int j2 = 0; j2 < this.listPackmen.get(j).getfullPathSize(); j2++) {
						this.listPackmen.get(j).setPackmenPosition(this.listPackmen.get(j).getfullStep(j2));
						int x_pacman =(int) listPackmen.get(j).getPixelPoint().GetX();
						System.out.println("1");
						int y_pacman = (int)listPackmen.get(j).getPixelPoint().GetY();
						System.out.println("2");
						g.drawImage(packmenIcon,x_pacman, y_pacman, 25, 25,this);
						Thread.sleep(500);
						System.out.println("onr second");
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Start_Draw_Path) {
			for (int j = 0; j < listPackmen.size(); j++) { 
				int x_path = (int) listPackmen.get(j).getPixelPoint().GetX();
				int y_path = (int) listPackmen.get(j).getPixelPoint().GetY();

				// need to add when the path is emoty dont do nothing
				if(listPackmen.get(j).getPixelPathSize() > 0) {
					int x_next = (int) listPackmen.get(j).getPixelStep(0).GetX();
					int y_next = (int) listPackmen.get(j).getPixelStep(0).GetY();
					g.drawLine(x_path,y_path,x_next,y_next);
					for (int i = 0 ; i < listPackmen.get(j).getPathSize() - 1 ;i++) {
						x_path = (int) listPackmen.get(j).getPixelStep(i).GetX();
						y_path = (int) listPackmen.get(j).getPixelStep(i).GetY();


						x_next = (int) listPackmen.get(j).getPixelStep(i+1).GetX();
						y_next = (int) listPackmen.get(j).getPixelStep(i+1).GetY();
						System.out.println(x_path +", "+y_path);
						System.out.println(x_next +", "+y_next);
						g.drawLine(x_path,y_path,x_next,y_next);
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		coordsToPixel m = new coordsToPixel();
		double x = e.getX();
		double y = e.getY();
		System.out.println("the X pixel is : "+e.getX()+", The Y pixel is:" +  e.getY());
		System.out.println("the GPS point: ("+m.convertFromPixelToGPS(e.getX(),e.getY())+")");
		repaint();
		if(addFruit){
			fruits fruit1 = new fruits();
			fruit1.setFruitsPosition(x,y);
			listFruits.add(fruit1);
			System.out.println("location = "+listFruits.get(listFruits.size()-1).getPixelPoint().GetX() +","+
					listFruits.get(listFruits.size()-1).getPixelPoint().GetY());
		}
		if(addPackmen){ 
			packmen p = new packmen();
			p.setPackmenPosition(x,y);
			listPackmen.add(p);
			System.out.println("location = "+listPackmen.get(listPackmen.size()-1).getPixelPoint().GetX()+","+
					listPackmen.get(listPackmen.size()-1).getPixelPoint().GetY());
			System.out.println("location = "+listPackmen.get(listPackmen.size()-1).get3Dpoint().x() +","+
					listPackmen.get(listPackmen.size()-1).get3Dpoint().y());
		}

		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		/////////////////////////////////////////////////////////////////////////////////////////////////
		//Drawing packmen
		if(event.getSource().equals(item1))
		{
			addPackmen = true;
			addFruit = false;
			System.out.println("Press packmen");
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////
		// Drawing fruit
		if(event.getSource().equals(item2))
		{
			addPackmen = false;
			addFruit = true;
			System.out.println("Press fruit");
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// Adding path way to every packmen
		if(event.getSource().equals(path)) {
			System.out.println("Adding path to the screnn");
			Start_Draw_Path = true;
			if(Start_Draw_Path) {
				ShortestPath p = new ShortestPath();
				p.run(listPackmen,listFruits );
			}
			repaint();
			//////////////////////////////////////////////////////////////
			//calculate  the distance from packmen to a fruit and make it less long, to show it after all in google maps 
			My_coords m = new My_coords();
			for (int i = 0; i < listPackmen.size(); i++) {
				if(this.listPackmen.get(i).getPathSize() > 0) {
					Point3D startpoint = this.listPackmen.get(i).get3Dpoint();
					Point3D endpoint = this.listPackmen.get(i).getStep(0);
					this.listPackmen.get(i).add3Dfullpath(startpoint);
					double[] a =  m.azimuth_elevation_dist(startpoint, endpoint);
					System.out.println(a[0] + "," + a[2]);
					double distance = a[2]/10;
					//cuts the distance beetwen packman and fruit to 10 Gps points
					for (int j = 0; j < 10; j++) {
						System.out.println("yes");
						this.listPackmen.get(i).add3Dfullpath(m.AddAzimuthAndVector(startpoint, a[0], distance));
					}

					for (int j = 0; j < listPackmen.get(i).getPathSize()-1; j++) {
						System.out.println("no");
						startpoint = this.listPackmen.get(i).getStep(j);
						endpoint = this.listPackmen.get(i).getStep(j+1);
						a =  m.azimuth_elevation_dist(startpoint, endpoint);
						distance = a[2]/10;
						//cuts the distance beetwen packman and fruit to 20 Gps points
						for (int j2 = 0; j2 < 10; j2++) {
							System.out.println("no");
							this.listPackmen.get(i).add3Dfullpath(m.AddAzimuthAndVector(startpoint, a[0], distance));
						}
					}
					for (int j = 0; j < this.listPackmen.get(0).getfullPathSize(); j++) {
						System.out.println("step " + j + ":" +this.listPackmen.get(0).getfullStep(j).x() +"," +this.listPackmen.get(0).getfullStep(j).y()) ;
					}
				}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Uploading a game from CSV
		if (event.getSource().equals(UPload)) {
			this.upload = true;
			try {
				//chhose a file to upload from the comuter
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " +
							chooser.getSelectedFile().getName());
				}
				reader = new BufferedReader(new FileReader(chooser.getCurrentDirectory().getPath()+ "\\" + chooser.getSelectedFile().getName()));
				String str = reader.readLine();
				str = reader.readLine();
				String parsed[] = str.split(",");
				while (str != null) {
					System.out.println(str);
					if(parsed[0].equals("P")) {
						packmen p = new packmen();
						Point3D pointGps = new Point3D(Double.parseDouble(parsed[2]) ,Double.parseDouble(parsed[3]));
						p.setPackmenPosition(pointGps);
						p.setSpeed(Double.parseDouble(parsed[5]));
						this.listPackmen.add(p);
					}
					if(parsed[0].equals("F")) {
						fruits f = new fruits();
						Point3D p2 = new Point3D(Double.parseDouble(parsed[2]) ,Double.parseDouble(parsed[3]));
						f.setFruitPositionFromGps(p2);
						f.setWeight(Integer.parseInt(parsed[5]));
						this.listFruits.add(f);
					}
					str = reader.readLine();
					if(str!= null ) parsed = str.split(",");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
		///////////////////////////////////////////////////////////////////////////////////////
		//convert from game to csv
		if(event.getSource().equals(Convert_toCSV)) {
			GameToCsv m = new GameToCsv(this.listPackmen);
			try {
				PathToKML k  = new PathToKML("test.csv", "test.kml");
				k.run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(event.getSource().equals(Play)) {
			play = true;
			repaint();
		}
			//try {
			//	for (int j = 0; j < this.listPackmen.size(); j++) {
			//		for (int j2 = 0; j2 < this.listPackmen.get(j).getfullPathSize(); j2++) {
			//			this.listPackmen.get(j).setPackmenPosition(this.listPackmen.get(j).getfullStep(j2));

			//			System.out.println(this.listPackmen.get(j).getPixelPoint().GetX() +","+ this.listPackmen.get(j).getPixelPoint().GetY());
			//			repaint();
			//			Thread.sleep(500);
			//			System.out.println("onr second");
			//		}
			//	}
			//} catch (InterruptedException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			//}

		}





		public static void main(String[]args) {

			GuiEX3 frame = new GuiEX3();
			frame.setVisible(true);
			frame.setSize(frame.myImage.getWidth(), frame.myImage.getHeight());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			frame.setResizable(false);

		}
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
