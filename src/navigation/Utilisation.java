package navigation;

import java.util.ArrayList;

public class Utilisation {
	
	public static void updateUtilisation(Segments s, int congestLvl){
		if(congestLvl == 4){
			s.setUtilisation(100);
		}else if(congestLvl == 3){
			s.setUtilisation(80);
		}else if(congestLvl == 2){
			s.setUtilisation(60);
		}else if(congestLvl == 1){
			s.setUtilisation(40);
		}
	}
	
	public static void updateCongest(Segments s, int utilisation){
		if(utilisation >= 100){
			s.setCongesLvl(4);
		}else if(utilisation >= 80){
			s.setCongesLvl(3);
		}else if(utilisation >= 50){
			s.setCongesLvl(2);
		}else{
			s.setCongesLvl(1);
		}
	}
	
	public static void showUtilisation(ArrayList<Integer> path){
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		int i=0;
		int j=1;
		
		while(j<path.size()){
			int fromNode = path.get(i);
			int toNode = path.get(j);
			
			for(Segments segment: adjList.get(fromNode)){
				if(segment.getNode2() == toNode){
					int utilisation = segment.getUtilisation();
					System.out.print("from: "+ fromNode + " to: " + toNode);
					System.out.print(" uti: " + utilisation);
					System.out.print(" congest: " + segment.getCongesLvl());
					System.out.print(" time: " + (int) segment.getCost());
					System.out.println(" speed: " + (segment.getSpeed()*60/1000) + " km/h");
					break;
				}
			}
			i++;
			j++;
		}
	}
	
	public static void divert(ArrayList<Integer> mainPath, ArrayList<Integer> divertPath, int rate){
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		int divertNode = 0;
		int newUtilisation;
		
		// find divert node
		while(mainPath.get(divertNode) != divertPath.get(divertNode)){
			divertNode++;
		}
//		System.out.println("divertNode: " + divertNode);
		// update main path
		int i = divertNode;
		int j = i + 1;
		while(j<mainPath.size()){
			int fromNode = mainPath.get(i);
			int toNode = mainPath.get(j);
			
			for(Segments segment: adjList.get(fromNode)){
				if(segment.getNode2() == toNode){
					if(segment.getType().equals("express")){
						newUtilisation = segment.getUtilisation() - rate;
					}else{
						newUtilisation = (int) (segment.getUtilisation() - rate * (30/25.0));
					}
					segment.setUtilisation(newUtilisation);
					updateCongest(segment, newUtilisation);
					break;
				}
			}
			i++;
			j++;
		}
		
		// update divert path
		i = divertNode;
		j = i + 1;
		while(j<divertPath.size()){
			int fromNode = divertPath.get(i);
			int toNode = divertPath.get(j);
			
			for(Segments segment: adjList.get(fromNode)){
				if(segment.getNode2() == toNode){
					if(segment.getType().equals("express")){
						newUtilisation = segment.getUtilisation() + rate;
					}else{
						newUtilisation = (int) (segment.getUtilisation() + rate * (30/25.0));
					}
					segment.setUtilisation(newUtilisation);
					updateCongest(segment, newUtilisation);
					break;
				}
			}
			i++;
			j++;
		}
	}
}
