package Gui;

import java.awt.Color;
import java.awt.Graphics;
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
import java.util.Set;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import elements.NodeV;
import utils.Point3D;



public class Graph_GUI extends JFrame implements ActionListener, MouseListener {

	//holds the vertex points.\\ 
	//ArrayList<NodeV> p_list = new ArrayList<NodeV>(); 
	private Collection<node_data> vertex;

	// contains all the edges by ID(src ver) and edge_data. 
	private Collection<edge_data> edges;
	private DGraph Graph;


	public Graph_GUI(DGraph dg)
	{
		initGUI();
		this.vertex	= dg.getV();
		this.Graph = dg;
	}

	private void initGUI() {
	
		
		this.setSize(100,100);
		this.setBackground(Color.white);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
//		//creating menu bar//
//		MenuBar menuBar	 = new MenuBar();
//		Menu menu_file	 = new Menu("file"); 
//		//adding the file section to the menu bar//
//		menuBar.add(menu_file);
//		this.setMenuBar(menuBar);
//
//		//creating a item in bar for short path
//		MenuItem Shortest_Path = new MenuItem("Short Path"); 
//		Shortest_Path.addActionListener(this);
//
//		//creating a item in bar for is connected
//		MenuItem is_connected = new MenuItem("connect check"); 
//		is_connected.addActionListener(this);
//
//		menu_file.add(is_connected);
//		menu_file.add(Shortest_Path);
//
//		//listen to the mouse\\
//		this.addMouseListener(this);
	}


	public void paint(Graphics g)
	{
		
		super.paintComponents(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(Color.blue);
		g.fillRect(25, 25, 100, 30);
		
		
		
//		//call paint func\\
//		super.paint(g);
//	
//		g.fillOval(10, 10, 10, 10);
//		Iterator hit = vertex.iterator();
//
//		while(hit.hasNext()) {
//			//creating the vertex\\ 
//			node_data v = (node_data) hit.next(); 
//			g.setColor(Color.BLUE);
//			g.fillOval(Graph.getNode(v.getKey()).getLocation().ix(),Graph.getNode(v.getKey()).getLocation().iy(), 40 , 40);	
//
//			//creating edges to the vertex\\ 
//			edges = Graph.getE(v.getKey());
//			if(edges == null) {continue;}
//
//			//go over the edges that come out of the specific vertex\\ 
//			Iterator hit2 = edges.iterator();
//
//			while(hit2.hasNext()) {
//				g.setColor(Color.red);
//				edge_data dest = (edge_data) hit2.next();
//				//From\\
//				int x1 = Graph.getNode(v.getKey()).getLocation().ix();
//				int y1 = Graph.getNode(v.getKey()).getLocation().iy();
//				//To\\
//				int x2 = Graph.getNode(dest.getDest()).getLocation().ix();
//				int y2 = Graph.getNode(dest.getDest()).getLocation().iy();
//
//				//draw the line between the vertexes\\ 
//				g.drawLine(x1, y1,x2,y2);
//
//				//Draw the circle indicates the direction of the edge,
//				//by mark a oval in the 3/4 the line next to the dest vertex. 
//				g.drawOval(((x1*1)/4)+((x2*3)/4),((y1*1)/4)+((y2*3)/4) , 3, 3);
//
//				double w = Graph.getNode(v.getKey()).getWeight();
//				g.setColor(Color.MAGENTA);
//				g.drawString(Double.toString(w),(x1+x2)/2,(y1+y2)/2);
//
//			}
//
//		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y);
	
		repaint();
		System.out.println("mousePressed"+e.getX()+e.getY());
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand(); 

		if(action.contentEquals("Short Path"))
		{
			System.out.println("svadh");
		}
		if(action.contentEquals("connect check"))
		{
			
		}
		
	}



















}
