import java.util.ArrayList;

import Model.*;

public class TGroup extends GroupObject{
	
	public TGroup(){
		super();
		super.setType(2);
	}
	
	public ArrayList<GroupObject> checkTGroup(Board board){
		// Pre-condition: Two lokums that wanted to be swapped are adjacent.

		// @requires: board != null 
			       
		// Post-condition: Exit of the procedure
		// @ensures: if any Tgroup is formed, they are detected.
		ArrayList<GroupObject> TGroups = new ArrayList<GroupObject>();
		
		HorizontalGroup horizontalGroup = new HorizontalGroup();
		VerticalGroup verticalGroup = new VerticalGroup();
		
		ArrayList<GroupObject> verticalGroups = verticalGroup.checkVerticalGroup(board);
		ArrayList<GroupObject> horizontalGroups = horizontalGroup.checkHorizontalGroup(board);
		
		int groupCounter = 0;
		
		int i=0;
		
		if(verticalGroups.size()!=0 && horizontalGroups.size()!=0){
			
			for(int j=0; j<horizontalGroups.size(); j++){
				
				GroupObject go2 = horizontalGroups.get(j);
				/*
				System.out.println("horizontal group:");
				for(int a=0; a<go2.getGroupObjects().size(); a++){
					System.out.println(go2.getGroupObjects().get(a));
				}
				*/
				
				ArrayList<BoardObject> al = new ArrayList<BoardObject>();
				
				i=0;
			
				while(i<verticalGroups.size()){
					
					GroupObject go = verticalGroups.get(i);
					/*
					System.out.println("vertical group:");
					for(int a=0; a<go.getGroupObjects().size(); a++){
						
						System.out.println(go.getGroupObjects().get(a));
					}
					*/
					
					if(go2.getGroupObjects().removeAll(go.getGroupObjects())){
						
						/*
						System.out.println("T shape detected");
						*/
						
						for(int a=0; a<go.getGroupObjects().size(); a++){
							al.add(a,go.getGroupObjects().get(a));
						}
						
						for(int a=go.getGroupObjects().size(); a<(go.getGroupObjects().size()+go2.getGroupObjects().size()); a++){
							al.add(a,go2.getGroupObjects().get(a-go.getGroupObjects().size()));
						}
						
						/*
						System.out.println("Tgroups");
						for(int a=0; a<TGroups.size(); a++){
							System.out.println(a + ". grup");
							GroupObject group = TGroups.get(a);
							for(int b=0; b<group.getGroupObjects().size(); b++){
								System.out.println(b + ". eleman: " + group.getGroupObjects().get(b));
							}
						}
						*/
						
						/*
						System.out.println("T shape alsi");
						for(int a=0; a<al.size(); a++){
							System.out.println(a + ". elaman: " + al.get(a));
						}
						
						System.out.println("gcounter: " + groupCounter);
						*/
						
						GroupObject grOb = new TGroup();
						grOb.setGroupObjects(al);
						TGroups.add(grOb);
						
						groupCounter++;
						
					}//if(go.getGroupObjects().removeAll(go2.getGroupObjects()))
					
					i++;
					
				}//while(i<verticalGroups.size())
				
			}//for(int j=0; j<horizontalGroups.size(); j++)
			
			
		}//if(verticalGroups.size()!=0 && horizontalGroups.size()!=0)
				
		
		
		return TGroups;
	}

}
