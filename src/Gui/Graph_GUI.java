package Gui;
import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicTreeUI.MouseHandler;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.NodeV;
import utils.Point3D;
import utils.StdDraw;
import java.lang.*;
import java.text.DecimalFormat;


public class Graph_GUI extends JFrame implements ActionListener, MouseListener {

	private static final Graphics Graphics = null;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	static JTextField textfield1, textfield2, textfield3;
	static JButton submit1,submit2;
	private static JFrame frame;
	boolean isConnected=false;
	List<node_data> path;
	
	int action=0;

	//holds the vertex points.\\ 
	//ArrayList<NodeV> p_list = new ArrayList<NodeV>(); 
	public Collection<node_data> vertex;

	// contains all the edges by ID(src ver) and edge_data. 
	public Collection<edge_data> edges;
	private graph graph;
	Graph_Algo G = new Graph_Algo();


	public Graph_GUI(graph dg)
	{
		initGUI();
		this.vertex	= dg.getV();
		this.graph = dg;
		G.init(this.graph);
	}

	private void initGUI() {

		this.setSize(900,900);
		
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//creating menu bar//
		MenuBar menuBar	 = new MenuBar();
		Menu menu_file1	 = new Menu("file");
		Menu menu_file2 = new Menu("Algorithems");


		//adding the file section to the menu bar//
		menuBar.add(menu_file1);
		menuBar.add(menu_file2);
		this.setMenuBar(menuBar);

		//creating a item in bar for short path
		MenuItem Save = new MenuItem("Save"); 
		Save.addActionListener(this);

		//creating a item in bar for short path
		MenuItem Load = new MenuItem("Load"); 
		Load.addActionListener(this);

		//creating a item in bar for short path
		MenuItem Shortest_Path = new MenuItem("Short Path"); 
		Shortest_Path.addActionListener(this);

		//creating a item in bar for is connected
		MenuItem is_connected = new MenuItem("connect check"); 
		is_connected.addActionListener(this);

		menu_file1.add(Save);
		menu_file1.add(Load);


		menu_file2.add(is_connected);
		menu_file2.add(Shortest_Path);

		//listen to the mouse\\
		this.addMouseListener(this);
	}
	
//	private void ShortPathPainter(java.util.List<node_data> path, Iterator it) {
//		
//		
//		for (int i = 0; i < path.size()-1; i++) {
//			int j = i+1;
//
//			node_data s =path.get(i);
//			node_data f =path.get(j);
//			g.setColor(Color.YELLOW);
//			g.drawOval(s.getLocation().ix(), s.getLocation().iy(), 2,2);
//			g.setColor(Color.BLACK);
//			if(i==0) {g.drawString("Start",s.getLocation().ix(), s.getLocation().iy());}
//			g.setColor(Color.GREEN);
//
//			g.drawLine(s.getLocation().ix(), s.getLocation().iy(),f.getLocation().ix(),f.getLocation().iy());
//		}
//		g.setColor(Color.YELLOW);
//		g.fillOval(path.get(path.size()-1).getLocation().ix(),path.get(path.size()-1).getLocation().iy(), 2,2);
//		g.setColor(Color.BLACK);
//		g.drawString("finish", path.get(path.size()-1).getLocation().ix(),path.get(path.size()-1).getLocation().iy());	
//	}
//	
	
	
	public void paint(Graphics g)
	{
		
		
		super.paintComponents(g);
		this.setBackground(Color.WHITE);

		g.setColor(Color.blue);
		g.fillRect(25, 25, 100, 30);

		//call paint func\\
		super.paint(g);


		g.fillOval(100, 100, 10, 10);
		Iterator hit = vertex.iterator();
		int [][] points_save = new int [vertex.size()][vertex.size()]; 


		while(hit.hasNext()) {

			/////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			////////////////creating the vertex on Screen \\\\\\\\\\\\\\\\\\\\\\\\\
			//////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


			node_data v = (node_data) hit.next(); 
			g.setColor(Color.BLUE);

			int i=0;
			int xv=graph.getNode(v.getKey()).getLocation().ix();
			int yv=graph.getNode(v.getKey()).getLocation().iy();
			g.drawRect(xv-10,yv-10,20,20);

			g.drawString(""+v.getKey(),xv-2,yv+5);


			//points_save[0][i]=xv;
			//points_save[1][i]=yv;

			//creating edges to the vertex\\ 
			edges = graph.getE(v.getKey());
			if(edges == null) {continue;}

			//go over the edges that come out of the specific vertex\\ 
			Iterator hit2 = edges.iterator();

			while(hit2.hasNext()) {


				g.setColor(Color.red);

				edge_data dest = (edge_data) hit2.next();
				//From\\
				int x1 = graph.getNode(v.getKey()).getLocation().ix();
				int y1 = graph.getNode(v.getKey()).getLocation().iy();
				//To\\
				int x2 = graph.getNode(dest.getDest()).getLocation().ix();
				int y2 = graph.getNode(dest.getDest()).getLocation().iy();

				//draw the line between the vertexes\\ 
				g.drawLine(x1, y1,x2,y2);
				g.setColor(Color.YELLOW);
				//Draw the circle indicates the direction of the edge,
				//by mark a oval in the 3/4 the line next to the dest vertex. 
				g.fillOval(((x1*1)/5)+((x2*4)/5),((y1*1)/5)+((y2*4)/5) , 15, 15);

				double w = dest.getWeight();

				g.setColor(Color.MAGENTA);
				g.drawString(df2.format(w),(x1+x2)/2,(y1+y2)/2);

			}
		}
		
		if(action == 1) {
			for (int i = 0; i < path.size()-1; i++) {
				int j = i+1;

				node_data s =path.get(i);
				node_data f =path.get(j);
				g.setColor(Color.YELLOW);
				g.drawOval(s.getLocation().ix(), s.getLocation().iy(), 2,2);
				g.setColor(Color.BLACK);
				if(i==0) {g.drawString("Start",s.getLocation().ix(), s.getLocation().iy());}
				g.setColor(Color.GREEN);

				g.drawLine(s.getLocation().ix(), s.getLocation().iy(),f.getLocation().ix(),f.getLocation().iy());
			}
			g.setColor(Color.YELLOW);
			g.fillOval(path.get(path.size()-1).getLocation().ix(),path.get(path.size()-1).getLocation().iy(), 2,2);
			g.setColor(Color.BLACK);
			g.drawString("finish", path.get(path.size()-1).getLocation().ix(),path.get(path.size()-1).getLocation().iy());	
		}
		
		if(action==2) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			g.getFont().getSize();
			g.drawString("isConeected: "+isConnected, 100, 1000);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered Screen");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//	System.out.println("mouse exited");
	}

	@Override
	public void mousePressed(MouseEvent e) {


		int x = e.getX();
		int y = e.getY();
		System.out.println(x+"    "+y);
		NodeV user = new NodeV(x,y);
		System.out.println(user.getLocation());

		this.paint(this.getGraphics());

		this.graph.addNode(user);

		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		///////////////////////////////////////////////
		/////////////Saving Command\\\\\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////
		if(e.getActionCommand() == "Save") {
			//FileDialog chooser = new FileDialog(StdDraw.frame, "Use a .png or .jpg extension", FileDialog.SAVE);
			JFrame f = null; 
			String Filename = JOptionPane.showInputDialog(f,"Enter File name");
			//	chooser.setVisible(true);
			String filename = Filename+".txt"; //chooser.getFile();
			G.save(filename);
			//if (filename != null) {
			//		StdDraw.save(chooser.getDirectory() + File.separator + chooser.getFile());
			//	}
		}
		///////////////////////////////////////////////
		/////////////Load Command\\\\\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////

		else if(e.getActionCommand() == "Load") {
			FileDialog chooser = new FileDialog(frame, "Use a .txt extension", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			graph = init(filename);
			G.init(graph);
			repaint();
		}

		///////////////////////////////////////////////
		/////////////ShortPath Command\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////
		else if(e.getActionCommand() == "Short Path") {
			JFrame f = null; 
			String src = JOptionPane.showInputDialog(f,"Enter Src");
			String dest = JOptionPane.showInputDialog(f,"Enter Dest");

			graph_algorithms a = new Graph_Algo();
			a.init(graph);
			path =a.shortestPath(Integer.parseInt(src), Integer.parseInt(dest));
			if(path==null) {System.out.println("its nulllllllll");}
			else {
				action = 1;
				repaint();
//				Iterator it = path.iterator();
//				ShortPathPainter(path,it);
			}
		}

		///////////////////////////////////////////////
		/////////////ConnectCheck Command\\\\\\\\\\\\\\\
		////////////////////////////////////////////////
		else if(e.getActionCommand() == ("connect check")) {
			graph_algorithms a = new Graph_Algo();
			a.init(graph);
			isConnected = a.isConnected();
			action=2;
			repaint();
		}

	}
	
	private graph init(String file_name) {
		
		graph temp = null;
        try
        {    
            FileInputStream file = new FileInputStream(file_name); 
            ObjectInputStream in = new ObjectInputStream(file);
            temp = (graph)in.readObject(); 
            in.close(); 
            file.close();
            System.out.println("Object has been deserialized");
            return temp;
        }    
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        return null;
	}
	


}





















