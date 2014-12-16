/**
 * 
 */

import java.util.ArrayList;

import Model.*;

public class Main {

	/**
	 * @param args
	 */
	
	public static GameEngine ge;
	public static Swap swap;
	public static HorizontalGroup horizontalGroup;
	public static TGroup tGroup;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationWindow a = new ApplicationWindow();
		a.setVisible(true);
		a.pack();
		
//		tGroup = new TGroup();
//		ge = new GameEngine();
//		swap = new Swap();
//		HorizontalGroup horizontalGroup = new HorizontalGroup();
//		VerticalGroup verticalGroup = new VerticalGroup();
		//Game Engine is initialized.
		//Operations are initialized.
//			for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
//				for(int j=0; j<ge.getBoard().getBoardObjects()[0].length; j++){
//					ge.getBoard().putObject(new EmptyObject(i,j));
//				}
//			}
			//System.out.println(ge.getBoard().toString());
			GreenPistachioLokum pl = new GreenPistachioLokum(0,0);
			GreenPistachioLokum pl2 = new GreenPistachioLokum(0,1);
			GreenPistachioLokum pl3 = new GreenPistachioLokum(0,2);
			GreenPistachioLokum pl4 = new GreenPistachioLokum(1,1);
			GreenPistachioLokum pl5 = new GreenPistachioLokum(2,1);
//			GreenPistachioLokum pl6 = new GreenPistachioLokum(1,4);
//			GreenPistachioLokum pl7 = new GreenPistachioLokum(1,5);
//			GreenPistachioLokum pl8 = new GreenPistachioLokum(4,1);
//			GreenPistachioLokum pl9 = new GreenPistachioLokum(4,2);
//			GreenPistachioLokum pl10 = new GreenPistachioLokum(2,2);
//			GreenPistachioLokum pl11 = new GreenPistachioLokum(4,0);
//			GreenPistachioLokum pl12 = new GreenPistachioLokum(5,2);
//			GreenPistachioLokum pl13 = new GreenPistachioLokum(6,2);
//			GreenPistachioLokum pl14 = new GreenPistachioLokum(8,3);
//			GreenPistachioLokum pl15 = new GreenPistachioLokum(8,4);
//			GreenPistachioLokum pl16 = new GreenPistachioLokum(8,5);
			
//			ge.getBoard().putObject(pl);
//			ge.getBoard().putObject(pl2);
//			ge.getBoard().putObject(pl3);
//			ge.getBoard().putObject(pl4);
//			ge.getBoard().putObject(pl5);
//			ge.getBoard().putObject(pl6);
//			ge.getBoard().putObject(pl7);
//			ge.getBoard().putObject(pl8);
//			ge.getBoard().putObject(pl9);
//			ge.getBoard().putObject(pl10);
//			ge.getBoard().putObject(pl11);
//			ge.getBoard().putObject(pl12);
//			ge.getBoard().putObject(pl13);
//			ge.getBoard().putObject(pl14);
//			ge.getBoard().putObject(pl15);
//			ge.getBoard().putObject(pl16);


//			System.out.println(ge.getBoard().toString());
//
//			ArrayList<GroupObject> tGroups = tGroup.checkTGroup(ge.getBoard());
//			
//			System.out.println("kjasda" + tGroups.size());

//			ArrayList<GroupObject> verticalGroups = verticalGroup.checkVerticalGroup(ge.getBoard());
//			System.out.println("kj" + verticalGroups.size());
//			for(int i = 0; i< verticalGroups.size(); i++){
//				System.out.println("ASDASDA"+verticalGroups.get(i).getGroupObjects().size());
//				for(int j=0; j<verticalGroups.get(i).getGroupObjects().size(); j++){
//					System.out.println(verticalGroups.get(i).getGroupObjects().get(j).getRow());
//				}
//			}
//			Crush c = new Crush();
//			System.out.println(c.crushGroup(ge.getBoard(), verticalGroups.get(0) ).toString()); */
	}

}
