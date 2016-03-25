package navigation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Dijkstra {
	public static ArrayList<Integer> dijkstra(int startPoint, int endPoint){
//		HashMap<String, Integer> nodesMap = NodesMap.getInstance(); //Maps the node name to a index.
		ArrayList<Nodes> nodeList =  NodeList.getInstance(); //The nodes
		HashSet<Integer> visited = new HashSet<Integer>();
		HashSet<Integer> unvisited = new HashSet<Integer>();
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> shorteastPath = new ArrayList<Integer>();
		int currentPoint;
		
		for(Nodes n: nodeList){
			n.setCost(Float.MAX_VALUE);
		}
		unvisited.add(startPoint);
		nodeList.get(startPoint).setCost(0);
		while(!unvisited.isEmpty() && !visited.contains(endPoint)){
			currentPoint = minOfUnvisited(unvisited);
			unvisited.remove(currentPoint);
			visited.add(currentPoint);
			path.add(currentPoint);
			
			for(Segments neighbor: adjList.get(currentPoint)){
//				int node2 = nodesMap.get(neighbor.getNode2());
				int node2 = neighbor.getNode2();
				if(!visited.contains(node2)){
					unvisited.add(node2);	// add neighbor index to unvisited
					float segCost = neighbor.getCost();
					float newCost = segCost + nodeList.get(currentPoint).getCost();
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
		int nextIdx = path.indexOf(evalPoint);
		shorteastPath.add(endPoint);
		
//		float timeCost = 0;
		DecimalFormat fmt = new DecimalFormat(".00");
		while(evalPoint != startPoint){
			nextIdx++;
			int nextNode = path.get(nextIdx);
//			System.out.println(nextIdx);
			
			for(Segments segment: adjList.get(nextNode)){
				float cost = nodeList.get(evalPoint).getCost() - segment.getCost();
				float nodeCost = nodeList.get(nextNode).getCost();
//				System.out.println(fmt.format(segCost) + "--" + fmt.format(nodeCost));
//				if(nodesMap.get(segment.getNode2()) == evalPoint &&  fmt.format(cost).equals(fmt.format(nodeCost))){
				if(segment.getNode2() == evalPoint &&  fmt.format(cost).equals(fmt.format(nodeCost))){
//					timeCost += segment.getCost();
					shorteastPath.add(nextNode);
					evalPoint = nextNode;
					nextIdx = path.indexOf(evalPoint);
					break;
				}
			}
		}
		Collections.reverse(shorteastPath);
//		System.out.println(shorteastPath);
//		TimeCalculate.timeCalculate(shorteastPath);
//		System.out.println("time spend: " + fmt.format(timeCost) + " min");
		return shorteastPath;
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
