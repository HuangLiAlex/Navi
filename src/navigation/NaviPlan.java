package navigation;

import java.util.ArrayList;
import java.util.Date;

public class NaviPlan implements Comparable<NaviPlan>{
	private ArrayList<Integer> path = new ArrayList<Integer>();
	private Date departureTime = new Date();
	private float cost;
	private int score;

	public NaviPlan(float cost, Date departureTime, ArrayList<Integer> path){
		this.departureTime.setTime(departureTime.getTime());
		this.path = path;
		this.cost = cost;		
	}
	
	public ArrayList<Integer> getPath() {
		return path;
	}
	
	public Date getDepartureTime() {
		return departureTime;
	}
	
	public float getCost() {
		return cost;
	}

	@Override
	public int compareTo(NaviPlan plan) {
		return new Double(this.getCost()).compareTo(new Double(plan.getCost()));
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
