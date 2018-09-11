import java.util.ArrayList;

public class MinHeap {
	
	private ArrayList<Node> list;
	
	//Constructor
	public MinHeap() {
        this.list = new ArrayList<Node>();
    }
	
	//functions
	public Node parent(int pos) {
		return list.get(pos/2);
	}
	public Node rightChild(int pos) {
		if((2*pos) +2 >= list.size()) {
			return null;
		}
		return list.get((2*pos)+2);
	}
	public Node leftChild(int pos) {
		if((2*pos) +1 >= list.size()) {
			return null;
		}
		return list.get((2*pos)+1);
	}
	
	public boolean isLeaf(int pos) {
		if ( (2*pos)+1 < list.size() && (2*pos)+2 < list.size()) {
			return true;
		}
		else return false;
	}
	
	public void swap(int f, int s) {
		Node temp = list.get(f);
		list.set(f, list.get(s));
		list.set(s, temp);	
	}
	
	public void insert(Node node) {
		list.add(node);
		int current = list.size()-1;
		
		while(list.get(current).getWeight() < parent(current).getWeight()){
			swap(current, current/2 );
			current = current/2;
		}
	}
	
	private void minHeapify(int pos) {
		
		if (isLeaf(pos) == false ) {
			
			if ((leftChild(pos) != null && list.get(pos).getWeight() > leftChild(pos).getWeight()) || (rightChild(pos) != null && list.get(pos).getWeight() > rightChild(pos).getWeight())){
				if(leftChild(pos) != null || rightChild(pos) != null) {
					if (leftChild(pos) != null && rightChild(pos) != null) {
						if(leftChild(pos) != null && (leftChild(pos).getWeight() < rightChild(pos).getWeight())) {
							System.out.println("Got here");
							swap(pos, (pos*2)+1);
							minHeapify((pos*2)+1);
						}else {
							swap(pos, (pos*2)+2);
							minHeapify((pos*2)+2);
						}
						
					}
				}
			}
		}
	}

	public void shiftDown(int pos) {
		
		
			
			if(leftChild(pos) == null && rightChild(pos) == null) {
				return;
			}
			
			if (leftChild(pos) != null && rightChild(pos) == null) {
				if (list.get(pos).getWeight() > leftChild(pos).getWeight()) {
					swap(pos, (pos*2)+1);
					shiftDown((pos*2)+1);
				}
			}
			if (leftChild(pos) != null && rightChild(pos) != null) {
				if((leftChild(pos).getWeight() < rightChild(pos).getWeight())) {
					swap(pos, (pos*2)+1);
					minHeapify((pos*2)+1);
				}else {
					swap(pos, (pos*2)+2);
					shiftDown((pos*2)+2);
				}
			}
	
	}
	
	public void minHeap()
	{
	        for (int pos = (list.size() / 2); pos >= 1 ; pos--)
	        {
	            minHeapify(pos);
	        }	
	}
	
	public int getsize() {
		return list.size();
	}
	
	public Node remove() {
		Node pop = list.get(0);
		list.set(0, list.get(list.size()-1));
		list.remove(list.size()-1);
		shiftDown(0);
		return pop;
	}
	
	public void print() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	 
	 
	
}
