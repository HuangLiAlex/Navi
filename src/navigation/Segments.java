package navigation;

public class Segments{
	private int node1;
	private	int node2;
	private float distance;
	private float cost;
	private float speed;
	private int congesLvl;
	private String type;
	private int utilisation;
	
	public Segments(int node1, int node2, float distance, String type){
		this.distance = distance;
		this.node1 = node1;
		this.node2 = node2;
		this.type  = type;
		this.congesLvl = 1;
		this.cost = 1;
		this.utilisation = 40;
		if(type.equals("express")){
			this.setSpeed(80 * 1000 / 60);		// 80km/h
		}else{
			this.setSpeed(40 * 1000 / 60);		// 40km/h
		}
	}

	public int getCongesLvl() {
		return congesLvl;
	}

	public float getDistance() {
		return distance;
	}

	public int getNode1() {
		return node1;
	}
	public int getNode2() {
		return node2;
	}

	public String getType() {
		return type;
	}

	public float getCost() {
		return cost;
	}

	public void setCongesLvl(int congesLvl) {
		this.congesLvl = congesLvl;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUtilisation() {
		return utilisation;
	}

	public void setUtilisation(int utilisation) {
		this.utilisation = utilisation;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
		public String toString(){
	//		return getNode1() + "->" + getNode2()+ ": " + getDistance() + ", " + getSpeed() + ", " + this.congesLvl;
			return getNode1() + "->" + getNode2()+ ": " + this.congesLvl;
		}
	
}
