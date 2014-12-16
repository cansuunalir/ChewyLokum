import java.util.ArrayList;

import Model.*;


public class HorizontalGroup extends GroupObject {
	
	public HorizontalGroup(){
		super();
		super.setType(0);
	}
	
	public ArrayList<GroupObject> checkHorizontalGroup(Board board){
		// Pre-condition: Two lokums that wanted to be swapped are adjacent.

		// @requires: board != null 
			       
		// Post-condition: Exit of the procedure
		// @ensures: If any horizontal group is formed, their information is returned.
		ArrayList<GroupObject> horizontalGroups = new ArrayList<GroupObject>();
		
		int j=0;
		int counter=1;
		int groupCounter=0;
		int groupCounter2=0;
		
		int look=0;
		int nulldegil = 0;
		int nullmis = 0;
		
		for(int i=0; i<board.getBoardObjects().length; i++){
			j=0;
			
			//groupCounter2=0;
			
			/*
			System.out.println("i: " + i);
			System.out.println("j: " + j);
			*/
			
			ArrayList<BoardObject> al = new ArrayList<BoardObject>();
			
			al.add(0,board.getBoardObjects()[0][0]); //Sonra set edebilmek icin basta anlamsiz birsey ekliyor
			
			while(j < board.getBoardObjects()[0].length){
				/*
				System.out.println("groupCounter2: " + groupCounter2);
				*/
				
				/*
				look++;
				System.out.println("while a girdi: " + look);
				*/
				
				BoardObject bo = board.getBoardObjects()[i][j];
				
				BoardObject newbo = new EmptyObject(0,0);
				BoardObject bo2 = newbo;
				
				if(j!=board.getBoardObjects()[0].length-1){
					bo2 = board.getBoardObjects()[i][j+1];
				}else{
					
					if(counter >= 3) {
						
						GroupObject go = new HorizontalGroup();
						go.setGroupObjects(al);
						
						/*
						System.out.println("Grup eklerken***");
						for(int a=0; a<al.size(); a++){
							System.out.println(a + ". eleman: " + al.get(a));
						}
						for(int b=0; b<go.getGroupObjects().size(); b++){
							System.out.println(b + ". group obje eleman: " + go.getGroupObjects().get(b));
						}
						*/
						
						horizontalGroups.add(groupCounter,go);
						groupCounter++;

					}
					
					counter=1;
					
				}
			
				/*
				System.out.println(horizontalGroups.size());
				for(int c=0; c<horizontalGroups.size(); c++){
					GroupObject gob = horizontalGroups.get(c);
					System.out.println(c + ". grup: " + gob);
					System.out.println("Kac eleman var: " + gob.getGroupObjects().size());
					for(int d=0; d<gob.getGroupObjects().size(); d++){
						System.out.println(d + ". icerdeki eleman: " +gob.getGroupObjects().get(d));
					}
				
				}
				*/
			
				
				if(bo.getColor() != null){
					/*
					nulldegil++;
					System.out.println("null degilmis: " + nulldegil);
					*/
				
					if(bo.getColor() != bo2.getColor()){
						
						
						if(counter >= 3) {
							
							GroupObject go = new HorizontalGroup();
							go.setGroupObjects(al);
							
							/*
							System.out.println("Grup eklerken***");
							for(int a=0; a<al.size(); a++){
								System.out.println(a + ". eleman: " + al.get(a));
							}
							for(int b=0; b<go.getGroupObjects().size(); b++){
								System.out.println(b + ". group obje eleman: " + go.getGroupObjects().get(b));
							}
							*/
							
							horizontalGroups.add(groupCounter,go);
							groupCounter++;
							//groupCounter2++;
							
						}

						counter = 1;
						j++;
						
						
						al = new ArrayList<BoardObject>();
						al.add(0,board.getBoardObjects()[0][0]);
						
						/*
						System.out.println(horizontalGroups.size());
						for(int c=0; c<horizontalGroups.size(); c++){
							GroupObject gob = horizontalGroups.get(c);
							System.out.println(c + ". grup: " + gob);
							System.out.println("Kac eleman var: " + gob.getGroupObjects().size());
							for(int d=0; d<gob.getGroupObjects().size(); d++){
								System.out.println(d + ". icerdeki eleman: " +gob.getGroupObjects().get(d));
							}
						
						}
						*/
						
						
					}else {
						al.set(counter-1,bo);
						
						/*
						for(int a=0; a<al.size(); a++){
							System.out.println(a + ". eleman: " + al.get(a));
						}
						*/
						
						counter++;
						
						/*
						System.out.println("counter arttý! " + counter);
						System.out.println("eklencek elaman: " + bo2);
						*/
						
						al.add(counter-1,bo2);
						
						/*
						for(int a=0; a<al.size(); a++){
							System.out.println(a + ". eleman: " + al.get(a));
						}
						*/
						
						j++;
					}
					
					
					
				} else {
					/*
					nullmis++;
					System.out.println("nullmis: " + nullmis);
					*/
					
					counter = 1;
					j++;
					
					/*
					System.out.println(horizontalGroups.size());
					for(int c=0; c<horizontalGroups.size(); c++){
						GroupObject gob = horizontalGroups.get(c);
						System.out.println(c + ". grup: " + gob);
						System.out.println("Kac eleman var: " + gob.getGroupObjects().size());
						for(int d=0; d<gob.getGroupObjects().size(); d++){
							System.out.println(d + ". icerdeki eleman: " +gob.getGroupObjects().get(d));
						}
					
					}
					*/
					
					
					
				}
				
				
			}
		
			
			
		}
			
		
		return horizontalGroups;
	}

}