import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanEncoder implements HuffmanCoding {
	
	//these variables are to store the values of the characters, and have them in ASCII order
	int table[];
	String travel[];
	
	
	/*This function returns a string with the number of times a character is in the file.*/
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		
		FileReader fileReader = new FileReader(inputFile);
		table = new int[128];
		int ch;
		 
		while (true) {
			try {
				ch = fileReader.read();
				if(ch == -1) {
					break;
				}
				table[ch]++; 
			} catch (IOException e) {
				System.out.println("File not found");
			}
		}
		
		StringBuilder S = new StringBuilder();
		
		for (int i = 0; i < 128; i++)
		{
			if (table[i] != 0) 
			{
				S.append((char)i + " " + table[i] + "\n");
			}
		} 
		
		String Str = S.toString();
		return Str;
	}

	/*I use a Priority Queue to create a the Huff Tree, I put the two smallest nodes together, forming a parent node, and 
	 * inserted this one back to the queue, eventually, there is one node left, I pop this node, and set it a the root of my huff tree*/
	
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		PriorityQueue<Node> q = new PriorityQueue();
			
		getFrequencies(inputFile);
		
		Node tmp;
		for (int i = 0; i < 128; i++) {
			 if(table[i] != 0) {
				 tmp = new Node((char)(i) , table[i]);
				 q.add(tmp);
			 }
		}
	
		Node com = null;

		while(q.size() > 1) {
			Node node1 = q.poll();
			Node node2 = q.poll();
			com = new Node(node1, node2, 'z', node1.getWeight() + node2.getWeight());
			q.add(com);
			
		}
		
		Node root = q.poll();
		HuffTree tree = new HuffTree(root);
		
		return tree;
	}

	/*
		By calling the traverse function, the travel array, has stores the string of every character in ASCII order, then I just read the 
		file, and go to the proper position of the array and get the code string value inside. I put all the Sting codes together in a string, and return this string
	 */
	public String encodeFile(File inputFile, HuffTree huffTree) {
		StringBuilder Str = new StringBuilder();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(inputFile);
			traverseHuffmanTree(huffTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ch;
		 
		while (true) {
			try {
				ch = fileReader.read();
				
				if(ch == -1) {
					break;
				}
				Str.append(travel[ch]); 
				
			} catch (IOException e) {
				
				System.out.println("File not found");
			
			}
		}
		
		String s = Str.toString();
		return s;
	}

	
	/*To decode the code I am traversing the tree, going to the proper leafs as a read the '1's and 
	'0's from the code. Then I append the characters in the leafs to a String. Finally, I return this string*/
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		char now;
		StringBuilder S = new StringBuilder();
		String str = "";
		Node current = huffTree.root();		
		for (int i = 0; i < code.length(); i++) {
			now = code.charAt(i);
			if(now == '0') {
				if(current.left() != null) {
					current = current.left();
				}
			}
			if(now == '1') {
				if(current.right() != null) {
					current = current.right();
				}
			}
			if(current.isLeaf()) {
				S.append(current.getValue());
				current = huffTree.root();
			}
			
		}
		str = S.toString();
		return str;
	}

	
	//to traverse I used a helper function, that traverses the tree recursively and stored the string codes into the travel array accodring to the ACII value
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		
		Node root = huffTree.root();
		travel = new String[128];
		StringBuilder S = new StringBuilder();

		traverse(root, "");
		
		for (int i =0; i < 128; i++) {
			if (travel[i] != null) {
				S.append((char)i + " " + travel[i] + "\n");
			}
		}
		
		String str = S.toString();
		return str;
	}
	
	//helper function to traverse the tree
	public void traverse (Node root, String str){ // Each child of a tree is a root of its subtree.
	    if (root.left() != null){
	        traverse (root.left(), str + "0");
	    }
	    if (root.isLeaf()) {
	    		travel[root.getValue()] = str;
	    }
	    if (root.right() != null){
	        traverse (root.right(), str + "1");
	    }
	    return;
	}
}
