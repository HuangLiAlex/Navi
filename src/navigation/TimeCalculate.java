package navigation;

import java.sql.Date;
import java.util.ArrayList;

public class TimeCalculate {
	private Date currentTime;
	private Nodes currentNode;
	public static void timeCalculate(ArrayList<Integer> path){
		
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		int i=0;
		int j=1;
		float totalTime=0;
		float refTime=0;
		float currSegTime=0;
		
		while(j<path.size()){
			int fromNode = path.get(i);
			int toNode = path.get(j);
			
			for(Segments segment: adjList.get(fromNode)){
				if(segment.getNode2() == toNode){
					currSegTime = segment.getCost();
//					System.out.println("from node: "+ fromNode + " to node: " + toNode + " currSegTime: " + currSegTime);
					break;
				}
			}
			totalTime += currSegTime;
			refTime += currSegTime;
			
			if(refTime > 5){
				// update congestion condition
				refTime -= 5;
			}
			// next segment
			i++;
			j++;
//			System.out.println("fn: "+ fromNode + " tn: " + toNode);
		}
		
		System.out.println("total time cost: " + totalTime);
	}
	
	public Date getCurrentTime() {
		return currentTime;
	}
	
	public Nodes getCurrentNode() {
		return currentNode;
	}
}
