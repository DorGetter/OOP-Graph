package dataStructure;

import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import elements.NodeV;

public class TEST {

	public static void main(String[] args) {

		graph g = new DGraph();
		
		g.addNode(new NodeV(1,2));
		g.addNode(new NodeV(3,5));
		g.addNode(new NodeV(6,8));
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 3; i++) {
			list.add(g.getNode(i).getKey());
		}
		
		g.connect(0, 1, 1);
		g.connect(1, 2, 1);
		g.connect(0, 2, 0);
		
		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		
		ga.TSP(list);
		
		List<node_data> end = ga.TSP(list);
		
		for (int i = 0; i < end.size(); i++) {
			System.out.println(end.get(i).getKey());
		}
		

	}

}
