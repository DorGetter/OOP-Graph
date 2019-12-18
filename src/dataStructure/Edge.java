package dataStructure;

public class Edge implements edge_data{
	
	node_data src;
	node_data dest;
	double w;
	int tag=0; 
	
	public Edge(node_data src, node_data dest, double w) {
		this.src = src; 
		this.dest	 = dest; 
		this.w	=w;
		this.tag=tag;
	}

	@Override
	public int getSrc() {
		return this.src.getKey();
	}

	@Override
	public int getDest() {
		return this.dest.getKey();
	}

	@Override
	public double getWeight() {
	
		return w;
	}

	@Override
	public String getInfo() {
	
		return "("+src.getKey()+","+dest.getKey()+")";
	}
///////////////////////////////////////////////////////
	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		
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
