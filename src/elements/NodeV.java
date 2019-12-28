package elements;

import java.io.Serializable;

import dataStructure.node_data;
import utils.Point3D;

public class NodeV implements node_data, Serializable {
	
	
    ////////////////////////////////////////////
    //////////////    fields     ///////////////
    ////////////////////////////////////////////
	static int id =0;
	int my_id =0;
	Point3D point;
	double weight=0;
	int tag = 0;
    /////////////////////////////////////////////////////////////////
    ///////////////////     Constructors    /////////////////////////
    /////////////////////////////////////////////////////////////////
	public NodeV(int x , int y, double w) {
		this.point = new Point3D(x,y);
		this.weight = w;
		my_id =id++;
		
	}
	
	public NodeV(int x , int y) {
		this.point = new Point3D(x,y);
		my_id =id++;
		
	}
	
	public NodeV(int x , int y , int z) {
		this.point = new Point3D(x,y,z);
		my_id =id++;
	}
	public NodeV(Point3D point) {
		this.point=new Point3D(point);
		my_id =id++;
	}
	public NodeV() {
		point = new Point3D(0, 0);
		my_id =id++;}
	
    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////       methods        /////////////////////////
    ///////////////////////////////////////////////////////////////////////////
	/**
	 * returns the node ID
	 */
	@Override
	public int getKey() {
		return this.my_id;
	}
/**
 * Gets the location : x & y as Point 3D. 
 */
	@Override
	public Point3D getLocation() {	
		return this.point;
	}
/**
 * Given 3D point sets the vertex coordinates.
 */
	@Override
	public void setLocation(Point3D p) {
		this.point = new Point3D(p);
	}
/**
 * returns the vertex weight.
 */
	@Override
	public double getWeight() {
		return this.weight;
	}
/**
 * sets the vertex weight.
 */
	@Override
	public void setWeight(double w) {
		this.weight = w;	
	}
/**
 * returns the vertex info. 
 */
	@Override
	public String getInfo() {
		return "("+point.toString()+")";
	}
/**
 * sets the vertex info.
 */
	@Override
	public void setInfo(String s) {
		
		id = Integer.parseInt(s);
	}
/**
 * returns the vertex tag status. 
 */
	@Override
	public int getTag() {
		
		return tag;
	}
/**
 * sets the tag of the vertex 
 * // 0 = unvisited || 1 = visited
 */
	@Override
	public void setTag(int t) {
		
		if(t<0 || t>4) {
			return;
		}
		this.tag = t;	
	}

}
