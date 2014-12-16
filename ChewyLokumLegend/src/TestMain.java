import java.util.ArrayList;

import Model.EmptyObject;
import Model.GreenPistachioLokum;


public class TestMain {
	
	public static GameEngine ge;
	public static Swap swap;
	public static HorizontalGroup horizontalGroup;

	public static void main(String[] args) {
		
		
		
			ge = new GameEngine();
			swap = new Swap();
			HorizontalGroup horizontalGroup = new HorizontalGroup();
			VerticalGroup verticalGroup = new VerticalGroup();
			//Game Engine is initialized.
			//Operations are initialized.
				for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
					for(int j=0; j<ge.getBoard().getBoardObjects()[0].length; j++){
						ge.getBoard().putObject(new EmptyObject(i,j));
					}
				}
				//System.out.println(ge.getBoard().toString());
				GreenPistachioLokum pl = new GreenPistachioLokum(0,1);
				GreenPistachioLokum pl2 = new GreenPistachioLokum(0,0);
				GreenPistachioLokum pl3 = new GreenPistachioLokum(0,2);
				GreenPistachioLokum pl4 = new GreenPistachioLokum(0,3);
				GreenPistachioLokum pl5 = new GreenPistachioLokum(1,4);
				GreenPistachioLokum pl6 = new GreenPistachioLokum(1,2);
				GreenPistachioLokum pl7 = new GreenPistachioLokum(1,3);
				ge.getBoard().putObject(pl);
				ge.getBoard().putObject(pl2);
				ge.getBoard().putObject(pl3);
				ge.getBoard().putObject(pl4);
				ge.getBoard().putObject(pl5);
				ge.getBoard().putObject(pl6);
				ge.getBoard().putObject(pl7);

				System.out.println(ge.getBoard().toString());
				ArrayList<GroupObject> horizontalGroups = horizontalGroup.checkHorizontalGroup(ge.getBoard());
				
				System.out.println("kj" + horizontalGroups.size());

	}

}
