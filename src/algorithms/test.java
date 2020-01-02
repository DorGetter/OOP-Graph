package algorithms;

import java.awt.List;
import java.util.ArrayList;

import dataStructure.DGraph;
import dataStructure.graph;
import elements.NodeV;

public class test {
public static void main(String[] args) {
	

	DGraph g = new DGraph();
	Graph_Algo ga= new Graph_Algo();

	g.addNode(new NodeV(100,100));		g.addNode(new NodeV(100,400));
	g.addNode(new NodeV(200,700));		g.addNode(new NodeV(400,500));
	g.addNode(new NodeV(400,900));		g.addNode(new NodeV(600,700));
	g.addNode(new NodeV(700,900));		g.addNode(new NodeV(700,200));
	g.addNode(new NodeV(900,600));		g.addNode(new NodeV(800,500));
	
	g.connect(0,1,4.0);					g.connect(1,2,5.0);
	g.connect(0,7,50.0);				g.connect(3,0,6.0);
	g.connect(3,2,50.0);				g.connect(3,5,1.0);
	g.connect(4,3,4.0);					g.connect(4,6,5.0);
	g.connect(5,2,-50.0);				g.connect(6,5,6.0);
	g.connect(7,3,50.0);				g.connect(8,9,1.0);
	g.connect(9,3,4.0);					g.connect(2,8,5.0);
	g.connect(9,7,5.0);					g.connect(5,4,5.0);
	
	DGraph e = new DGraph();
	e.edgeSize();
	e.getE(3);
	e.getEdge(3, 4);
	e.getMC();
	g.getNode(4);
	e.getV();
	e.removeEdge(5, 6);
	e.addNode(new NodeV(3,4));
	e.connect(5, 6, 1);
	e.nodeSize();
	
	//ga.init(e);
	
	ArrayList<Integer> targets = new ArrayList<Integer>();
	targets.add(6);
	targets.add(6);
	targets.add(6);
	targets.add(6);
	targets.add(5);
	targets.add(4);
	targets.add(3);
	targets.add(1);
	targets.add(0);
	targets.add(6);
	targets.add(6);
	
	ga.save("test");
	
	ga.TSP(targets);
	ga.copy();
	ga.isConnected();
	
}
	
	
	
	
}
