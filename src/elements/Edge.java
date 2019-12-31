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

	node_data src;
	node_data dest;
	double w;
	int tag=0; 
	/////////////////////////////////////////////////////////////////
	///////////////////     Constructor     /////////////////////////
	/////////////////////////////////////////////////////////////////
	public Edge() {} 
	public Edge(node_data src, node_data dest, double w) {
	
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
	public void setInfo(String s) {;}
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
