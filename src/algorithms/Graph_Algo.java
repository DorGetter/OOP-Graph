package algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Edge;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	graph g;
	@Override
	public void init(graph g) {
		this.g = g;	
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		
		Queue<node_data> q = new LinkedList<node_data>();
		HashMap<Integer, node_data> vertex = (HashMap<Integer, node_data>)g.getV();
		if(vertex.isEmpty()) {
			return false;
		}
		all_white(vertex);
		Set setMapKey = vertex.keySet();
		Iterator hit = setMapKey.iterator();
		node_data a = (node_data) hit.next();
		a.setTag(1);
		q.add(a);
		
		while(!q.isEmpty()) {	
			node_data first_out = q.remove();
			HashMap<Integer, edge_data> edges = (HashMap<Integer, edge_data>)g.getE(a.getKey());
			Set setMapKeyE = edges.keySet();
			Iterator hitE = setMapKeyE.iterator();
		
			while(hitE.hasNext()) {
				Edge e = (Edge) hitE.next();
				int nver = e.getDest();
				if(vertex.get(nver).getTag()==0) { //0 no visit 
					vertex.get(nver).setTag(1);
					q.add(vertex.get(nver));
				}
			}
		}
		
		return check_all_visited(vertex);
		
	}

	private boolean check_all_visited(HashMap<Integer, node_data> vertex) {
		Set setMapKey = vertex.keySet();
		Iterator hitV = setMapKey.iterator();
		
		while(hitV.hasNext()) {
			node_data check = (node_data) hitV.next();
			if(check.getTag()==0) {
				return false;
			}
		}
		return true;
	}

	private void all_white(HashMap<Integer, node_data> vertex) {
		Set setMapKey = vertex.keySet();
		Iterator hit = setMapKey.iterator();
		while(hit.hasNext()) {
			node_data check = (node_data) hit.next();
			check.setTag(0);
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
