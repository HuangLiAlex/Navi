package navigation;

import patternFactory.*;

public class PatternFactory {
   public InputPattern getPattern(String patternType){
      if(patternType == null){
    	  return null;
      }		
      
      if(patternType.equalsIgnoreCase("congestionByTime")){
    	  return new congestionByTime();
      }
      
      return null;
   }
}