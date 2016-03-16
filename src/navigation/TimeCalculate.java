package navigation;

import java.util.ArrayList;

public class TimeCalculate {

	public static void timeCalculate(ArrayList<Integer> path){
		// for each segment
		// 		get fromNode
		// 		get toNode
		// 		get segment
		// 		get segment.cost
		// 		increament totalTime, refTime
		// 		if refTime > 5 min
		// 			update congestion condition
		// 			refTime -= 5
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
}
