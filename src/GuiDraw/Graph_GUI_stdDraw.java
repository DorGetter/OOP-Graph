package GuiDraw;

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

import java.awt.Color;
import dataStructure.edge_data;
import dataStructure.node_data;
import elements.NodeV;
import utils.Point3D;
import utils.StdDraw;



public class Graph_GUI_stdDraw  {

	//holds the vertex points.\\ 
	//ArrayList<NodeV> p_list = new ArrayList<NodeV>(); 
	private Collection<node_data> vertex;

	// contains all the edges by ID(src ver) and edge_data. 
	private Collection<edge_data> edges;
	private DGraph Graph;

	public Graph_GUI_stdDraw(DGraph g) {
		this.Graph = g;
		this.vertex=g.getV();
	}
	
	public void activity () {
		
		
	}
	
	public void buildGui() {
		
		StdDraw.setCanvasSize(1080,512);
		
		StdDraw.setXscale(0, 150);
		StdDraw.setYscale(0, 150);
		Iterator hit = vertex.iterator();
		
		//call paint func\\

		while(hit.hasNext()) {
			//creating the vertex\\ 
			node_data v = (node_data) hit.next(); 
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.filledCircle(Graph.getNode(v.getKey()).getLocation().ix(),Graph.getNode(v.getKey()).getLocation().iy(), 1);	
	
			//creating edges to the vertex\\ 
			edges = Graph.getE(v.getKey());
			if(edges == null) {continue;}

			//go over the edges that come out of the specific vertex\\ 
			Iterator hit2 = edges.iterator();

			while(hit2.hasNext()) {
				
				StdDraw.setPenColor(Color.red);
				edge_data dest = (edge_data) hit2.next();
				//From\\
				int x1 = Graph.getNode(v.getKey()).getLocation().ix();
				int y1 = Graph.getNode(v.getKey()).getLocation().iy();
				//To\\
				int x2 = Graph.getNode(dest.getDest()).getLocation().ix();
				int y2 = Graph.getNode(dest.getDest()).getLocation().iy();

				//draw the line between the vertexes\\ 
				StdDraw.line(x1, y1,x2,y2);
				
				StdDraw.setPenColor(Color.YELLOW);
				//Draw the circle indicates the direction of the edge,
				//by mark a oval in the 3/4 the line next to the dest vertex. 
				StdDraw.filledCircle(((x1*1)/4)+((x2*3)/4),((y1*1)/4)+((y2*3)/4) , 1);

				double w = dest.getWeight() ;
				System.out.println(w);
				StdDraw.setPenColor(Color.MAGENTA);
				StdDraw.text((x1+x2)/2,(y1+y2)/2, w+"");
				
				
					
				}
		

			}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
