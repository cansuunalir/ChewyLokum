import Model.BoardObject;


public class Swap {
	
	public Swap(){
		
	}
	
	public boolean areAdjacent(BoardObject object1, BoardObject object2){
		// Pre-condition: Entry to the procedure with two selected BoardObjects.

		// @requires: object1 != null && object2 != null 
	        //        Both BoardObjects are lokums and not obstacles.   
	        //            

		// Post-condition: Exit of the procedure
		// @ensures: object1, object2 do not change! Returns true if lokums are adjacent.
		                  
		// What may it modify?
	        // @modifies: result boolean
		if( (Math.abs(object1.getRow() - object2.getRow())) <= 1
				&& (Math.abs(object1.getColumn() - object2.getColumn())) <= 1){
			return true;
		}
			return false;
	}

}
