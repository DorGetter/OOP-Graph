package dataStructure;

import java.util.ArrayList;

import elements.NodeV;

public class MinHeap{
	ArrayList<NodeV> heap;

	public MinHeap () {
		heap = new ArrayList<NodeV>();}

	public void add(NodeV v, double w, int prev_id) {

		if(heap.contains(v)) {
			updatew(v,w,prev_id);
			return;
		}
		v.setWeight(w);
		v.setPrev_Id(prev_id);
		add_heapfyup(v);
	}

	private void add_heapfyup(NodeV v) {
		heap.add(v);
		int index = heap.size()-1;
		while(index > 0) {
			int parent = (index-1)/2;

			if(heap.get(index).getWeight()< heap.get(parent).getWeight()) {
				NodeV temp = heap.get(parent);
				heap.set(parent, heap.get(index));
				heap.set(index, temp);
				index = parent;
			}
			else {
				return;
			}
		}

	}

	private void updatew(NodeV v, double w,int prev_id) {
		if(w<v.getWeight()) {
			v.setWeight(w);
			v.setPrev_Id(prev_id);
		}
	}

	public NodeV pop() {
		
		NodeV temp = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.set(heap.size()-1, temp);
		NodeV pop = heap.remove(heap.size()-1);

		heapfy_down();
		return pop;
	}

	private void swap(int a ,int b) {
		NodeV temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}

	private void heapfy_down() {

		int right 		=2;
		int left 		=1;
		int movingindex =0;
		int end 		=heap.size()-1; 


		while(movingindex != end && right < end && left < end) {

			if(heap.get(movingindex).getWeight() > heap.get(left).getWeight())
			{
				if(heap.get(left).getWeight() < heap.get(right).getWeight())
				{
					swap(movingindex,left);
					movingindex = left; 
					left = (movingindex*2)+1;
					right= (movingindex*2)+2;
				}
				else if(heap.get(right).getWeight() < heap.get(left).getWeight())
				{
					swap(movingindex,right);
					movingindex = right; 
					left = (movingindex*2)+1;
					right= (movingindex*2)+2;
				}
				else 
				{
					swap(movingindex,left);
					movingindex = left; 
					left = (movingindex*2)+1;
					right= (movingindex*2)+2;
				}

			} else return; 
		}
		
		if(left<end) 
		{
			if(heap.get(left).getWeight() < heap.get(movingindex).getWeight())
			 {swap(movingindex,left);}
		}
	}
	
	public String toString() {
		String print = "[";
		for (int i = 0; i <heap.size(); i++) {
			print+=heap.get(i).getWeight();
			if(i != heap.size()-1) {
				print+=",";
			}
		}
		print+="]";
		return print;
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
}






