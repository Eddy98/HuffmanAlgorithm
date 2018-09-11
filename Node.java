
public class Node implements Comparable {
	private char value;
	private int weight;
	private Node left;
	private Node right;
	
	//constructor
	Node(Node left, Node right, char value, int weight){
		this.left = left;
		this.right = right;
		this.value = value;
		this.weight =weight;
	}
	
	//contructor2
	Node(char value, int weight){
		this.value = value;
		this.weight =weight;
		this.right = null;
		this.left = null;
	}
	
	//setters
	void setValue(char value) {
		this.value = value;
	}
	void setWeight(int weight) {
		this.weight = weight;
	}
	
	//getters
	char getValue() {
		return this.value;
	}
	int getWeight() {
		return this.weight;
	}
	Node left() {
		return this.left;
	}
	Node right() {
		return this.right;
	}
	
	//functions
	public String toString() {
		  return this.value + " " + this.weight;				  
	  }
	
	boolean isLeaf() {
		if (this.right == null && this.left == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int compareTo(Object t) {
	    Node that = (Node)t;
	    if (weight < that.getWeight()) return -1;
	    else if (weight == that.getWeight()) return 0;
	    else return 1;
	  }
	
}
