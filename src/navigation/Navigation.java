package navigation;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


public class Navigation {

	public static void main(String[] args) {
		HashMap<String, Integer> nodesMap = new HashMap<String, Integer>(); //Maps the node name to a index.
		ArrayList<Segments> segments = new ArrayList<Segments>(); //All the edges from the input
		ArrayList<Nodes> nodeList =  NodeList.getInstance(); //The nodes
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		
		readMap(nodesMap, nodeList, segments);

		for(@SuppressWarnings("unused") Nodes n: nodeList){
			ArrayList<Segments> newBranch = new ArrayList<Segments>();
			adjList.add(newBranch);
		}
		
		for(Segments s: segments){
			int startNode = nodesMap.get(s.getNode1());
			adjList.get(startNode).add(s);
		}

		// read start point and end point, convert to index
		dijkstra(nodesMap, 0, 10);

//		for(int n=0; n<adjList.size(); n++){
//			for(int j=0; j<adjList.get(n).size(); j++){
//				System.out.print(adjList.get(n).get(j) + " ");
//			}
//			System.out.println();
//		}
		
//		System.out.println(nodeList.size());
//		for(Nodes n: nodeList){
//			System.out.println(nodesMap.get(n.getNodes()));
//		}
		
//		System.out.println(nodeList.get(0).getNodes());
//		for(Segments s : segments){
//			System.out.println(s);
//		}
			
	}

	private static void readMap(HashMap<String, Integer> nodesMap, ArrayList<Nodes> nodeList, ArrayList<Segments> segments) {
		String inputString;
		int i = 0;
		
		try {			
			String filename = "test.in";
			FileInputStream fis = new FileInputStream(filename);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(fis, "UTF-8").useDelimiter("\n");
			
			while(scanner.hasNext()) {
				inputString = scanner.nextLine();
				if(!inputString.contains(",")) {
					//Inserting nodes
					String nodeName = inputString.trim();
					
					Nodes node = new Nodes();
					node.setNodes(nodeName);
					node.setCost(Float.MAX_VALUE);
					nodeList.add(node);
					
					nodesMap.put(nodeName, i); //node nbr i gets index nbr i in the nodes array.
					i++;
				} else {
					// Add the segments to the Array segments
					// text format: node1,node2,distance
					String[] parts1 = inputString.split(",");
					String nodeName1 = parts1[0];
					String nodeName2 = parts1[1];
					String sDistance = parts1[2];
					String type		 = parts1[3];
					float distance = Float.parseFloat(sDistance);
					int speed;
					if(type.trim().equals("express"))
						speed = 80;
					else
						speed = 40;
					
					Segments s = new Segments(nodeName1, nodeName2, distance, speed);
					segments.add(s);
				} 
			} 
		} catch (FileNotFoundException e) {
			System.out.println(e);	
		}
	}
	
	// Dijkstra
	private static void dijkstra(HashMap<String, Integer> nodesMap, int startPoint, int endPoint){
		ArrayList<Nodes> nodeList =  NodeList.getInstance(); //The nodes
		HashSet<Integer> visited = new HashSet<Integer>();
		HashSet<Integer> unvisited = new HashSet<Integer>();
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> shorteastPath = new ArrayList<Integer>();
		int currentPoint;
		
		unvisited.add(startPoint);
		nodeList.get(startPoint).setCost(0);
		while(!unvisited.isEmpty() && !visited.contains(endPoint)){
			currentPoint = minOfUnvisited(unvisited);
			unvisited.remove(currentPoint);
			visited.add(currentPoint);
			path.add(currentPoint);
			
			for(Segments neighbor: adjList.get(currentPoint)){
				int node2 = nodesMap.get(neighbor.getNode2());				
				if(!visited.contains(node2)){
					unvisited.add(node2);	// add neighbor index to unvisited
					float cost = neighbor.getDistance() / neighbor.getSpeed();
					float newCost = cost + nodeList.get(currentPoint).getCost();
					if(newCost < nodeList.get(node2).getCost()){
						nodeList.get(node2).setCost(newCost);
					}
				}
			}
		}
		
		// return the path
		int evalPoint = endPoint;
//		System.out.println(path);
		Collections.reverse(path);
		System.out.println(path);
		int nextIdx = path.indexOf(evalPoint);
		shorteastPath.add(endPoint);
		
		while(evalPoint != startPoint){
			nextIdx++;
			int nextNode = path.get(nextIdx);
			System.out.println(nextIdx);
			
			for(Segments s: adjList.get(nextNode)){
				DecimalFormat fmt = new DecimalFormat(".0000");
				float segCost = nodeList.get(evalPoint).getCost() - s.getDistance()/s.getSpeed();
				float nodeCost = nodeList.get(nextNode).getCost();
//				System.out.println(fmt.format(segCost) + "--" + fmt.format(nodeCost));
				if(nodesMap.get(s.getNode2()) == evalPoint &&  fmt.format(segCost).equals(fmt.format(nodeCost))){
					shorteastPath.add(nextNode);
					evalPoint = nextNode;
					nextIdx = path.indexOf(evalPoint);
					break;
				}
			}

			
		}
		Collections.reverse(shorteastPath);
		System.out.println(shorteastPath);
	}
	
	private static int minOfUnvisited(HashSet<Integer> unvisited){
		ArrayList<Nodes> nodeList =  NodeList.getInstance();
		int idxOfMin = 0;
		float minValue = Float.MAX_VALUE;
		
		for(int i: unvisited){
			if(nodeList.get(i).getCost() < minValue){
				idxOfMin = i;
				minValue = nodeList.get(i).getCost();
			}
		}
		return idxOfMin;
	}
			
}
