package JFRAME;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.naming.InitialContext;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.MinHeap;
import dataStructure.graph;
import dataStructure.node_data;
import elements.NodeV;


public class checkGUI_stdDraw {

	public static void main(String[] args) {
		DGraph g = new DGraph();
	
		double start = 0;
		double end = 100;
		for (int i = 0; i < 25; i++) {
			double random = new Random().nextDouble();
			g.addNode(new NodeV(new Random().nextInt(100+1)+10,new Random().nextInt(100+1)+10));
			
		}
		Collection<node_data> c = g.getV();
		Iterator it = c.iterator();
		
		for (int i = 0; i < 100; i++) {
			
			double random = new Random().nextDouble();
			
			g.connect(new Random().nextInt(25+1)+0,new Random().nextInt(25+1)+0,start + (random * (end - start)));
		}
		
		
		
		
		
		Graph_GUI_stdDraw a = new Graph_GUI_stdDraw(g);
		a.buildGui();
		Collection vertex = g.getV();
		Iterator hit = vertex.iterator();

		while(hit.hasNext()) {
			NodeV temp = (NodeV) hit.next();
			System.out.println(temp.getKey());
		}



		
		



	}


}
