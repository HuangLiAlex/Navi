package navigation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapSetup{

	public static void mapSetup(){
		ArrayList<Segments> segments = SegmentList.getInstance();
		ArrayList<Nodes> nodeList =  NodeList.getInstance();
		String inputString;
		
		try {			
			String filename = "mapdata.in";
			FileInputStream fis = new FileInputStream(filename);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(fis, "UTF-8").useDelimiter("\n");
			
			while(scanner.hasNext()) {
				inputString = scanner.nextLine();
				if(!inputString.contains(",")) {
					//Inserting nodes
					int nodeName = Integer.parseInt(inputString.trim());
					
					Nodes node = new Nodes();
					node.setNodes(nodeName);
					node.setCost(Float.MAX_VALUE);
					nodeList.add(node);
				} else {
					// Add the segments to the Array segments
					// text format: node1,node2,distance
					String[] parts = inputString.split(",");
					int nodeName1 = Integer.parseInt(parts[0]);
					int nodeName2 = Integer.parseInt(parts[1]);
					String sDistance = parts[2];
					String type		 = parts[3].trim();
					float distance = Float.parseFloat(sDistance) * 1000;	// meters

					Segments s = new Segments(nodeName1, nodeName2, distance, type);
					
					s.setCost(ComputeCost.computeSingleSegCost(s));
					segments.add(s);
					
					s = new Segments(nodeName2, nodeName1, distance, type);
					s.setCost(ComputeCost.computeSingleSegCost(s));
					segments.add(s);
				} 
			} 
		} catch (FileNotFoundException e) {
			System.out.println(e);	
		}
		
		buildAdjList();
	}
	
	private static void buildAdjList() {
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		ArrayList<Segments> segments = SegmentList.getInstance();
		ArrayList<Nodes> nodeList =  NodeList.getInstance(); 				//The nodes
		
		for(int n=0; n<nodeList.size(); n++){
			ArrayList<Segments> newBranch = new ArrayList<Segments>();
			adjList.add(newBranch);
		}
		
		for(Segments s: segments){
			int startNode = s.getNode1();
			adjList.get(startNode).add(s);
		}
	}
}
