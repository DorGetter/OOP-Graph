package algorithms;


import dataStructure.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
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
		graph temp = null;
        try
        {    
            FileInputStream file = new FileInputStream(file_name); 
            ObjectInputStream in = new ObjectInputStream(file);
            temp = (graph)in.readObject(); 
            this.init(temp);
            in.close(); 
            file.close();
            System.out.println("Object has been deserialized");
        }    
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 

	}

	@Override
	public void save(String file_name) {
		
        String filename = file_name; 
          
        try
        {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file);   
            out.writeObject(this.g); 
            out.close(); 
            file.close(); 
              
            System.out.println("Object has been serialized"); 
        }   
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
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
			return createpath(src,dest);
		}
		
		while(counter != vertex.size() && !heap.isEmpty()) {
			System.out.println("loop1");
			NodeV pop = heap.pop();
			if(pop.getTag()==1) {continue;}
			pop.setTag(1); // visited pop
			
			if(pop.getKey() == dest) {counter = vertex.size(); continue;}
			
			Collection<edge_data> e = g.getE(pop.getKey());		
			if(e==null) {continue;}
			Iterator<edge_data> edges =  e.iterator();

			while(edges.hasNext()) //adding all sons..
			{
				System.out.println("loop2");
				edge_data temp = edges.next();
				if(g.getNode(temp.getDest()).getTag()==1) {continue;}
				heap.add((NodeV) g.getNode(temp.getDest()) , temp.getWeight()+pop.getWeight(), pop.getKey());
			}
			counter++;
		}

		return createpath(src,dest);
	}

	private ArrayList<node_data> createpath(int src,int dest) {

		if(g.getNode(dest).getWeight() == Double.MAX_VALUE) { return null;}
		ArrayList<node_data> path = new ArrayList<node_data>();

		NodeV a = (NodeV) g.getNode(dest);
		//a.getPrev_Id() != a.getKey()
		while(a.getKey() != src) {
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
	
	public Object [] shortestPath_Dist(int src, int dest) {

		List<node_data> temp = Dijkstra(src, dest);
		if(temp == null ) {
			return null;
		}
		
		Object [] arr = {temp.get(temp.size()-1).getWeight(),temp};
		
		return arr;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		
		
		double min_path = Double.MAX_VALUE;
		
		double min_path_temp =-1;
		System.out.print("Loading");
		Object [] arr_temp;
		ArrayList<node_data> arr = new ArrayList<node_data>();
		ArrayList<node_data> ans = new ArrayList<node_data>();
		for (int i = 0; i < 100; i++) {  ///how much times check
			arr = new ArrayList<node_data>();
			System.out.print(".");
			List<Integer> tmp = shuffleTargets(targets);
			System.out.print("YYY");
			min_path_temp =0;
			for (int j = 0; j < targets.size()-1; j++) {
				System.out.print(">");
				arr_temp = shortestPath_Dist(tmp.get(j),tmp.get(j+1));
				System.out.print("<");
				if(arr_temp==null) {
					min_path_temp = -1;
					j=targets.size(); 
					continue;}
				min_path_temp += (double)arr_temp[0];
				List<node_data> a = (List<node_data>) arr_temp[1];
				for ( int k = 0;k< a.size(); k++) {
					if(arr.size() != 0 && arr.get(arr.size()-1).getKey() == a.get(k).getKey() ) {continue;}
					arr.add(a.get(k));
				}
			}
			if(min_path_temp < min_path && min_path_temp != -1 ) {
				min_path = min_path_temp;
				ans = new ArrayList<node_data>();
				for (int j = 0; j < arr.size(); j++) {
					ans.add(arr.get(j));
				}
			}
			
		}
		
		if(ans.size() == 0) {return ans;}
		return ans;
	}

	

	private List<Integer> shuffleTargets(List<Integer> targets) {
		
		int t1 = new Random().nextInt(targets.size()-1);
		int t2 = new Random().nextInt(targets.size()-1);
		System.out.print("X");
		if(t1==t2)
		{
			while(t1==t2)
			{
				System.out.print("X");
				t2 = new Random().nextInt(targets.size()-1);
			}
		}
		System.out.print("BBB");
		int temp = targets.get(t1);
		targets.set(t1, targets.get(t2));
		targets.set(t2,temp);
		
		return targets;
	}

	@Override
	public graph copy() {
		
		return null;
	}


}
