package GuiDraw;

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
import utils.StdDraw;


public class checkGUI_stdDraw {

	public static void main(String[] args) {
		DGraph g = new DGraph();
	
		double start = 0;
		double end = 100;
		for (int i = 0; i < 10; i++) {
			double random = new Random().nextDouble();
			g.addNode(new NodeV(new Random().nextInt(100+1)+10,new Random().nextInt(100+1)+10));
			
		}
		Collection<node_data> c = g.getV();
		Iterator it = c.iterator();
		
		for (int i = 0; i < 19; i++) {
			
			double random = new Random().nextDouble();
			
			g.connect(new Random().nextInt(10+1)+0,new Random().nextInt(10+1)+0,start + (random * (end - start)));
		}
		
		
		
		
		
		StdDraw s = new StdDraw(g);
		s.play();
		
	}


}
