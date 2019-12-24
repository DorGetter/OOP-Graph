package Gui;
import algorithms.Graph_Algo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	static JTextField textfield1, textfield2, textfield3;

	static JButton submit1,submit2;

	Graphics g;


	//holds the vertex points.\\ 
	//ArrayList<NodeV> p_list = new ArrayList<NodeV>(); 
	public Collection<node_data> vertex;

	// contains all the edges by ID(src ver) and edge_data. 
	public Collection<edge_data> edges;
	private graph Graph;
	Graph_Algo G = new Graph_Algo();


	public Graph_GUI(DGraph dg)
	{
		initGUI();
		this.vertex	= dg.getV();
		this.Graph = dg;
		G.init(this.Graph);

	}

	private void initGUI() {

		
		this.setSize(900,900);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//creating menu bar//
		MenuBar menuBar	 = new MenuBar();
		Menu menu_file	 = new Menu("Algorithems"); 
		//adding the file section to the menu bar//
		menuBar.add(menu_file);
		this.setMenuBar(menuBar);

		//creating a item in bar for short path
		MenuItem Shortest_Path = new MenuItem("Short Path"); 
		Shortest_Path.addActionListener(this);

		//creating a item in bar for is connected
		MenuItem is_connected = new MenuItem("connect check"); 
		is_connected.addActionListener(this);

		menu_file.add(is_connected);
		menu_file.add(Shortest_Path);


		//listen to the mouse\\
		this.addMouseListener(this);
	}


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
			int xv=Graph.getNode(v.getKey()).getLocation().ix();
			int yv=Graph.getNode(v.getKey()).getLocation().iy();
			g.drawRect(xv-10,yv-10,20,20);
			
			g.drawString(""+v.getKey(),xv-2,yv+5);
			
			
			//points_save[0][i]=xv;
			//points_save[1][i]=yv;

			//creating edges to the vertex\\ 
			edges = Graph.getE(v.getKey());
			if(edges == null) {continue;}

			//go over the edges that come out of the specific vertex\\ 
			Iterator hit2 = edges.iterator();

			while(hit2.hasNext()) {
				
				
				g.setColor(Color.red);
				
				edge_data dest = (edge_data) hit2.next();
				//From\\
				int x1 = Graph.getNode(v.getKey()).getLocation().ix();
				int y1 = Graph.getNode(v.getKey()).getLocation().iy();
				//To\\
				int x2 = Graph.getNode(dest.getDest()).getLocation().ix();
				int y2 = Graph.getNode(dest.getDest()).getLocation().iy();

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
		
		this.Graph.addNode(user);
		
		repaint();

		
		//		Iterator hit = vertex.iterator();
		//		while(hit.hasNext()) {
		//			node_data v = (node_data) hit.next(); 
		//			int xv=Graph.getNode(v.getKey()).getLocation().ix();
		//			int yv=Graph.getNode(v.getKey()).getLocation().iy();
		//			
	}
	
	//repaint();
	//System.out.println("mousePressed"+e.getX()+e.getY());


	//	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());
		
		String action = e.getActionCommand(); 

		if(action.contentEquals("Short Path"))    //shortest path. 

		{


		JFrame frame = new JFrame();
		
		
			
			frame.getContentPane().setLayout(new FlowLayout());
			
			textfield1 = new JTextField("Enter Source",20);
			textfield2 = new JTextField("Enter Dest",20);
			submit1 = new JButton("submit dest");
			submit2 = new JButton("submit src");
			
			frame.getContentPane().add(textfield1);
			
			frame.getContentPane().add(textfield2);
			frame.getContentPane().add(submit1);
			frame.getContentPane().add(submit2);
			
			
			frame.pack();
			frame.setVisible(true);
			frame.setVisible(true);
			String text1=textfield1.getText().toString();
			String text2=textfield2.getText().toString();
			
			System.out.println(text1);
			System.out.println(text2);
			
			System.out.println(text1);
			System.out.println(text2);
			List<node_data> s_p;
			
//			s_p = this.G.shortestPath(Integer.parseInt(text1),Integer.parseInt(text2));
//			if(s_p == null ) {}
//			
//			Label jlabel = null;
//			jlabel.setText(s_p.toString());
//			
			
			G.shortestPath(getX() ,getY());
		}
		if(action.contentEquals("connect check"))
		{

			boolean a =G.isConnected();
			

			System.out.println(a);
		}

	}}
	




















