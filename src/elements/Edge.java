package elements;

import java.io.Serializable;
import java.util.Collection;
import java.util.StringTokenizer;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;


public class Edge implements edge_data, Serializable{


	////////////////////////////////////////////
	//////////////    fields     ///////////////
	////////////////////////////////////////////
	graph g;
	node_data src;
	node_data dest;
	double w;
	int tag=0; 
	/////////////////////////////////////////////////////////////////
	///////////////////     Constructor     /////////////////////////
	/////////////////////////////////////////////////////////////////
	public Edge(node_data src, node_data dest, double w,graph g) {
		this.g = g;
		this.src = src; 
		this.dest	 = dest; 
		this.w	=w;
		this.tag=tag;
	}
	///////////////////////////////////////////////////////////////////////////
	////////////////////////////       methods        /////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Will return the src of the edge.
	 */
	@Override
	public int getSrc() {
		return this.src.getKey();
	}
	/**
	 * Will return the dest of the edge.
	 */
	@Override
	public int getDest() {
		return this.dest.getKey();
	}
	/**
	 * Will return the weight of the edge.
	 */
	@Override
	public double getWeight() {

		return w;
	}
	/**
	 * Will return the starting point end and weight of the edge.
	 */

	@Override
	public String getInfo() {

		return "("+src.getKey()+","+dest.getKey()+","+this.getWeight()+")";
	}
	///////////////////////////////////////////////////////
	@Override
	public void setInfo(String s) {

		StringTokenizer st1 = new StringTokenizer(s.substring(1, s.length()-2), " ");
		int counter = 0;
		
		try {
		// Getting all the metadata from the string	
		int sr	 = Integer.parseInt((String) st1.nextElement());
		int d	 = Integer.parseInt((String) st1.nextElement());
		double w = Double.parseDouble((String) st1.nextElement());
		
		
		// Check if src and dest are valid vertex
		if(g.getNode(sr)==null) {
			System.out.println("src dosn't exist");
			return;
		}
		if(g.getNode(d)==null) {
			System.out.println("dest dosn't exist");
			return;
		}
		
		//Check if graph contain edge src --> des.
		Collection<edge_data> e = g.getE(sr);
		if(e != null && e.contains(d)) {
			System.out.println("Edge alrady exist");
			return;
		}
		}catch (Exception e) {
			System.out.println("not valid input");
		}
		
		
		
	}
	////////////////////////////////////////////////////////
	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		if(t<0 || t>4) {return; }
		else 
			this.tag=t;
	}

}
