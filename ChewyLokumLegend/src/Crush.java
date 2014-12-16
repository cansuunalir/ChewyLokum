import Model.*;

public class Crush {
	
	public Crush(){
		super();
	}
	
	public Board crushVertical(Board board, int column){
	 
	//Pre-condition:Call to procedure from Game Engine 
	//				Two lokums must have been swapped in vertical direction
	//
	//@requires: column < board[0].length
	//			 
	//Post-condition: The given column of the board is updated.
	//				  board[i][column]=0;	
	//				  
	//@ensures: Each index in given column is set to null.
	//		
	//				
	//@modifies: this.board is modified 
		
	//	for(int i=0; i<board.length(); i++){
	//		board[i][column]=0;
	//	}
		
		for(int i=0; i<board.getBoardObjects().length; i++){
			EmptyObject empty = new EmptyObject(i,column);
			board.putObject(empty);
		}
		
		return board;				
		
	}
	
	public Board crushHorizontal(Board board, int row){
		//Pre-condition: Two lokums must have been swapped in horizontal direction
		//
		//@requires: column < board.length
		//			 
		//Post-condition: The given row of the board is updated.
		//				  
		//@ensures: Each index in given row is set to null.
		//				
		//@modifies: this.board is modified 
		//
		//
		//	for(int i=0; i<board[0].length(); i++){
		//		board[row][i]=0;
		//	}
		
		for(int i=0; i<board.getBoardObjects()[0].length; i++){
			EmptyObject empty = new EmptyObject(row,i);
			board.putObject(empty);
		}
		
		return board;
		
	}
	
	public Board crushColor(Board board, int color){
		//Pre-condition: Colorbomb lokum must have been swapped 
		//
		//@requires: color = lokum2.color()
		//			 
		//			 
		//Post-condition: Each lokum object on board with color=lokum2.color() is 
		//				  converted into null
		//				  
		//@ensures: Only objects with given color are crashed
		//				
		//@modifies: this.board is modified 
		
		for(int i=0; i<board.getBoardObjects().length; i++){
			for(int j=0; j<board.getBoardObjects()[0].length; j++){
				if(board.getBoardObjects()[i][j].getColor() != null){
					if(board.getBoardObjects()[i][j].getColor() == color) {
						EmptyObject empty = new EmptyObject(i,j);
						board.putObject(empty);
					}
				}
			}
		}
		
		return board;
		
	}
	
	public Board crushArea(Board board, int row, int column){
		//Pre-condition: Wrapped lokum must have been swapped 
		//
		//@requires: row = board.getObject().getRow && 
		//			 column = board.getObject().getColumn
		//			 
		//Post-condition: Area around to wrapped between [row-1] and [row+1]
		//				  && [column-1] and [column+1] are set to 0.
		//				  
		//@ensures: Only the area around wrapped is set to 0.
		//				
		//@modifies: this.board is modified 
		
		for(int i=row-1; i<=row+1; i++){
			for(int j=column-1; j<=column+1; j++){
				if(i>=0 && j>=0 && i<board.getBoardObjects().length 
						&& j<board.getBoardObjects()[0].length){
				EmptyObject empty = new EmptyObject(i,j);
				board.putObject(empty);
				}
			}
		}
		
		return board;
		
	}
	
	public Board crushGroup(Board board, GroupObject group){
		
		for(int i=0; i<group.getGroupObjects().size(); i++){
			BoardObject bo = group.getGroupObjects().get(i);
//			int r= bo.getRow();
//			int c = bo.getColumn();
//			bo = null;
			EmptyObject empty = new EmptyObject(bo.getRow(),bo.getColumn());
			board.putObject(empty);
		}
		
		return board;
		
	}
	
}
