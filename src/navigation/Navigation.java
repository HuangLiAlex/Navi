package navigation;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import patternFactory.*;

public class Navigation {

	private static final int default_diverse_rate = 10;

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
				
		Date departureTime = parseTime("08:00");	// when road is not congested
		System.out.println("start time: 08:00");
		ArrayList<Integer> mainPath = navigate(1, 27, departureTime);
		System.out.println(mainPath);
		System.out.println();
		
//		departureTime = parseTime("08:20");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);		
//		departureTime = parseTime("08:30");	
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);		
//		departureTime = parseTime("08:40");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);		
		
		// get 1st divert path
		departureTime = parseTime("08:50");
		System.out.println("start time: 08:50");
		ArrayList<Integer> divertPath = navigate(1, 27, departureTime);
		System.out.println(divertPath);
		System.out.println();
		
		
		// under current road condition, estimate time cost
		System.out.println("*** Before divert ***");
		System.out.println("main path: " + mainPath);
		showPathInfo(mainPath);
		
		System.out.println("detour path: " + divertPath);
		showPathInfo(divertPath);
		
		System.out.println("*** After divert ***");
		
		Utilisation.divert(mainPath, divertPath, default_diverse_rate);
		
		System.out.println("main path: " + mainPath);
		float timeSpend1 = showPathInfo(mainPath);
		
		System.out.println("detour path: " + divertPath);
		float timeSpend2 = showPathInfo(divertPath);
		
		findOptimun(mainPath, divertPath, timeSpend1, timeSpend2);
		
		// block divert path option 1 for another option
		PatternFactory patternFactory = new PatternFactory();
		InputPattern inputPattern2 = patternFactory.getPattern("CongestionByIncident");
		inputPattern2.readMap("case2");
		
		System.out.println("*** Before divert ***");
		System.out.println("main path: " + mainPath);
		showPathInfo(mainPath);
		
		ArrayList<Integer> divertPath2 = Dijkstra.dijkstra(1, 27);
		System.out.println("detour path 2: " + divertPath2);
		showPathInfo(divertPath2);
		
		System.out.println("*** After divert ***");
		Utilisation.divert(mainPath, divertPath2, default_diverse_rate);
		
		System.out.println("main path: " + mainPath);
		timeSpend1 = showPathInfo(mainPath);
		
		System.out.println("detour path 2: " + divertPath2);
		timeSpend2 = showPathInfo(divertPath2);
		
		findOptimun(mainPath, divertPath2, timeSpend1, timeSpend2);
		
//		departureTime = parseTime("09:00");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);		
//		departureTime = parseTime("09:10");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);		
//		departureTime = parseTime("09:20");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);
//		departureTime = parseTime("09:30");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);
//		departureTime = parseTime("09:40");
//		System.out.println("start time: " + departureTime.getHours()+":"+departureTime.getMinutes());
//		navigate(1, 27, departureTime);		
		
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
	
	private static float showPathInfo(ArrayList<Integer> mainPath) {
		float timeSpend = TimeCalculate.calcuPathCost(mainPath);
		System.out.println("total time cost: " + timeSpend);
		Utilisation.showUtilisation(mainPath);
		System.out.println();
		return timeSpend;
	}
	
	private static void findOptimun(ArrayList<Integer> mainPath,
			ArrayList<Integer> divertPath, float timeSpend1, float timeSpend2) {
		int tolerance = 3;
		System.out.println(">>> find a balance");
		int diverse_rate = default_diverse_rate/2;
		while(Math.abs(timeSpend1 - timeSpend2) > tolerance && diverse_rate > 0){
			if(timeSpend1 > timeSpend2){
				Utilisation.divert(mainPath, divertPath, diverse_rate);			
			}else{
				Utilisation.divert(divertPath, mainPath, diverse_rate);
			}
			diverse_rate /= 2;
			System.out.println("main path: " + mainPath);
			timeSpend1 = TimeCalculate.calcuPathCost(mainPath);
			System.out.println("total time cost: " + timeSpend1);
			Utilisation.showUtilisation(mainPath);
			System.out.println();
			
			System.out.println("detour path: " + divertPath);
			timeSpend2 = TimeCalculate.calcuPathCost(divertPath);
			System.out.println("total time cost: " + timeSpend2);
			Utilisation.showUtilisation(divertPath);
			System.out.println();
		}
		System.out.println(">>> end of diverse");
		System.out.println();
	}
	
	private static Date parseTime(String time){
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
	
	private static ArrayList<Integer> navigate(int startNode, int endNode, Date startTime){
		TimeCalculate calculate = new TimeCalculate();
		
		Date currentTime = new Date();
		Date deadline = new Date();
		
		int currentNode = startNode;
		boolean flag = true;
		
		currentTime.setTime(startTime.getTime());
		while(flag){
			// load congestion condition
			System.out.println("current node: " + currentNode);
			deadline = updateCongesCond(currentTime);
			ArrayList<Integer> path = Dijkstra.dijkstra(currentNode, endNode);
			
			flag = calculate.timeCalculate(path, currentTime, deadline);
			
			currentNode = calculate.getCurrentNode();
			currentTime = calculate.getCurrentTime();
		}
		ArrayList<Integer> actualPath = calculate.getActualPath();
//		System.out.println("actual path: " + actualPath);
		float timeCost= (float) ((currentTime.getTime() - startTime.getTime())/60000.0);
		System.out.println("total time spend: " + timeCost);
		return actualPath;
	}
	
	private static Date updateCongesCond(Date time) {
		Date deadline;
		PatternFactory patternFactory = new PatternFactory();
		InputPattern inputPattern = patternFactory.getPattern("CongestionByTime");
		InputPattern inputPattern2 = patternFactory.getPattern("CongestionByIncident");

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
//			inputPattern2.readMap("0840"); 		// hardcode accident
			System.out.println("update time: 08:40");
		}else if(time.before(deadline = parseTime("09:00"))){
			inputPattern.readMap("0850");
			System.out.println("update time: 08:50");
		}else if(time.before(deadline = parseTime("09:10"))){
			inputPattern.readMap("0900");
//			inputPattern2.readMap("0900"); 		// hardcode accident
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
		
		// hardcode accident
		if(time.after(parseTime("08:49")))
			inputPattern2.readMap("case1"); 
		
		return deadline;
	}
}
