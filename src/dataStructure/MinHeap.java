package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;

import elements.NodeV;

public class MinHeap{
	ArrayList<node_data> heap;

	public MinHeap () {
		heap = new ArrayList<node_data>();}

	public void add(node_data v, double w, int prev_id,HashMap<Integer, Integer> prev) {

		if(heap.contains(v)) {
			updatew(v,w,prev_id,prev);
			return;
		}
		v.setWeight(w);
		//v.setPrev_Id(prev_id);
		prev.put(v.getKey(), prev_id);
		add_heapfyup(v);
	}

	private void add_heapfyup(node_data v) {
		heap.add(v);
		int index = heap.size()-1;
		while(index > 0) {
			int parent = (index-1)/2;

			if(heap.get(index).getWeight()< heap.get(parent).getWeight()) {
				node_data temp = heap.get(parent);
				heap.set(parent, heap.get(index));
				heap.set(index, temp);
				index = parent;
			}
			else {
				return;
			}
		}

	}

	private void updatew(node_data v, double w,int prev_id,HashMap<Integer, Integer> prev) {
		if(w < v.getWeight()) {
			v.setWeight(w);
			//v.setPrev_Id(prev_id);
			prev.replace(v.getKey(), prev_id);
		}
	}

	public node_data pop() {
		
		node_data temp = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.set(heap.size()-1, temp);
		node_data pop = heap.remove(heap.size()-1);

		heapfy_down();
		return pop;
	}

	private void swap(int a ,int b) {
		node_data temp = heap.get(a);
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






