package navigation;

public class Nodes {
	private String nodeName;
	private float cost;
	
	public String getNodes() {
		return nodeName;
	}
	public void setNodes(String nodeName) {
		this.nodeName = nodeName;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float value) {
		this.cost = value;
	}

	@Override
	public String toString(){
		return getNodes() + " " + getCost();
	}
}
