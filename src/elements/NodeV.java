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
	int prev_id = (int) Double.MAX_VALUE;
	Point3D point;
	double weight=0;
	int tag = 0; 
    /////////////////////////////////////////////////////////////////
    ///////////////////     Constructor     /////////////////////////
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
	
	@Override
	public int getKey() {
		return this.my_id;
	}

	@Override
	public Point3D getLocation() {	
		return this.point;
	}

	@Override
	public void setLocation(Point3D p) {
		this.point = new Point3D(p);
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;	
	}

	@Override
	public String getInfo() {
		return "("+point.toString()+")";
	}

	@Override
	public void setInfo(String s) {
		
		
	}

	@Override
	public int getTag() {
		
		return tag;
	}

	//start with 0||1 setting tag start 2 || set prev; 
	@Override
	public void setTag(int t) {// 0 = unvisited || 1 = visited || ..... 4 ||
		
		if(t<0 || t>4) {
			return;
		}
		this.tag = t;	
	}
	
	public void setPrev_Id(int id) {
		this.prev_id = id;
	}
	public int getPrev_Id() {
		return this.prev_id;
	}

}
