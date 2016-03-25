package navigation;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import patternFactory.*;

public class Navigation {

	public static void main(String[] args) {
//		PatternFactory patternFactory = new PatternFactory();
		MapSetup.mapSetup();
		
//		InputPattern inputPattern = patternFactory.getPattern("DistSpeed");
//		inputPattern.readMap();
		
		// usecase 1: 08:20, from node 27 to node 1
//		InputPattern inputPattern = patternFactory.getPattern("congestionByTime");
//		inputPattern.readMap("0820");
//		// read start point and end point, convert to index
//		Dijkstra.dijkstra(27, 1);
		
		Date departureTime = parseTime("08:00");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("08:20");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("08:30");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("08:40");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("08:50");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("09:00");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("09:10");		
		navigate(27, 1, departureTime);
		
		departureTime = parseTime("09:20");		
		navigate(27, 1, departureTime);

		departureTime = parseTime("09:30");		
		navigate(27, 1, departureTime);

		departureTime = parseTime("09:40");		
		navigate(27, 1, departureTime);

		// show adjList
//		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
//		for(int n=0; n<adjList.size(); n++){
//			for(int j=0; j<adjList.get(n).size(); j++){
//				System.out.print(adjList.get(n).get(j) + " ");
//			}
//			System.out.println();
//		}

		// show nodeList
//		ArrayList<Nodes> nodeList =  NodeList.getInstance();
//		System.out.println("Total number of nodes: " + nodeList.size());
//		for(Nodes n: nodeList){
//			System.out.print(n.getNodes() + " - ");
//		}
//		System.out.println();
	}
	public static Date parseTime(String time){
		Date result = null;
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");
		try {
			result = ft.parse(time); 
//			System.out.println(ft.format(result)); 
		} catch (ParseException e) { 
			System.out.println("Unparseable using " + ft); 
		}
		return result;
	}
	
	public static void navigate(int startNode, int endNode, Date startTime){
		TimeCalculate calculate = new TimeCalculate();
		
		Date currentTime = new Date();
		Date deadline = new Date();
		
		int currentNode = startNode;
		boolean flag = true;
		
		currentTime.setTime(startTime.getTime());
		while(flag){
			// load congestion condition
			deadline = updateCongesCond(currentTime);
			ArrayList<Integer> path = Dijkstra.dijkstra(currentNode, endNode);
			
			flag = calculate.timeCalculate(path, currentTime, deadline);
			
			currentNode = calculate.getCurrentNode();
			currentTime = calculate.getCurrentTime();
		}
		ArrayList<Integer> actualPath = calculate.getActualPath();
		System.out.println("actual path: " + actualPath);
		float timeCost= (float) ((currentTime.getTime() - startTime.getTime())/60000.0);
		System.out.println(timeCost);
	}
	
	private static Date updateCongesCond(Date time) {
		Date deadline;
		PatternFactory patternFactory = new PatternFactory();
		InputPattern inputPattern = patternFactory.getPattern("congestionByTime");
		
		if(time.before(deadline = parseTime("08:20"))){
			ArrayList<Segments> segments = SegmentList.getInstance();
			for(Segments s: segments){
    			s.setCongesLvl(1);}
			System.out.println("update time: 08:00");
		}else if(time.before(deadline = parseTime("08:40"))){
			inputPattern.readMap("0820");
			System.out.println("update time: 08:20");
		}else if(time.before(deadline = parseTime("08:50"))){
			inputPattern.readMap("0840");
			System.out.println("update time: 08:40");
		}else if(time.before(deadline = parseTime("09:00"))){
			inputPattern.readMap("0850");
			System.out.println("update time: 08:50");
		}else if(time.before(deadline = parseTime("09:10"))){
			inputPattern.readMap("0900");
			System.out.println("update time: 09:00");
		}else if(time.before(deadline = parseTime("09:20"))){
			inputPattern.readMap("0910");
			System.out.println("update time: 09:10");
		}else if(time.before(deadline = parseTime("09:40"))){
			inputPattern.readMap("0920");
			System.out.println("update time: 09:20");
		}else if(time.before(deadline = parseTime("10:00"))){
			inputPattern.readMap("0940");
			System.out.println("update time: 09:40");
		}else{
			inputPattern.readMap("1000");
			System.out.println("update time: 10:00");
		}
		return deadline;
	}
}
