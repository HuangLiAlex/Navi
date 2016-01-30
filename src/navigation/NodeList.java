package navigation;

import java.util.ArrayList;

public class NodeList {
	private static ArrayList<Nodes> nodeList =  new ArrayList<Nodes>();
	private NodeList(){}
	
	public static ArrayList<Nodes> getInstance(){
		return nodeList;
	}
}
