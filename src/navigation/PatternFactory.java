package navigation;

import patternFactory.*;

public class PatternFactory {
   public InputPattern getPattern(String patternType){
      if(patternType == null){
    	  return null;
      }		
      
      if(patternType.equalsIgnoreCase("CongestionByTime")){
    	  return new CongestionByTime();
      }
      
      if(patternType.equalsIgnoreCase("CongestionByIncident")){
    	  return new CongestionByIncident();
      }
      
      return null;
   }
}