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
		
		g.addNode(new NodeV(1,4));//0
		g.addNode(new NodeV(1,20));//1
		g.addNode(new NodeV(1,50));//2
		g.addNode(new NodeV(1,90));//3
		g.addNode(new NodeV(30,10));//4
		g.addNode(new NodeV(30,30));//5
		g.addNode(new NodeV(30,90));//6
		g.addNode(new NodeV(20,10));//7
		g.connect(0, 1, 2.0);
		g.connect(0, 2, 2.0);
		g.connect(0, 3, 2.0);
		g.connect(0, 4, 2.0);
		g.connect(0, 5, 2.0);
		g.connect(0, 6, 2.0);
		g.connect(1, 2, 2.0);
		g.connect(1, 3, 2.0);
		g.connect(1, 4, 2.0);
		g.connect(1, 6, 2.0);
		g.connect(4, 2, 2.0);
		g.connect(7, 1, 2.0);
		g.connect(9, 5, 2.0);
		g.connect(2, 5, 2.0);
		g.connect(7, 6, 2.0);
		g.connect(6, 7, 2.0);
		g.connect(3, 2, 2.0);
		g.connect(5, 1, 2.0);
		g.connect(1, 2, 2.0);
		g.connect(10, 1, 2.0);
		StdDraw s = new StdDraw(g);
		s.play();
//		for (int i = 0; i < 10; i++) {
//			double random = new Random().nextDouble();
//			g.addNode(new NodeV(new Random().nextInt(100+1)+10,new Random().nextInt(100+1)+10));
//			
//		}
//		Collection<node_data> c = g.getV();
//		Iterator it = c.iterator();
//		
//		for (int i = 0; i < 19; i++) {
//			
//			double random = new Random().nextDouble();
//			
//			g.connect(new Random().nextInt(10+1)+0,new Random().nextInt(10+1)+0,start + (random * (end - start)));
//		}
//		
//		
//		
//		
//		
//		StdDraw s = new StdDraw(g);
//		s.play();
		
	}


}
