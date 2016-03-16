package navigation;

import java.util.ArrayList;

public class SegmentList {
	private static ArrayList<Segments> segmentList =  new ArrayList<Segments>();
	private SegmentList(){}
	
	public static ArrayList<Segments> getInstance(){
		return segmentList;
	}
}
