import Model.RegularLokum;


public class TestPlanCrush {

	private static GameEngine ge;
	public static Crush crush;
	
	public static void setUp(){
		//Initialize Board and Crush for test cases.
		ge = new GameEngine();
		ge.populateBoard();
		crush = new Crush();
		
	}
	
	//Test for "crushVertical(int column)".
	public static void testVertical(){
		setUp();
		int column;
		//can be iterated between 0 and board[0].length()
		column=0;
		crush.crushVertical(ge.getBoard(),column);
		//Board[*][column] should be equal to 0
		System.out.println(ge.getBoard().toString());
		
		//For the given column it should print 6's for each row.
	}
	
	//Test for "crushHorizontal(int row)".
	public static void testHorizontal(){
		setUp();
		int row;
		//can be iterated between 0 and board.length()
		row=1;
		crush.crushHorizontal(ge.getBoard(),row);
		//Board[row][*] should be equal to 0
		System.out.println(ge.getBoard().toString());
		
		//For the given row it should print 6's for each column.
	}
	
	//Test for "crushColor(Color color)".
	public static void testColor(){
		setUp();
		int color;
		//Color can be tested for any color of the lokums
		color= 3;
		crush.crushColor(ge.getBoard(),color);
		//Checks each object if any lokum with given color is present.
		for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
			for (int j=0; j<ge.getBoard().getBoardObjects().length; j++){
				//If a lokum is present with given color, signal error
				if((((RegularLokum) ge.getBoard().getObject(i,j)).getColor()) == 3 ){
					System.out.println("Lokums are not crushed correctly");
					break;
				}
				else 
					//If no lokum is present with given color then crush is successful
					System.out.println("Crush completed");
			}
		
		}
	}
	
	//Test for "crushArea(int row, int column)".
	public static void testArea(){
		setUp();
		int row;
		int column;
		//Let us initialize for any applicable integers
		row=4;
		column=4;
		//The area around these row and columns on board are set to 6.
		crush.crushArea(ge.getBoard(), row, column);
		System.out.println(ge.getBoard().toString());
		//Check if the given area is set to 6 or not.
		
		
	}
	
	//Test for crushArea at the corner.
	public static void testArea2(){
		setUp();
		int row;
		int column;
		//Let us initialize for any applicable integers
		row=0;
		column=0;
		//The area around these row and columns on board are set to 6.
		crush.crushArea(ge.getBoard(), row, column);
		System.out.println(ge.getBoard().toString());
		//Check if the given area is set to 6 or not.
		
		
	}
	
}
