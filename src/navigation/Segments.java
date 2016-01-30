package navigation;

public class Segments{
	private String node1;
	private	String node2;
	private float distance;
	private int speed;

	public Segments(String node1, String node2, float distance, int speed){
		this.node1 = node1;
		this.node2 = node2;
		this.distance = distance;
		this.speed = speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String getNode1() {
		return node1;
	}
	public String getNode2() {
		return node2;
	}
	
	public float getDistance() {
		return distance;
	}
	
	@Override
	public String toString(){
		return getNode1() + "->" + getNode2()+ ": " + getDistance() + ", " + getSpeed();
	}
	
}
