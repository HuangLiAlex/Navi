package navigation;

import java.util.ArrayList;

public class AdjList {
	private static ArrayList<ArrayList<Segments>> adjList = new ArrayList<ArrayList<Segments>>();
	private AdjList(){}
	
	public static ArrayList<ArrayList<Segments>> getInstance(){
		return adjList;
	}
}
