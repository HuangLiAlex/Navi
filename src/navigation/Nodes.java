package navigation;

public class Nodes {
	private String nodeName;
	private int distance;
	
	public String getNodes() {
		return nodeName;
	}
	public void setNodes(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString(){
		return getNodes() + " " + getDistance();
	}
}
