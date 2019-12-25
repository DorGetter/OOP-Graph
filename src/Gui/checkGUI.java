package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
		Graph_Algo ga= new Graph_Algo();
		double start = 0;
		double end = 100;
		
		g.addNode(new NodeV(100,100));//0
		g.addNode(new NodeV(100,400));//1
		g.addNode(new NodeV(200,700));//2
		g.addNode(new NodeV(400,500));//3
		g.addNode(new NodeV(400,900));//4
		g.addNode(new NodeV(600,700));//5
		g.addNode(new NodeV(700,900));//6
		g.addNode(new NodeV(700,200));//7
		g.addNode(new NodeV(900,600));//8
		g.addNode(new NodeV(800,500));//9
		
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

		Graph_GUI wind = new Graph_GUI(g);				
		wind.setVisible(true);
		
		ga.init("dor.txt");
		




	}


}
