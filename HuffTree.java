/* A Huffman coding tree */
public class HuffTree {
	
  private Node root;  

  //Constructor
  HuffTree(Node root){
	  this.root = root;
  }
  
  //getters
  Node root() {
	  return this.root;
  }

  void setRoot(Node root) {
	  this.root = root;
  }
  
}