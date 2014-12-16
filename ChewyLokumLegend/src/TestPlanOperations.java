import Model.EmptyObject;
import Model.GreenPistachioLokum;
import Model.RedRoseLokum;


public class TestPlanOperations {
	
	public static GameEngine ge;
	public static Swap swap;
	public static HorizontalGroup horizontalGroup;
	public static VerticalGroup verticalGroup;
	
	public static void setUp(){
		ge = new GameEngine();
		swap = new Swap();
		horizontalGroup = new HorizontalGroup();
		verticalGroup = new VerticalGroup();
		//Game Engine is initialized.
		//Operations are initialized.
			for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
				for(int j=0; j<ge.getBoard().getBoardObjects()[0].length; j++){
					ge.getBoard().putObject(new EmptyObject(i,j));
				}
			}
		//Board is populated with empty objects.
	    }
	
	public static void testOne(){
		setUp();
		RedRoseLokum rl = new RedRoseLokum(0,0); 
		RedRoseLokum rl2 = new RedRoseLokum(0,1);
		RedRoseLokum rl3 = new RedRoseLokum(0,2);
		ge.getBoard().putObject(rl);
		ge.getBoard().putObject(rl2);
		ge.getBoard().putObject(rl3);
		//Three lokums are added horizontally.
		horizontalGroup.checkHorizontalGroup(ge.getBoard());
		//Horizontal group is detected and the information is returned
	}
	
	public static void testTwo(){
		setUp();
		GreenPistachioLokum pl = new GreenPistachioLokum(1,0);
		GreenPistachioLokum pl2 = new GreenPistachioLokum(2,0);
		GreenPistachioLokum pl3 = new GreenPistachioLokum(3,0);
		ge.getBoard().putObject(pl);
		ge.getBoard().putObject(pl2);
		ge.getBoard().putObject(pl3);
		//Three lokums are added vertically.
		verticalGroup.checkVerticalGroup(ge.getBoard());
		//Vertical group is detected and the information is returned.
	}
	
	public static void testThree(){
		setUp();
		GreenPistachioLokum pl = new GreenPistachioLokum(1,0);
		GreenPistachioLokum pl2 = new GreenPistachioLokum(2,0);
		GreenPistachioLokum pl3 = new GreenPistachioLokum(3,0);
		GreenPistachioLokum pl4 = new GreenPistachioLokum(2,1);
		GreenPistachioLokum pl5 = new GreenPistachioLokum(2,2);
		ge.getBoard().putObject(pl);
		ge.getBoard().putObject(pl2);
		ge.getBoard().putObject(pl3);
		ge.getBoard().putObject(pl4);
		ge.getBoard().putObject(pl5);
		//Five lokums are added in "T" shape.
		verticalGroup.checkVerticalGroup(ge.getBoard());
		//"T" shape group is detected and the information is returned.
	}
	

}
