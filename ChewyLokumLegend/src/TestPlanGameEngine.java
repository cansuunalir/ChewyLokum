import Model.EmptyObject;
import Model.RegularLokum;


public class TestPlanGameEngine {
	
	public static GameEngine ge;
	
	public static void setUp(){
		ge = new GameEngine();
		//Game Engine is initialized
	    }
	
	//Test for "populateBoard()".
	public static void testOne(){
		setUp();
		ge.populateBoard();
		//populates board with random regular lokums.
		System.out.println(ge.getBoard().toString());
		
	}
	
	//Test for "generateLokum()".
	public static void testTwo(){
		setUp();
		RegularLokum lokum = ge.generateRegularLokum();
		//Creates a regular lokum with a random color.
		System.out.println(lokum.toString());
		//It should print the specifications of the lokum. (Column:0, Row:0, Type:1)
	}
	
	//Test for "fillEmptySpaces()".
	public static void testThree(){
		setUp();
		ge.populateBoard();
		//Board is created and populated.
		ge.getBoard().getBoardObjects()[5][5] = new EmptyObject(5,5);
		ge.getBoard().getBoardObjects()[4][4] = new EmptyObject(4,4);
		//Two lokums are converted to EmptyObjects.
		System.out.println(ge.getBoard().toString());
		
		ge.fillEmptySpaces();
		//After calling this method empty spaces should fill with lokums.
		System.out.println(ge.getBoard().toString());
		
	}
	
	//Test for "fillEmptySpaces()" having an empty column
	public static void testFour(){
		setUp();
		ge.populateBoard();
		//Board is created and populated.
		Crush c = new Crush();
		c.crushVertical(ge.getBoard(), 3);
		//A column is converted to emptyObjects.
		System.out.println(ge.getBoard().toString());
		
		ge.fillEmptySpaces();
		//After calling this method empty spaces should fill with lokums.
		System.out.println(ge.getBoard().toString());
		
	}
}
