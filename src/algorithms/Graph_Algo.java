package algorithms;

import dataStructure.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Edge;
import elements.NodeV;
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
		Collection<node_data> vertex = g.getV();
		if(BFS(vertex)) {
			Collection<node_data> reverse_vertex = rev_collection(vertex.toArray());
			return BFS(reverse_vertex);
		}
		return false;
	}

	private Collection<node_data> rev_collection(Object [] array) {

		Collection<node_data> rev = new ArrayList<node_data>();
		for (int i = array.length-1; i >= 0; i--) {
			rev.add((node_data) array[i]);
		}
		return rev;
	}

	public boolean BFS(Collection<node_data> vertex) {

		Queue<node_data> q = new LinkedList<node_data>();
		if(vertex.isEmpty()) {
			return false;
		}
		all_white(vertex);
		Iterator hit = vertex.iterator();
		node_data a = (node_data) hit.next();
		a.setTag(1);
		q.add(a);

		while(!q.isEmpty()) {
			node_data first_out = q.remove();
			Collection<edge_data> e = g.getE(first_out.getKey());
			if(e == null) {return false;}
			Iterator bgu = e.iterator();
			while(bgu.hasNext()) {
				edge_data ed = (Edge) bgu.next();
				int nver = ed.getDest();
				if(g.getNode(nver).getTag() == 0) {
					g.getNode(nver).setTag(1);
					q.add(g.getNode(nver));
				}
			}

		}
		return check_all_visited(vertex);
	}

	private boolean check_all_visited(Collection<node_data> vertex) {

		Iterator idc = vertex.iterator();
		while(idc.hasNext()) {
			node_data check = (node_data) idc.next();
			if(check.getTag()==0) {
				return false;
			}
		}
		return true;
	}

	private void all_white(Collection<node_data> vertex) {
		Iterator tau = vertex.iterator();
		while(tau.hasNext()) {
			node_data check = (node_data) tau.next();
			check.setTag(0);
		}
	}

	private void all_inf(Collection<node_data> vertex) {
		Iterator tau = vertex.iterator();
		while(tau.hasNext()) {
			node_data check = (node_data) tau.next();
			check.setWeight(Double.MAX_VALUE);
		}
	}

	private ArrayList<node_data> Dijkstra(int src, int dest){

		Collection<node_data> vertex = g.getV();
		all_white(vertex);
		all_inf(vertex);

		node_data a = g.getNode(src);
		a.setWeight(0);
		int counter=0;
		MinHeap heap = new MinHeap();
		heap.add((NodeV) a, 0, a.getKey());
		if(src == dest) {
			return createpath(dest);
		}

		while(counter != vertex.size() && !heap.isEmpty()) {
			NodeV pop = heap.pop();
			if(pop.getTag()==1) {continue;}
			pop.setTag(1); // visited pop

			Collection<edge_data> e = g.getE(pop.getKey());		
			if(e==null) {continue;}
			Iterator<edge_data> edges =  e.iterator();

			while(edges.hasNext()) //adding all sons..
			{
				edge_data temp = edges.next();
				heap.add((NodeV) g.getNode(temp.getDest()) , temp.getWeight()+pop.getWeight(), pop.getKey());
			}
			counter++;
		}

		return createpath(dest);
	}

	private ArrayList<node_data> createpath(int dest) {

		if(g.getNode(dest).getWeight() == Double.MAX_VALUE) { return null;}
		ArrayList<node_data> path = new ArrayList<node_data>();

		NodeV a = (NodeV) g.getNode(dest);
		while(a.getPrev_Id() != a.getKey()) {
			path.add(a);
			int prev_id = a.getPrev_Id();
			a = (NodeV) g.getNode(prev_id);
		}

		path.add(a);
		path = (ArrayList<node_data>) rev_collection(path.toArray());
		return path;
	}

	@Override
	public double shortestPathDist(int src, int dest) {	

		List<node_data> temp = Dijkstra(src, dest);
		if(temp == null ) {
			return -1;
		}

		return temp.get(temp.size()-1).getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		return Dijkstra(src, dest);
	}


	@Override
	public List<node_data> TSP(List<Integer> targets) {

		HashMap<Integer, List<node_data>> keep_score = new HashMap<Integer, List<node_data>>();
		Iterator hit = targets.iterator();
		while(hit.hasNext()) {
			Integer tar = (Integer) hit.next();

			for (int i = 0; i < targets.size(); i++) {
				if(i == tar) {continue;}

				List<node_data> temp = Dijkstra(tar, i);
				keep_score.put(tar, null);
				int count =0;
				for (int j = 0; j < targets.size(); j++) {
					try {
					if(temp.contains(g.getNode(targets.get(i)))) {
						count++;
					}}catch (NullPointerException e) {}
				}
				if(count != targets.size()) {continue;}
				
				if(keep_score.get(tar) == null) { 
					keep_score.remove(tar);
					keep_score.put(tar, temp);
				}
				else if(keep_score.get(tar).size() > temp.size()) {
					keep_score.remove(tar);
					keep_score.put(tar, temp);
				}
			}
		}
		
		List<node_data> minimal_path = null;
		List<node_data> test;
		
		Set setMapKey = keep_score.keySet();
		Iterator f = setMapKey.iterator();
		while (f.hasNext()) {
			
			try {
			test = keep_score.get(f.next());
			if(minimal_path == null) {minimal_path= test;}
			else if(test.size() < minimal_path.size()){minimal_path= test;}
			}catch (NullPointerException e) {}
		}
		
		return minimal_path;
	}



	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}


}
