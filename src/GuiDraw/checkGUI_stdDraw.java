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
		Graph_Algo ga= new Graph_Algo();
		double start = 0;
		double end = 100;
		
		g.addNode(new NodeV(10,10));//0
		g.addNode(new NodeV(10,40));//1
		g.addNode(new NodeV(20,70));//2
		g.addNode(new NodeV(40,50));//3
		g.addNode(new NodeV(40,90));//4
		g.addNode(new NodeV(60,70));//5
		g.addNode(new NodeV(70,90));//6
		g.addNode(new NodeV(70,20));//7
		g.addNode(new NodeV(90,60));//8
		g.addNode(new NodeV(80,50));//9
		
		g.connect(0,1,4.0);
		g.connect(1,2,5.0);
		g.connect(0,7,50.0);
		g.connect(3,0,6.0);
		g.connect(3,2,50.0);
		g.connect(3,5,1.0);
		g.connect(4,3,4.0);
		g.connect(4,6,5.0);
		g.connect(5,2,50.0);
		g.connect(6,5,6.0);
		g.connect(7,3,50.0);
		g.connect(8,9,1.0);
		g.connect(9,3,4.0);
		g.connect(2,8,5.0);
		g.connect(9,7,5.0);
		g.connect(5,4,5.0);
		
		
		ga.init(g);
		List<Integer> x = new ArrayList<Integer>();
		x.add(0);x.add(3);x.add(5);x.add(9);
		
		
		
		List<node_data> a= ga.TSP(x);
		
		if(a == null) { System.out.println("....");}
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).getKey());
		}
		
		
		
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
