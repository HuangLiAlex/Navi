package navigation;

import java.io.*;
import java.util.*;


public class Navigation {

	public static void main(String[] args) {
		try {
			String filename = "test.in";
			FileInputStream fis = new FileInputStream(filename);
			Scanner scanner = new Scanner(fis, "UTF-8").useDelimiter("\n");
			String inputString;
//			HashMap<String, Integer> map = new HashMap<String, Integer>(); //Maps the node name to a index.
			ArrayList<Nodes> nodeList =  new ArrayList<Nodes>(); //The nodes
			ArrayList<Segments> segments =  new ArrayList<Segments>(); //All the edges from the input
			int nbrOfNodes = 0;

//			int i = 0;
			while(scanner.hasNext()) {
				inputString = scanner.nextLine();
				if(!inputString.contains(",")) {
					//Inserting nodes
					String nodeName = inputString.trim();
					
					Nodes node = new Nodes();
					node.setNodes(nodeName);
					node.setDistance(Integer.MAX_VALUE);
					nodeList.add(node);
//					map.put(nodeName, i); //node nbr i gets index nbr i in the nodes array.
//					i++;
//					nbrOfNodes++;
				} else {
					// Add the segments to the Array segments
					// text format: node1,node2,distance
					
					String[] parts1 = inputString.split(",");
					// String[] parts2 = parts1[1].split(","); 
					
					String nodeName1 = parts1[0];
					String nodeName2 = parts1[1];
					String sDistance = parts1[2];
					int distance = Integer.parseInt(sDistance);
					
					Segments s = new Segments(nodeName1, nodeName2, distance);
					segments.add(s);
				} 
			} 
			
			for(Nodes n: nodeList){
				System.out.println(n);
			}
			for(Segments s : segments){
				System.out.println(s);
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e);	
		}
	}
}
