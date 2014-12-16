import java.util.ArrayList;
import Model.*;
public class VerticalGroup extends GroupObject {
	
	public VerticalGroup(){
		super();
		super.setType(1);
	}
	
/*	public boolean isElement(BoardObject object){
		BoardObject a;
		for(int i = 0 ; i<this.getGroupObjects().size(); i++){
			a= this.getGroupObjects().get(i);
			if(a.equals(object))
				return true;
		}
		return false;
		
	}*/
	
	public ArrayList<GroupObject> checkVerticalGroup(Board board){
		// Pre-condition: Two lokums that wanted to be swapped are adjacent.
		// @requires: board != null 
			       
		// Post-condition: Exit of the procedure
		// @ensures: If any vertical group is formed, their information is returned.
		ArrayList<GroupObject> verticalGroups = new ArrayList<GroupObject>();
		
		int i=0;
		int counter=1;
		
		for(int j=0; j<board.getBoardObjects()[0].length; j++){
			i=1;
			
		
			ArrayList<BoardObject> al = new ArrayList<BoardObject>();
			
			while(i < board.getBoardObjects().length){
				BoardObject bo = board.getBoardObjects()[i-1][j];
				al.add(bo);
				//counter++;
				
				if(bo.getColor() != null){
				
					
					if(bo.getColor() != board.getBoardObjects()[i][j].getColor()){
						
						
						if(counter >= 3) {
							GroupObject go = new VerticalGroup();
							go.setGroupObjects(al);
							verticalGroups.add(go);
							
							
						}
						
						al = new ArrayList<BoardObject>();
						
						counter = 1;
						i++;
						
					}else {
						
						counter++;
					
						i++;
						
					}
					
					
					
				} else {
					
					if(counter >= 3) {
						GroupObject go = new VerticalGroup();
						go.setGroupObjects(al);
						verticalGroups.add(go);
					}
					
					al = new ArrayList<BoardObject>();
										
					counter = 1;
					i++;
				//}
				}
				
			}
			
			if(board.getBoardObjects()[i-2][j].getColor() == board.getBoardObjects()[i-1][j].getColor() 
					&& i==board.getBoardObjects().length){
			al.add(board.getBoardObjects()[i-1][j]);
			//counter++;
			}
			if(counter >= 3) {
				GroupObject go = new VerticalGroup();
				go.setGroupObjects(al);
				verticalGroups.add(go);
				
				
			}
			
			al = new ArrayList<BoardObject>();
			
			counter = 1;
			
		}
			
		
		return verticalGroups;
	}
	
	
	
}