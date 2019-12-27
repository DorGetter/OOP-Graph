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
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import elements.Edge;
import elements.NodeV;
/**
 * This class holds implementations of graph_algorithems interface.
 * Algorithms: 
 * 			1.init(file.txt) 	 graph from file.txt.
 * 			2.save			 	 deserialized to graph object from txt file. 
 * 								 and build a new graph from it.
 * 			3.isConnected  		 checks if the graph is strongly Connected. 
 *			4.shortest_path		 return the shortest path between two Vertexes. (Using Dijakstra)
 *			5.shortest_path		 sum the shortest path weight between two vertxes. (Using Dijakstra) 			
 * 			6.TSP				 return the relatively shortest path that pass on all the targets 
 * 								 vertexes input to function.
 * 
 * 
 *Secondery Algorithems + Methods: 
 *			
 * 			1.BFS			 Breath first check algorithm Used to check isConnected.
 * 			2.Dijkstra		 calculate the shortest path between src --> dest.
 * 
 * @author Dor Getter && Omer Rugi  
 *
 */
public class Graph_Algo implements graph_algorithms{
	
	graph g;
	/**
	 * ******************************************
	 ****************Constructor*****************
	 ********************************************
	 */
	@Override
	public void init(graph g) {
		this.g = g;	
	}
	/**
	 * Initialized graph from file
	 * @param g
	 */
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
/**
 * The Method received a String name and save the graph OBJ 
 * in given name; 
 * @param file_name: Name of the file wished to save in.
 */
	@Override
	public void save(String file_name) {
		//holds the wished name to save by;
        String filename = file_name; 
          
        try
        {    
        	
            FileOutputStream file = new FileOutputStream(filename); //writing data to file;  
            ObjectOutputStream out = new ObjectOutputStream(file);  //serilaze the object to the file
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
	
	
/**
 * 			This method checks if a graph is "Strongly connected".
 * Strong Connected Directed Graph definition:  
 * 		A directed graph is called strongly connected if there is a path
 *  	between each pair of vertices of the graph.
 * 
 * 
 * Using BFS algorithm the function checks if between random points of the graph
 * there is a path ad in which all vertexes have been visited. 
 * . 
 * if all vertexes been visited it returns true;   
 */
	@Override
	public boolean isConnected() {
		Collection<node_data> vertex = g.getV();
		if(BFS(vertex)) {
			Collection<node_data> reverse_vertex = rev_collection(vertex.toArray());
			return BFS(reverse_vertex);
		}
		return false;
	}
/**
 * This method reverse a given Collection. 
 * @param array Obj array; 
 * @return reverse node_data Collection.  
 */
	private Collection<node_data> rev_collection(Object [] array) {

		Collection<node_data> rev = new ArrayList<node_data>();
		for (int i = array.length-1; i >= 0; i--) {
			rev.add((node_data) array[i]);
		}
		return rev;
	}
/**
 * Breath First search: 
 * 
 * 
 * In given Collection of vertexes from a given graph,
 * the algorithem  will check if the graph is strongly Connected.
 * Breadth first search is a graph traversal algorithm that starts traversing the graph from random node and explores
 * all the neighboring nodes. Then, it selects the nearest node and explore all the unexplored nodes. 
 * The algorithm follows the same process for each of the nearest node using a queue until queue is empty which indicates all the neighboring 
 * that are connected to the random first node are connected by path. 
 * then using a 'check_all_visited' method check if all vertexes marked visited. 
 * 
 * @param vertex collections of vertexes of the graph.
 * @return boolean , true: if all vertexes been visited, false otherwise;  
 */
	public boolean BFS(Collection<node_data> vertex) {

		Queue<node_data> q = new LinkedList<node_data>();
		if(vertex.isEmpty()) {
			return false;
		}
		all_Zero(vertex); //set tag of vertexes to 0 (unvisited); 
		Iterator hit = vertex.iterator();
		node_data a = (node_data) hit.next();
		a.setTag(1); //sets the first vertex to visited.
		q.add(a);	//adds it to queue 

		while(!q.isEmpty()) {
			
			node_data first_out = q.remove(); //dequeue the first in queue. 
			Collection<edge_data> e = g.getE(first_out.getKey()); //getting all the neighboring.
			if(e == null) {return false;} //if there are no edges coming out return false (indicates the graph cannot be connected).   
			Iterator bgu = e.iterator();
			while(bgu.hasNext()) {  //iterating over the vertex edges
				edge_data ed = (Edge) bgu.next(); 
				int nver = ed.getDest();	//gets the next neighbor. 
				if(g.getNode(nver).getTag() == 0) {	//checks if already visited. 
					g.getNode(nver).setTag(1);	//if not visited set to visited. 
					q.add(g.getNode(nver));	//add the vertex to the queue. 
				}
			}

		}
		return check_all_visited(vertex); //checks the tag on all vertexes. 
	}
/**
 * In given node_data Collection iterates all vertexes and validate all tags marked as visited (visited==1). 
 * @param vertex
 * @return true: all marked 1 , false: if at least one vertex tag 0 ;
 */
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
/**
 * Iterates Over all vertexes and set tag to 0; (unvisited);
 * @param vertex
 */
	private void all_Zero(Collection<node_data> vertex) {
		Iterator tau = vertex.iterator();
		while(tau.hasNext()) {
			node_data check = (node_data) tau.next();
			check.setTag(0);
		}
	}
/**
 * Iterates Over all vertexes and set weight to infinity; (unvisited);
 * @param vertex
 */
	private void all_inf(Collection<node_data> vertex) {
		Iterator tau = vertex.iterator();
		while(tau.hasNext()) {
			node_data check = (node_data) tau.next();
			check.setWeight(Double.MAX_VALUE);
		}
	}
/**
 * Dijkstra:
 * Given a graph and a source vertex in the graph, find shortest paths from source to all vertices in the given graph.
 * Base:
 * setting all vertexes weight to INF and tag them 0 - Unvisited; 
 * --------------------------------------------------------------
 * Algo: 
 * Extract the first src node and set his weight to 0 (The cost of path from src to src is zero); 
 * Using a Min heap add src to heap; 
 * 
 * 
 * 
 * @param src start point.
 * @param dest end point . 
 * @return
 */
	private ArrayList<node_data> Dijkstra(int src, int dest){

		Collection<node_data> vertex = g.getV();
		all_Zero(vertex);
		all_inf(vertex);

		node_data a = g.getNode(src);
		a.setWeight(0);
		int counter=0;
		
		MinHeap heap = new MinHeap();
		HashMap<Integer, Integer> prev_id = new HashMap<Integer, Integer>();
		
		heap.add((node_data) a, 0, a.getKey(),prev_id);
		if(src == dest) {
			return createpath(src,dest,prev_id);
		}
		
		while(counter != vertex.size() && !heap.isEmpty()) {
			node_data pop = heap.pop();
			if(pop.getTag()==1) {continue;}
			pop.setTag(1); // visited pop
			
			if(pop.getKey() == dest) {counter = vertex.size(); continue;}
			
			Collection<edge_data> e = g.getE(pop.getKey());		
			if(e==null) {continue;}
			Iterator<edge_data> edges =  e.iterator();

			while(edges.hasNext()) //adding all sons..
			{
				edge_data temp = edges.next();
				if(g.getNode(temp.getDest()).getTag()==1) {continue;}
				heap.add((NodeV) g.getNode(temp.getDest()) , temp.getWeight()+pop.getWeight(), pop.getKey(),prev_id);
			}
			counter++;
		}

		return createpath(src,dest,prev_id);
	}

	private ArrayList<node_data> createpath(int src,int dest,HashMap<Integer, Integer> prev_id) {

		if(g.getNode(dest).getWeight() == Double.MAX_VALUE) { return null;}
		ArrayList<node_data> path = new ArrayList<node_data>();

		node_data a = (node_data) g.getNode(dest);

		while(a.getKey() != src) {
			path.add(a);
			int prev = prev_id.get(a.getKey()) ;
			a = (node_data) g.getNode(prev);
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
		
		if(!this.isConnected()) {return null;}
		
		double min_path = Double.MAX_VALUE;
		
		double min_path_temp =-1;
		Object [] arr_temp;
		ArrayList<node_data> arr = new ArrayList<node_data>();
		ArrayList<node_data> ans = new ArrayList<node_data>();
		for (int i = 0; i < 68; i++) {  ///how much times check
			arr = new ArrayList<node_data>();
			List<Integer> tmp = shuffleTargets(targets);
			min_path_temp =0;
			for (int j = 0; j < targets.size()-1; j++) {
				arr_temp = shortestPath_Dist(tmp.get(j),tmp.get(j+1));
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
		if(t1==t2)
		{
			while(t1==t2)
			{
				t2 = new Random().nextInt(targets.size()-1);
			}
		}
	
		int temp = targets.get(t1);
		targets.set(t1, targets.get(t2));
		targets.set(t2,temp);
		
		return targets;
	}

	@Override
	public graph copy() {
		
		graph new_g = new DGraph();
		
		Collection<node_data> v = g.getV();
		Iterator v_it = v.iterator();
		while(v_it.hasNext()) {	
			node_data new_v = (node_data)v_it.next();
			new_g.addNode(new_v);
			
			Collection<edge_data> e = g.getE(new_v.getKey());
			Iterator e_it = e.iterator();
			
			while (e_it.hasNext()) {
				edge_data new_e = (edge_data) e_it.next();
				new_g.connect(new_e.getSrc(), new_e.getDest(), new_e.getWeight());
			}
		}

		return new_g;
	}


}
