import Model.BoardObject;


public class Board {
	
	private BoardObject[][] board;
	
	public Board(int rows, int columns){
		board = new BoardObject[rows][columns];
	}

	public BoardObject[][] getBoardObjects() {
		return board;
	}

	public void setBoard(BoardObject[][] board) {
		this.board = board;
	}
	
	public void putObject(BoardObject object){
		board[object.getRow()][object.getColumn()] = null;
		board[object.getRow()][object.getColumn()] = object;
	}
	
	public BoardObject getObject(int row, int column){
		return board[row][column];
	}
	
	public void setBoardObject(BoardObject object, int row, int column){
		board[row][column]=null;
		board[row][column] = object;
	}
	
	public String toString(){
		String result = "";
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[0].length; j++){
				result = result + " " + Integer.toString(board[i][j].getColor());
			}
			result = result + "\n";
		}
		return result;
	}

}
