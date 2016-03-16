package navigation;

public class Nodes {
	private int nodeName;
	private float cost;
	
	public int getNodes() {
		return nodeName;
	}
	public void setNodes(int nodeName) {
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
