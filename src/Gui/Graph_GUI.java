package Gui;
import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
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
import java.util.Collections;
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

import org.omg.CORBA.INITIALIZE;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.NodeV;
import utils.Point3D;
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
	private int min_x = 500;
	private int min_y = 500;
	private static final int DEFAULT_SIZE = 500;
	private static double penRadius;
	private static Graphics2D offscreen;

	int action=0;

	//holds the vertex points.\\ 
	//ArrayList<NodeV> p_list = new ArrayList<NodeV>(); 
	public Collection<node_data> vertex;

	// contains all the edges by ID(src ver) and edge_data. 
	public Collection<edge_data> edges;
	private graph graph;
	Graph_Algo G = new Graph_Algo();


	/////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/////////////////////////   Constructor  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	//////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public Graph_GUI(graph dg)
	{
		initGUI();
		this.vertex	= dg.getV();
		this.graph = dg;
		G.init(this.graph);
	}


	/////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/////////////////////////   window settings  \\\\\\\\\\\\\\\\\\\\\\\\\\
	//////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	private void initGUI() {

		this.setSize(900,900);

		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//creating menu bar//
		MenuBar menuBar	 = new MenuBar();
		Menu menu_file1	 = new Menu("file");
		Menu menu_file2 = new Menu("Algorithems");
		Menu menu_file3 = new Menu("Set");


		//adding the file section to the menu bar//
		menuBar.add(menu_file1);
		menuBar.add(menu_file2);
		menuBar.add(menu_file3);

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

		MenuItem TSP = new MenuItem("TSP"); 
		TSP.addActionListener(this);

		MenuItem Set_Edge = new MenuItem("Set Edge"); 
		Set_Edge.addActionListener(this);

		MenuItem Delete_Edge = new MenuItem("Delete Edge"); 
		Delete_Edge.addActionListener(this);

		MenuItem Delete_Vertex = new MenuItem("Delete Vertex"); 
		Delete_Vertex.addActionListener(this);


		//adding to menues:\\
		//file
		menu_file1.add(Save);
		menu_file1.add(Load);

		//algorithems
		menu_file2.add(is_connected);
		menu_file2.add(Shortest_Path);
		menu_file2.add(TSP);

		//Set
		menu_file3.add(Set_Edge);
		menu_file3.add(Delete_Edge);
		menu_file3.add(Delete_Vertex);



		//listen to the mouse\\
		this.addMouseListener(this);
	}


	public void paint(Graphics g)
	{

		super.paintComponents(g);

		if(action == 4) {
			node_data counterV = new NodeV();
			int last_node_id = graph.getMC()+1;
			counterV.setInfo(""+last_node_id);

			action =0;
		}
		this.setBackground(Color.WHITE);

		g.setColor(Color.blue);
		g.fillRect(25, 25, 100, 30);

		//call paint func\\
		super.paint(g);


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


			if(xv > min_x) min_x =	xv;
			if(yv > min_y) min_y =	yv;


			g.drawString(""+v.getKey(),xv-2,yv+5);


			/////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			////////////////creating edges to the vertex \\\\\\\\\\\\\\\\\\\\\\\\\\
			//////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

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


		///////////////////////////////////////////////
		/////////////ShortPath Command\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////


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



		///////////////////////////////////////////////
		/////////////ConnectCheck Command\\\\\\\\\\\\\\\
		////////////////////////////////////////////////



		if(action==2) {
			if(isConnected==false) {g.setColor(Color.RED);}
			else g.setColor(Color.green.darker());

			g.drawString("isConeected: "+isConnected, min_x-50, min_y-50);
			action = 0;
		}






		///////////////////////////////////////////////
		//////////////////TSP Command\\\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////


		if(action==3) {
			if(path!=null) {
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
					g.setColor(Color.BLACK);	
					g.drawString("egde:"+i,(((s.getLocation().ix()*1)/5)+f.getLocation().ix()*4)/5 ,(s.getLocation().iy()*1)/5+(f.getLocation().iy()*4)/5);
				}
				g.setColor(Color.YELLOW);
				g.fillOval(path.get(path.size()-1).getLocation().ix(),path.get(path.size()-1).getLocation().iy(), 2,2);
				g.setColor(Color.BLACK);
				g.drawString("finish", path.get(path.size()-1).getLocation().ix(),path.get(path.size()-1).getLocation().iy());
			}
		}

		if(this.graph!=null) {
			this.setSize(min_x+100,min_y+100);
		}	
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
			action= 0;
			repaint();
			FileDialog chooser = new FileDialog(frame, "Use a .txt extension", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename =chooser.getDirectory()+chooser.getFile();
			G.save(filename);
			System.out.println(G);

		}


		///////////////////////////////////////////////
		/////////////Load Command\\\\\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////

		else if(e.getActionCommand() == "Load") {
			action= 0;
			repaint();
			FileDialog chooser = new FileDialog(frame, "Use a .txt extension", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename =chooser.getDirectory()+chooser.getFile();
			this.graph = init(filename);
			this.G.init(graph);
			this.vertex=graph.getV();
			action = 4;

			repaint();
		}



		///////////////////////////////////////////////
		/////////////ShortPath Command\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////


		else if(e.getActionCommand() == "Short Path") {

			action= 0;
			repaint();
			JFrame f = null; 
			try {


				int src=0, Dest=0;
				boolean flag = true; 	
				String temp1="",temp2=""; 

				while (flag==true) {

					temp1 	= JOptionPane.showInputDialog(f,"Enter src ID: ");
					if(temp1.matches("\\d+")) {

						src =Integer.parseInt(temp1);	
						if(graph.getNode(src)!=null) {flag=false; continue;}
					}
					System.out.println("src Not Valid");
				}flag =true;


				while (flag==true) {

					temp1 	= JOptionPane.showInputDialog(f,"Enter Dest ID: ");
					if(temp1.matches("\\d+")) {

						Dest =Integer.parseInt(temp1);	
						if(graph.getNode(Dest)!=null) {flag=false; continue;}
					}
					System.out.println("dest Not Valid");
				}flag =true;




				graph_algorithms a = new Graph_Algo();
				a.init(graph);
				path =a.shortestPath(src,Dest);
				if(path==null) {System.out.println("null");}
				else {
					action = 1;
					repaint();
				}
			}catch (Exception es) {}
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




		///////////////////////////////////////////////
		//////////////////TSP Command\\\\\\\\\\\\\\\\\\\
		////////////////////////////////////////////////



		else if(e.getActionCommand() == ("TSP")) {
			action= 0;
			repaint();
			JFrame f = null;  
			graph_algorithms a = new Graph_Algo();
			a.init(graph);
			boolean flag =true; String temp1="";
			int src=0,Dest=0;int size_of_tr=0;
			ArrayList<Integer> targets = new ArrayList<Integer>(); ;			

			try {
				//input targets\\
				while(flag==true) {

					if(targets.size()==vertex.size()) {flag=false; continue;}

					temp1 = JOptionPane.showInputDialog(f,"Enter targets src ID (enter: Done when finish)");
					System.out.println(temp1);
					if(temp1.equals("done") || temp1.equals("Done") ) {flag=false; continue;}

					else {
						if(temp1.matches("\\d+")) {
							int targ = Integer.parseInt(temp1);
							//containe allready
							if(targets.contains(targ)) {System.out.println("This vertex allready in the search path");}
							//isvalid < vertex.size
							else if(graph.getNode(targ)==null) {System.out.println("This vertex doesnt exists");}

							//adding the target
							else
								targets.add(targ);

						}
					}
				}

				//copy the path for paint\\
				path = a.TSP(targets);
				//set action;
				action=3;
				repaint();
			}
			catch (Exception es) {}
		}



		///////////////////////////////////////////////
		//////////////// set Edge Command \\\\\\\\\\\\\\
		////////////////////////////////////////////////


		else if(e.getActionCommand() == ("Set Edge")) {

			JFrame f= null ;


			int src=0;		int Dest=0;		double weight = 0;		boolean flag = true;
			String temp1=""; 	

			while (flag==true) {

				temp1 	= JOptionPane.showInputDialog(f,"Enter src ID: ");
				if(temp1.matches("\\d+")) {

					src =Integer.parseInt(temp1);	
					if(graph.getNode(src)!=null) {flag=false; continue;}
				}
				System.out.println("src Not Valid");
			}flag =true;


			while (flag==true) {

				temp1 	= JOptionPane.showInputDialog(f,"Enter Dest ID: ");
				if(temp1.matches("\\d+")) {

					Dest =Integer.parseInt(temp1);	
					if(graph.getNode(Dest)!=null) {flag=false; continue;}
				}
				System.out.println("dest Not Valid");
			}flag =true;



			while (flag==true) {

				temp1 	= JOptionPane.showInputDialog(f,"Enter weight: ");
				try {
					weight =Double.parseDouble(temp1);
					flag=false; 
					continue;		

				}catch (Exception ex) {System.out.println("weight Not Valid"); continue;}
			}


			this.graph.connect(src, Dest, weight);
			repaint();
		}



	///////////////////////////////////////////////
	//////////////// Delete Edge  \\\ \\\\\\\\\\\\\\
	////////////////////////////////////////////////

	else if(e.getActionCommand() == ("Delete Edge")) {

		JFrame f= null ;


		int src=0;		int Dest=0;		boolean flag = true;
		String temp1=""; 	



		while (flag==true) {

			temp1 	= JOptionPane.showInputDialog(f,"Enter src ID: ");
			if(temp1.matches("\\d+")) {

				src =Integer.parseInt(temp1);	
				if(graph.getNode(src)!=null) {flag=false; continue;}
			}
			System.out.println("src Not Valid");
		}flag =true;


		while (flag==true) {

			temp1 	= JOptionPane.showInputDialog(f,"Enter Dest ID: ");
			if(temp1.matches("\\d+")) {

				Dest =Integer.parseInt(temp1);	
				if(graph.getNode(Dest)!=null) {flag=false; continue;}
			}
			System.out.println("dest Not Valid");
		}flag =true;

		
		graph.removeEdge(src, Dest);

		repaint();

	}


	///////////////////////////////////////////////
	//////////////// Delete Vertex \\\\\\\\\\\\\\\\\
	////////////////////////////////////////////////
	else if(e.getActionCommand() == ("Delete Vertex")) {

		JFrame f= null ;


		int src=0;	boolean flag = true;
		String temp1=""; 	


		while (flag==true) {

			temp1 	= JOptionPane.showInputDialog(f,"Enter src ID: ");
			if(temp1.matches("\\d+")) {

				src =Integer.parseInt(temp1);	
				if(graph.getNode(src)!=null) {flag=false; continue;}
			}
			System.out.println("src Not Valid");
		}flag =true;
		


		flag=false;
		graph.removeNode(src);

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





















