package navigation;

import java.util.ArrayList;
import java.util.Date;

public class TimeCalculate {
	private ArrayList<Integer> actualPath = new ArrayList<Integer>();
	private int nextNode;
	private Date currentTime;
	
	public boolean timeCalculate(ArrayList<Integer> path, Date currentTime, Date deadline){
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		float currSegTime=0;
		int i=0;
		int j=1;
		
		this.currentTime = currentTime;
		while(j<path.size()){
			int fromNode = path.get(i);
			int toNode = path.get(j);
			actualPath.add(fromNode);
			
			for(Segments segment: adjList.get(fromNode)){
				if(segment.getNode2() == toNode){
					currSegTime = segment.getCost();
//					System.out.println("from node: "+ fromNode + " to node: " + toNode + " currSegTime: " + currSegTime);
					break;
				}
			}

			this.currentTime.setTime(this.currentTime.getTime() + (int)(currSegTime*60000));
			
			if(this.currentTime.after(deadline)){
				this.nextNode = toNode;
				return true;
			}
			// else calculate next segment
			i++;
			j++;
		}
		actualPath.add(path.get(i));
		return false;
	}
	
	public int getCurrentNode() {
		return nextNode;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public ArrayList<Integer> getActualPath() {
		return actualPath;
	}
}
