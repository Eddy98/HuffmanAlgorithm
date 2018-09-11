import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class test {

	
	public static void main(String[] args) throws Exception{
		HuffmanCoding test = new HuffmanEncoder();
		
		File file = new File("../illiad.txt");		
		HuffTree tree = test.buildTree(file);
		
		test.traverseHuffmanTree(tree);
		
		
//		long start = System.currentTimeMillis();
//		
//		
//		String code = test.encodeFile(file, tree);
//		
//		test.decodeFile(code, tree);
//		
//		long end = System.currentTimeMillis();
//		
//		System.out.println(end - start);
		
//
		String code = test.encodeFile(file, tree);
		System.out.println(code);
		
		System.out.println();
		System.out.println(test.decodeFile(code, tree));
		System.out.println();
		
		
		
	}
	
}
