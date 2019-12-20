package elements;

import dataStructure.node_data;
import utils.Point3D;

public class NodeV implements node_data {
	
	
    ////////////////////////////////////////////
    //////////////    fields     ///////////////
    ////////////////////////////////////////////
	static int id =0;
	int my_id =0;
	Point3D point;
	int weight=0;
	int tag = 0; 
    /////////////////////////////////////////////////////////////////
    ///////////////////     Constructor     /////////////////////////
    /////////////////////////////////////////////////////////////////
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
	public NodeV() {my_id =id++;}
	
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
		this.weight = weight;	
	}

	@Override
	public String getInfo() {
		return "("+point.toString()+")";
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTag() {
		
		return tag;
	}

	@Override
	public void setTag(int t) {
		if(t<0 || t>4) {
			return;
		}
		this.tag = t;	
	}
	

}
