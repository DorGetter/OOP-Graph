package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import elements.Edge;
import elements.NodeV;

public class DGraph implements graph{

	// contains all the vertexes by ID and weight.
	HashMap<Integer,node_data> vertex = new HashMap<Integer, node_data>();;
	// contains all the edges by ID(src ver) and edge_data. 
	HashMap<Integer, HashMap<Integer, edge_data>> edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	int id =0;
	int mc =0;

	@Override
	public node_data getNode(int key) {
		if(vertex.containsKey(key)) {
			return vertex.get(key);
		}
		System.out.println("Vertex Not Exist");
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(edges.containsKey(src) && edges.get(src).containsKey(dest)) {
			return edges.get(src).get(dest);
		}
		System.out.println("Edge Not Exist");
		return null ;
	}



	@Override
	public void addNode(node_data n) {
		if(vertex.containsValue(n)) {
			System.out.println("This vertex allready exist.");
		}
		else {
			vertex.put(n.getKey(), n);
			mc++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(vertex.containsKey(src)&&vertex.containsKey(dest)) {
			//edge existence check 
			if(edges.containsKey(src) && edges.get(src).containsKey(dest)) {
				
				System.out.println("edge allready exist.");
				return ;
			}
			//if src vertex exist in edges hash , and no dest. 
			else if(edges.containsKey(src)) {
				edges.get(src).put(dest, new Edge(vertex.get(src),vertex.get(dest),w));
			}
			else {
				edges.put(src, new HashMap<Integer, edge_data>());
				edges.get(src).put(dest, new Edge(vertex.get(src),vertex.get(dest),w));
			}

		}
		else {
			System.out.println("No Src or des to connect with.");
		}
		mc++;
	}

	@Override
	public Collection<node_data> getV() {
		Collection<node_data> v = vertex.values();
		return (Collection<node_data>) v;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {

		if(!edges.containsKey(node_id)) {
			return null;
		}
		else {
			Collection<edge_data> e = edges.get(node_id).values();
			return (Collection<edge_data>) e;
			}
	}

	@Override
	public node_data removeNode(int key) {
		//existance??
		if(!vertex.containsKey(key)) {
			System.out.println("not existed");
			return null; 
		}

		if(edges.containsKey(key)) {
			edges.remove(key);
		}
		remove_from_edges(key);
		node_data removed = vertex.get(key);
		vertex.remove(key);
		mc++;
		return removed;
		
	}

	private void remove_from_edges(int key) {
		Set setMapKey = vertex.keySet();
		Iterator hit = setMapKey.iterator();
		while(hit.hasNext()) {
			Integer temp = (Integer) hit.next();
			//go over all vertexes in edge hash
			if(edges.containsKey(temp)){
				//if there is a edge between ver B to ver(key).
				if(edges.get(temp).containsKey(key)) {
					edges.get(temp).remove(key);
					//if there is no edges in ver B(temp). 
					if(edges.get(temp).isEmpty()) {
						edges.remove(temp); 
					}
				}
			}
		}
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(edges.containsKey(src)&& edges.get(src).containsKey(dest)) {
			edge_data temp = edges.get(src).get(dest);
			edges.get(src).remove(dest);
			mc++;
			return temp; 
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return vertex.size();
	}

	@Override
	public int edgeSize() {
		
		int sum =0;
		
		Set mapSet = vertex.keySet();
		Iterator hit = mapSet.iterator();
		
		while (hit.hasNext()) {
			
			Collection<edge_data> e = getE((int)hit.next());
			if(e== null) continue;
			sum+=e.size();
		}
		
		return sum;
		
	}

	@Override
	public int getMC() {
		return mc;
	}

}
