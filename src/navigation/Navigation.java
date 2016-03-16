package navigation;

import patternFactory.*;

public class Navigation {

	public static void main(String[] args) {
		PatternFactory patternFactory = new PatternFactory();
		
		InputPattern inputPattern = patternFactory.getPattern("DistSpeed");
		inputPattern.readMap();
//		inputPattern = patternFactory.getPattern("congestionByTime");
		inputPattern.readMap();
		// read start point and end point, convert to index
		Dijkstra.dijkstra(1, 27);

//		// show adjList
//		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
//		for(int n=0; n<adjList.size(); n++){
//			for(int j=0; j<adjList.get(n).size(); j++){
//				System.out.print(adjList.get(n).get(j) + " ");
//			}
//			System.out.println();
//		}

//		// show nodeList
//		HashMap<String, Integer> nodesMap = NodesMap.getInstance();
//		ArrayList<Nodes> nodeList =  NodeList.getInstance();
//		System.out.println("Total number of nodes: " + nodeList.size());
//		for(Nodes n: nodeList){
//			System.out.println(nodesMap.get(n.getNodes()));
//		}
	}		
}
