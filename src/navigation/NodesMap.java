package navigation;

import java.util.HashMap;

public class NodesMap {
	private static HashMap<String, Integer> nodesMap = new HashMap<String, Integer>();;
	private NodesMap(){}
	
	public static HashMap<String, Integer> getInstance(){
		return nodesMap;
	}
}
