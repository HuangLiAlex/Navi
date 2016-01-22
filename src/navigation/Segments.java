package navigation;

public class Segments implements Comparable<Segments>{
	private String node1;
	private	String node2;
	private int distance;
	
	public Segments(String node1, String node2, int distance){
		this.node1 = node1;
		this.node2 = node2;
		this.distance = distance;
	}
	
	public String getNode1() {
		return node1;
	}
	public String getNode2() {
		return node2;
	}
	
	public int getDistance() {
		return distance;
	}

	@Override
	public int compareTo(Segments s) {
		if(s.getDistance() > this.distance){
			return -1;
		}
		return 1;
	}
	
	@Override
	public String toString(){
		return getNode1() + "->" + getNode2()+ ": "+ getDistance();
	}
	
}
