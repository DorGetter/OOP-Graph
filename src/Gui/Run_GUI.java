package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;
import elements.NodeV;

public class Run_GUI {

	public static void main(String[] args) {

		//creates the default graph. 
		graph g = new DGraph(); 

		
		/*****************************************************************************
		 * ***************************************************************************
		 * for test the graph on 100,000 vertexes and 200,000 edges					**
		 * 1. uncomment the next line. 												**
		 * 2. comment the graph generator function. 								**
		 * 3. RUN																	**
		 * * (make sure the graph generator below is on comment).					**
		 * ***************************************************************************
		 *****************************************************************************/
		
		g=test_100k_Vertexes_Edges();
		
		/*****************************************************************************
		 * ***************************************************************************
		 * Creates a default graph. 												**
		 * 1. Uncomment the next line (if needed) 									**
		 * 2. RUN																	**
		 * * (make sure the test_100k_Vertexes_Edges is on comment). 				**
		 * ***************************************************************************
		 *****************************************************************************/
		
		//g=graphGenerator();
		

		/*****************************************************************************
		 * ***************************************************************************
		 *	**	for adding a new graph manually please insert you're code below: 	**
		 *	**	for convenience graph 'g' is already initialized. 					**
		 *	**	g.addNode - adding a vertex , g.connect - adding an edge 			**
		 *	**************************************************************************
		 *****************************************************************************/
		
		//g=ggg();
		//set visibility: 
		GUI wind = new GUI(g);						
		wind.setVisible(true);
		

	}

	private static graph test_100k_Vertexes_Edges() {

		DGraph g = new DGraph();
		Graph_Algo ga= new Graph_Algo();
		int start =0,end=100;
		
		
		
		int 	random0 		= new Random().nextInt(800 + 70) +70;
		int 	random1 		= new Random().nextInt(800 + 70) +70;
		
		//creates first vertex\\
		NodeV nodetemp1 		= new NodeV(random0,random1);
		
		int key =nodetemp1.getKey();
		
		for (int i = 0; i < 50000; i++) {

			System.out.println(i);
			//create random x,y,weight\\
			int 	random2 		= new Random().nextInt(800 + 70) +70;
			int 	random3 		= new Random().nextInt(800 + 70) +70;
			double	random4 		= new Random().nextInt(50 + 1)   +1;
			
			NodeV nodetemp2 		= new NodeV(random2,random3);
			NodeV nodetemp3 		= new NodeV(random3,random2);
			
			g.addNode(nodetemp2);
			g.addNode(nodetemp3);
			
			//0 --> 1
			g.connect(key				,nodetemp2.getKey(),random3);
			//1 --> 0
			g.connect(nodetemp2.getKey(),             	key,random3);
			//1 --> 2
			g.connect(nodetemp2.getKey(),nodetemp3.getKey(),random3);
			//2 -->1
			g.connect(nodetemp3.getKey(),nodetemp2.getKey(),random3);
			
			key=nodetemp3.getKey();
			
			
		}
		ga.init(g);
		return g;
	}

	private static graph graphGenerator() {

		//creating the defualt graph;;
		DGraph g = new DGraph();
		Graph_Algo ga= new Graph_Algo();

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
		return g;
	}
	
	private static graph ggg() {
		
		DGraph graph = new DGraph();
		Graph_Algo ga= new Graph_Algo();
		node_data a = new NodeV(100,100);		node_data b =new NodeV(100,400);
		node_data c =new NodeV(200,700);		node_data d =new NodeV(400,500);
		node_data e =new NodeV(400,900);		node_data f =new NodeV(600,700);
		node_data g =new NodeV(700,900);		node_data h =new NodeV(700,200);
		node_data i =new NodeV(900,600);		node_data j =new NodeV(800,500);
		
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addNode(g);
		graph.addNode(h);
		graph.addNode(i);
		graph.addNode(j);
		

		graph.connect(a.getKey(),b.getKey(),4.0);
		graph.connect(b.getKey(),c.getKey(),5.0);
		graph.connect(a.getKey(),h.getKey(),50.0);
		graph.connect(d.getKey(),a.getKey(),6.0);
		graph.connect(d.getKey(),c.getKey(),50.0);
		graph.connect(d.getKey(),f.getKey(),1.0);
		graph.connect(e.getKey(),d.getKey(),4.0);
		graph.connect(e.getKey(),g.getKey(),5.0);
		graph.connect(f.getKey(),c.getKey(),50.0);
		graph.connect(g.getKey(),f.getKey(),6.0);
		graph.connect(h.getKey(),d.getKey(),50.0);
		graph.connect(i.getKey(),j.getKey(),1.0);
		graph.connect(j.getKey(),d.getKey(),4.0);
		graph.connect(c.getKey(),i.getKey(),5.0);
		graph.connect(j.getKey(),h.getKey(),5.0);
		graph.connect(f.getKey(),e.getKey(),5.0);
		
		ga.init(graph);
		return graph;
		
		
	}

}
