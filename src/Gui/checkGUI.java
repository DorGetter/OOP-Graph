package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.MinHeap;
import dataStructure.graph;
import dataStructure.node_data;
import elements.NodeV;

public class checkGUI {

	public static void main(String[] args) {
		DGraph g = new DGraph();
				
				
				g.addNode(new NodeV(12,10));
				g.addNode(new NodeV(22,18));
				g.addNode(new NodeV(5,25));
				g.addNode(new NodeV(11,35));
				g.addNode(new NodeV(8,14));
				
				Collection vertex = g.getV();
				Iterator hit = vertex.iterator();
				
				while(hit.hasNext()) {
					NodeV temp = (NodeV) hit.next();
					System.out.println(temp.getKey());
				}
				
				g.connect(0, 1, 1);
				g.connect(1, 2, 3);
				g.connect(2, 3, 4);
				g.connect(3, 2, 1);
				g.connect(1, 3, 1);
				g.connect(3, 4, 6);
				g.connect(4, 0, 6);
				Graph_Algo ga = new Graph_Algo();
				ga.init(g);
				System.out.println(ga.isConnected());
				
				Graph_GUI wind = new Graph_GUI(g);
				
				
				
				
				
				
				
				
				
				wind.setVisible(true);
		NodeV a = new NodeV();
		NodeV b = new NodeV();
		NodeV c = new NodeV();
		NodeV d = new NodeV();
		NodeV e = new NodeV();
		NodeV f = new NodeV();
		NodeV w = new NodeV();
		NodeV h = new NodeV();
		NodeV q = new NodeV();
		a.setWeight(12);
		b.setWeight(12);
		c.setWeight(12);
		d.setWeight(12);
		e.setWeight(12);
		f.setWeight(12);
		w.setWeight(12);
		h.setWeight(12);

		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		g.addNode(f);
		g.addNode(w);
		g.addNode(h);
		g.addNode(q);

		g.connect(a.getKey(), b.getKey(), 2);

		g.connect(a.getKey(), w.getKey(), 30);
		g.connect(w.getKey(), f.getKey(), 1);
		g.connect(d.getKey(), w.getKey() ,2);
		g.connect(a.getKey() ,c.getKey() ,10);
		g.connect(c.getKey(),d.getKey(), 5);
		g.connect(c.getKey(),f.getKey(), 15);
		g.connect(f.getKey(), d.getKey(),3);
		g.connect(d.getKey(),e.getKey(), 20);
		g.connect(b.getKey(), e.getKey(),40);
		g.connect(e.getKey(), h.getKey(),10);
		g.connect(w.getKey(), h.getKey(),12);
		Graph_Algo gr = new Graph_Algo();
		gr.init(g);
		System.out.println(gr.shortestPathDist(a.getKey(), q.getKey()));
		List<node_data> path = gr.shortestPath(a.getKey(), q.getKey());
		



	}


}
