import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.BoardObject;
import Model.BrownHazelnutLokum;
import Model.ColorBombLokum;
import Model.EmptyObject;
import Model.GreenPistachioLokum;
import Model.RedRoseLokum;
import Model.RegularLokum;
import Model.SpecialLokum;
import Model.StripedLokum;
import Model.WhiteCoconutLokum;
import Model.WrappedLokum;


public class GameEngine {
	
	private Integer id = 1;
	private String name = "Arda";
	private Integer score=0;
	private Integer goalScore=350000;
	private Integer level=1;
	private Integer movesLeft=50;

	private Board board;
	Random random = new Random();
	private Swap swap = new Swap();
	public Crush crush = new Crush();
	public VerticalGroup vg = new VerticalGroup();
	public HorizontalGroup hg = new HorizontalGroup();
	private TGroup tg = new TGroup();
	
	public GameEngine(){
		this.board = new Board(9,6);
		populateBoard();
		while(vg.checkVerticalGroup(board).size() !=0 || hg.checkHorizontalGroup(board).size() !=0){
			if(hg.checkHorizontalGroup(board).size() !=0){
			a();
			fillEmptySpaces();
			}
			if(vg.checkVerticalGroup(board).size() !=0){
			b();
			fillEmptySpaces();
			}
			score = 0;
		}
	}
	
	
	public boolean canSwap(BoardObject object1, BoardObject object2){
		boolean result = false;
		
		HorizontalGroup horizontalGroup = new HorizontalGroup();
		VerticalGroup verticalGroup = new VerticalGroup();
		
		if(swap.areAdjacent(object1, object2)){
			
			System.out.println("adjacentmis");
			
			int row1 = object1.getRow();
			int col1 = object1.getColumn();
			int row2 = object2.getRow();
			int col2 = object2.getColumn();
			
				object1.setRow(row2);
				object1.setColumn(col2);
				object2.setRow(row1);
				object2.setColumn(col1);
				board.putObject(object1);
				board.putObject(object2);
	
				ArrayList<GroupObject> verticalGroups = verticalGroup.checkVerticalGroup(board);
				ArrayList<GroupObject> horizontalGroups = horizontalGroup.checkHorizontalGroup(board);
			
				if(verticalGroups.size() != 0 || horizontalGroups.size() != 0 ){
	
					for(int i=0; i < verticalGroups.size(); i++) {
						GroupObject group = verticalGroups.get(i);
						for(int j=0; j<group.getGroupObjects().size(); j++){
							if(object1 == group.getGroupObjects().get(j) ||
									object2 == group.getGroupObjects().get(j)){
								result = true;
							}	
						}
					}
					
					for(int i=0; i < horizontalGroups.size(); i++) {
						GroupObject group = horizontalGroups.get(i);
						for(int j=0; j<group.getGroupObjects().size(); j++){
							if(object1 == group.getGroupObjects().get(j) ||
									object2 == group.getGroupObjects().get(j)){
								result = true;
							}	
						}
					}					
				
				}
				
		}
		
		
		return result;
	}

	
	
	public void handleSelections(BoardObject object1, BoardObject object2){
		if(swap.areAdjacent(object1, object2)){
			
			if(!canSwap(object1,object2)){
				int row1 = object2.getRow();
				int col1 = object2.getColumn();
				int row2 = object1.getRow();
				int col2 = object1.getColumn();
				
				object1.setRow(row1);
				object1.setColumn(col1);
				object2.setRow(row2);
				object2.setColumn(col2);
				board.putObject(object1);
				board.putObject(object2);
			}else{
				movesLeft = movesLeft - 1;
			}
		}

		if(object1.getType() == 2 && object2.getType() == 2){
			
			if(((SpecialLokum)object1).getSpecialType() ==2){
				if(((SpecialLokum)object2).getSpecialType() == 2){
				for(int i = 0 ; i< this.board.getBoardObjects().length ; i++){
					for(int j = 0 ; j < this.board.getBoardObjects()[0].length; j++){
						this.board.setBoardObject(null, i, j);
						score += 100;
					}
				}
				populateBoard();
				}
				else if(((SpecialLokum)object2).getSpecialType() ==0){
					
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == object2.getColor()){
								Random r = new Random(); 
								board.putObject(new StripedLokum(i,j,object2.getColor(),r.nextBoolean()));
								if(((StripedLokum)object2).isOrientation()){
									crush.crushHorizontal(board, i);
									score += board.getBoardObjects()[0].length * 60;
								}else 
								crush.crushVertical(board, j);
									score += board.getBoardObjects().length * 60;
								
							}
						}
					}
				
					
				}else if(((SpecialLokum)object2).getSpecialType() ==1){
					int numColors=0;
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == object1.getColor()){
									numColors++;
							}
						}
						}
					score += numColors * numColors * 60;
					
					crush.crushColor(board, object1.getColor());
					Random r = new Random(4);
					int randomColor = r.nextInt();
					
					int numColors2=0;
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == randomColor){
									numColors2++;
							}
						}
						}
					score += numColors2 * numColors2 * 60;
					
					crush.crushColor(board,randomColor );
					
				}
			}
						
			
			if(((SpecialLokum)object1).getSpecialType() == 0){
				
				if(((SpecialLokum)object2).getSpecialType() == 0){
					
						crush.crushHorizontal(board, object1.getRow());
						score += board.getBoardObjects()[0].length * 60;
				
						crush.crushVertical(board, object2.getColumn());
						score += board.getBoardObjects().length * 60;	
					
				}else if(((SpecialLokum)object2).getSpecialType() == 1){
					if(((StripedLokum)object1).isOrientation()){
		
						crush.crushHorizontal(board, object1.getRow());
						score += board.getBoardObjects()[0].length * 60;
							if(object1.getRow()-1 >=0 )
						crush.crushHorizontal(board, object1.getRow()-1);
							score += board.getBoardObjects()[0].length * 60;
						if(object1.getRow()+1 <= this.board.getBoardObjects().length-1)
							crush.crushHorizontal(board, object1.getRow()+1);
						score += board.getBoardObjects()[0].length * 60;
						
					}else 
						crush.crushVertical(board, object1.getColumn());
					score += board.getBoardObjects().length * 60;
						if(object1.getColumn()-1 >=0 )
							crush.crushVertical(board, object1.getColumn()-1);
						score += board.getBoardObjects().length * 60;
						if(object1.getColumn()+1 <= this.board.getBoardObjects()[0].length-1)
							crush.crushVertical(board, object1.getColumn()+1);
						score += board.getBoardObjects().length * 60;
					
	
				} else if(((SpecialLokum)object2).getSpecialType() == 2){
					
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == object1.getColor()){
								Random r = new Random(); 
								board.putObject(new StripedLokum(i,j,object1.getColor(),r.nextBoolean()));
								if(((StripedLokum)object1).isOrientation()){
									crush.crushHorizontal(board, i);
									score += board.getBoardObjects()[0].length * 60;
								}else 
								crush.crushVertical(board, j);
								score += board.getBoardObjects().length * 60;
								
							}
						}
					}
				} 
			
		}
			else if(((SpecialLokum)object1).getSpecialType() == 1){
				if(((SpecialLokum)object2).getSpecialType() == 0){
					
					if(((StripedLokum)object2).isOrientation()){
						crush.crushHorizontal(board, object2.getRow());
						score += board.getBoardObjects()[0].length * 60;
						if(object2.getRow()-1 >=0 )
								crush.crushHorizontal(board, object2.getRow()-1);
						score += board.getBoardObjects()[0].length * 60;
						if(object2.getRow()+1 <= this.board.getBoardObjects().length-1)
							crush.crushHorizontal(board, object2.getRow()+1);
						score += board.getBoardObjects()[0].length * 60;
						}else 
						crush.crushVertical(board, object2.getColumn());
						score += board.getBoardObjects().length * 60;
						if(object2.getColumn()-1 >=0 )
							crush.crushVertical(board, object2.getColumn()-1);
						score += board.getBoardObjects().length * 60;
						if(object2.getColumn()+1 <= this.board.getBoardObjects()[0].length-1)
							crush.crushVertical(board, object2.getColumn()+1);
						score += board.getBoardObjects().length * 60;
					
				}else if(((SpecialLokum)object2).getSpecialType() == 1){
					for(int i=object1.getRow()-1; i<=object1.getRow()+2; i++){
						for(int j=object1.getColumn()-1; j<=object1.getColumn()+2; j++){
							if(i>=0 && j>=0 && i<board.getBoardObjects().length 
									&& j<board.getBoardObjects()[0].length){
							EmptyObject empty = new EmptyObject(i,j);
							board.putObject(empty);
							}
						}
					}
					score += 3600;
				}else if(((SpecialLokum)object2).getSpecialType() == 2){
					
					int numColors=0;
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == object1.getColor()){
									numColors++;
							}
						}
						}
					score += numColors * numColors * 60;
					
					crush.crushColor(board, object1.getColor());
					Random r = new Random(4);
					int randomColor = r.nextInt();
					
					int numColors2=0;
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == randomColor){
									numColors2++;
							}
						}
						}
					score += numColors2 * numColors2 * 60;
					
					crush.crushColor(board,randomColor );
					
					
					
				}
			} 
	
			
		
		}
	
		else if(object1.getType() == 2){
			if(((SpecialLokum)object1).getSpecialType() == 2){
				int numColors=0;
				for(int i=0; i<board.getBoardObjects().length; i++){
					for(int j=0; j<board.getBoardObjects()[0].length; j++){
						if(board.getObject(i, j).getColor() == object2.getColor()){
								numColors++;
						}
					}
					}
				
				crush.crushColor(board, ((RegularLokum)object2).getColor());
				BoardObject empty;
				empty = new EmptyObject(object1.getRow(), object1.getColumn());
				this.board.putObject(empty);
				score += numColors * numColors * 60;
			}
			
		}else if(object2.getType() == 2){
			if(((SpecialLokum)object2).getSpecialType() == 2){
				
					int numColors=0;
					for(int i=0; i<board.getBoardObjects().length; i++){
						for(int j=0; j<board.getBoardObjects()[0].length; j++){
							if(board.getObject(i, j).getColor() == object1.getColor()){
									numColors++;
							}
						}
						}
				crush.crushColor(board, ((RegularLokum)object1).getColor());
				BoardObject empty;
				empty = new EmptyObject(object1.getRow(), object1.getColumn());
				this.board.putObject(empty);
				score += numColors * numColors * 60;
				
			}
		}
		
		//fillEmptySpaces();
		
		t();
		b();
		a();
		fillEmptySpaces();
		
		while(vg.checkVerticalGroup(board).size() !=0 || hg.checkHorizontalGroup(board).size() !=0){
			if(hg.checkHorizontalGroup(board).size() !=0){
			a();
			fillEmptySpaces();
			}
			if(vg.checkVerticalGroup(board).size() !=0){
			b();
			fillEmptySpaces();
			}
			
		}
				
	}
	
	
	
	public void a(){
		ArrayList<GroupObject> horizontalGroups = hg.checkHorizontalGroup(board);
		int numOfGroups = horizontalGroups.size();
		
		for(int i=0; i<numOfGroups; i++){
			int groupSize =horizontalGroups.get(i).getGroupObjects().size();
			for(int k=0; k<groupSize; k++){
				if(horizontalGroups.get(i).getGroupObjects().get(k).getType() == 2){
					
					if(((SpecialLokum)horizontalGroups.get(i).getGroupObjects().get(k)).getSpecialType() == 0){
						if(((StripedLokum)horizontalGroups.get(i).getGroupObjects().get(k)).isOrientation() == true){
							crush.crushVertical(board, horizontalGroups.get(i).getGroupObjects().get(k).getColumn());
							score += board.getBoardObjects().length * 60;
						}else{
							crush.crushHorizontal(board, horizontalGroups.get(i).getGroupObjects().get(k).getRow());
							score += board.getBoardObjects()[0].length * 60;
						}
						
					}else if(((SpecialLokum)horizontalGroups.get(i).getGroupObjects().get(k)).getSpecialType() == 1){
						crush.crushArea(board, horizontalGroups.get(i).getGroupObjects().get(k).getRow(),
								horizontalGroups.get(i).getGroupObjects().get(k).getColumn());
						score += 1080;
					}
					
				}
			}
			crush.crushGroup(board, horizontalGroups.get(i));
			if(groupSize >= 5){
				
				ColorBombLokum cb = new ColorBombLokum(horizontalGroups.get(i).getGroupObjects().get(2).getRow(),
													horizontalGroups.get(i).getGroupObjects().get(2).getColumn());
				board.putObject(cb);
				score += 200;
			}else if(groupSize >= 4){
				StripedLokum sl = new StripedLokum(horizontalGroups.get(i).getGroupObjects().get(2).getRow(),
														horizontalGroups.get(i).getGroupObjects().get(2).getColumn(),
														(horizontalGroups.get(i).getGroupObjects().get(2)).getColor(),
														true);
				board.putObject(sl);
				score += 120;
			}else{
				score += 60;
			}
			//fillEmptySpaces();
		
		}
	}
	
	public void b(){
		ArrayList<GroupObject> verticalGroups = vg.checkVerticalGroup(board);
		int numOfGroups = verticalGroups.size();
		
		for(int i=0; i<numOfGroups; i++){
			int groupSize = verticalGroups.get(i).getGroupObjects().size();
			for(int k=0; k< groupSize; k++){
				if(verticalGroups.get(i).getGroupObjects().get(k).getType() == 2){
					
					if(((SpecialLokum)verticalGroups.get(i).getGroupObjects().get(k)).getSpecialType() == 0){
						if(((StripedLokum)verticalGroups.get(i).getGroupObjects().get(k)).isOrientation() == true){
							crush.crushVertical(board, verticalGroups.get(i).getGroupObjects().get(k).getColumn());
							score += board.getBoardObjects().length * 60;
						}else{
							crush.crushHorizontal(board, verticalGroups.get(i).getGroupObjects().get(k).getRow());
							score += board.getBoardObjects()[0].length * 60;
						}
						
					}else if(((SpecialLokum)verticalGroups.get(i).getGroupObjects().get(k)).getSpecialType() == 1){
						crush.crushArea(board, verticalGroups.get(i).getGroupObjects().get(k).getRow(),
								verticalGroups.get(i).getGroupObjects().get(k).getColumn());
						score += 1080;
					}
					
				}
			}
			crush.crushGroup(board, verticalGroups.get(i));
			if(groupSize >= 5){
				
				ColorBombLokum cb = new ColorBombLokum(verticalGroups.get(i).getGroupObjects().get(2).getRow(),
														verticalGroups.get(i).getGroupObjects().get(2).getColumn());
				board.putObject(cb);
				score += 200;
			}else if(groupSize >= 4){
				StripedLokum sl = new StripedLokum(verticalGroups.get(i).getGroupObjects().get(2).getRow(),
													verticalGroups.get(i).getGroupObjects().get(2).getColumn(),
														(verticalGroups.get(i).getGroupObjects().get(2)).getColor(),
														true);
				board.putObject(sl);
				score += 120;
			}else{
				score += 60;
			}
			//fillEmptySpaces();
		
		}
	}
	
	
	public void t(){
		ArrayList<GroupObject> tGroups = tg.checkTGroup(board);
		int numOfGroups = tGroups.size();
		
		for(int i=0; i<numOfGroups; i++){
			int groupSize = tGroups.get(i).getGroupObjects().size();
			for(int k=0; k< groupSize; k++){
				if(tGroups.get(i).getGroupObjects().get(k).getType() == 2){
					
					if(((SpecialLokum)tGroups.get(i).getGroupObjects().get(k)).getSpecialType() == 0){
						if(((StripedLokum)tGroups.get(i).getGroupObjects().get(k)).isOrientation() == true){
							crush.crushVertical(board, tGroups.get(i).getGroupObjects().get(k).getColumn());
							score += board.getBoardObjects().length * 60;
						}else{
							crush.crushHorizontal(board, tGroups.get(i).getGroupObjects().get(k).getRow());
							score += board.getBoardObjects()[0].length * 60;
						}
						
					}else if(((SpecialLokum)tGroups.get(i).getGroupObjects().get(k)).getSpecialType() == 1){
						crush.crushArea(board, tGroups.get(i).getGroupObjects().get(k).getRow(),
								tGroups.get(i).getGroupObjects().get(k).getColumn());
						score += 1080;
					}
					
				}
				
			}
			crush.crushGroup(board, tGroups.get(i));
			WrappedLokum wl = new WrappedLokum(tGroups.get(i).getGroupObjects().get(1).getRow(),
					tGroups.get(i).getGroupObjects().get(1).getColumn(),
					tGroups.get(i).getGroupObjects().get(1).getColor());
					board.putObject(wl);
					score += 200;
			//fillEmptySpaces();
		
		}
	}


	
	/**
	 * 
	 * @return Lokum : randomly generated lokum
	 */
	public RegularLokum generateRegularLokum(){
	 /**
	  * Pre-condition: Entry to the procedure by a call from the procedures fillEmptySpaces or populateBoard
	  * 
	  * @requires:  score != null	&&	board != null	&&	level != null
	  * 
	  * Post-condition: Exit of the procedure			 
	  * 
	  * @ensures:  score != null    &&	board != null   && level != null
	  * 		 Score, board and level remains same.
	  * 		 A random Lokum object is created.
	  * 
	  * @modifies: 
	  * 
	  * 
	  */
		int lokumType = random.nextInt(4);
		RegularLokum lokum;
		switch (lokumType) {
        case 0:  lokum = new BrownHazelnutLokum(0,0);
                 break;
        case 1:  lokum = new RedRoseLokum(0,0);
                 break;
        case 2:  lokum = new WhiteCoconutLokum(0,0);
                 break;
        case 3:  lokum = new GreenPistachioLokum(0,0);
                 break;
        default: lokum = null;
        		 break;
		}
		return lokum;
	}
	
	public void fillEmptySpaces(){
		/**
		 * Pre-condition: Entry to the procedure
		 * 
		 * @requires: score != null && board != null && level != null
		 * 
		 * Post-condition: Exit to the procedure
		 * 
		 * @ensures: score != null && board != null && level != null
		 * 			Score and level remains same.
		 * 			Board's empty spaces filled with new randomly generated lokums.
		 * 
		 * @modifies: board
		 * 	
		 */
		
	
		for(int i = this.board.getBoardObjects().length - 1 ; i >= 0 ; i--){
			for (int j = 0; j < this.board.getBoardObjects()[0].length; j++){
				BoardObject empty;
				
				if(this.board.getBoardObjects()[i][j].getType() == 4){
				
					BoardObject dusecekObje;
					int dusecekObjeninYeri;
					dusecekObjeninYeri = i-1;
					
					while( dusecekObjeninYeri >0  && 
						this.board.getBoardObjects()[dusecekObjeninYeri][j].getType() == 4){
						dusecekObjeninYeri--;
						
					}
					if( dusecekObjeninYeri >= 0 &&
						this.board.getBoardObjects()[dusecekObjeninYeri][j].getType() !=4 ){
						
						
						dusecekObje = this.board.getBoardObjects()[dusecekObjeninYeri][j];
						dusecekObje.setRow(i);
						
						this.board.putObject(dusecekObje);
						
						empty = new EmptyObject(dusecekObjeninYeri, j);
						board.putObject(empty);
						
						System.out.println(board.toString());
						//this.board.getBoardObjects()[dusecekObjeninYeri][j].setType(4);
					}
					else{
						
						dusecekObje = generateRegularLokum();
						dusecekObje.setRow(i);
						dusecekObje.setColumn(j);
						
						this.board.setBoardObject(dusecekObje, i, j);
						
						System.out.println(board.toString());
					}
						
				}
			}
		}
		} 
		

	
	
	public void populateBoard(){
		/**
		* Pre-condition: Entry to the procedure
		* 
		* @requires: score != null && board == null && level != null
		* 
		* Post-condition: Exit to the procedure
		* 
		* @ensures: score != null && board != null && level != null
		* 			Score and level remains same.
		* 			All board is filled with new randomly generated lokums.
		* 
		* @modifies: board
		* 	
		*/
		for(int i=0; i<this.board.getBoardObjects().length; i++){
			for(int j=0; j<this.board.getBoardObjects()[0].length; j++){
				RegularLokum lokum = generateRegularLokum();
				lokum.setRow(i);
				lokum.setColumn(j);
				board.putObject(lokum);
			}
		}
	}
	
	public void saveGame(){
		/**
		 * Pre-condition: Entry to the procedure
		 * 
		 * @reqires: score != null	&&	board != null	&&	level != null 
		 * 	
		 * Post-condition: Exit of the procedure
		 * 
		 * @ensures:  score != null    &&	board != null   && level != null
		 * 			Score, board and level won't be changed until the game is loaded.
		 * 
		 * @modifies: 
		 * 
		 */
		String fileName=JOptionPane.showInputDialog("Enter a file name:");

		JFileChooser fileChooser = new JFileChooser(); 
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Choose a save directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		File saveFile=null;   
		if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) { 
			saveFile =  fileChooser.getSelectedFile();
		}
		else {
			JOptionPane.showMessageDialog(null,"No file Selected!");
			return;
		}

		File newXML=new File(saveFile.getAbsolutePath()+"//"+fileName+".xml");

		Writer writer = null;

		try {
			
			BoardObject[][] boardObjects = board.getBoardObjects();


			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newXML), "utf-8"));
			
			
			writer.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
			writer.write(" <game>\n");
			writer.write("<player>\n");
			writer.write("<id>" + id + "</id>\n");
			writer.write("<name>" + name + "</name>\n");
			writer.write("</player>\n");
			writer.write(" <board>\n");
			writer.write(" <lokums>\n");
			
			for(int i=0; i < boardObjects.length; i++){
				for(int j=0; j < boardObjects[0].length; j++){
					writer.write(" <lokum>\n");
					String colorString ="";
					switch ( boardObjects[i][j].getColor() ) {
					case 0:
						colorString = "Brown";
						break;
					case 1:
						colorString = "Red";
						break;
					case 2:
						colorString = "White";
						break;
					case 3:
						colorString = "Green";
						break;
					default:
						colorString = "";
					}
					
					writer.write(" <color>" + colorString + " </color>\n" );
					writer.write(" <position>\n");
					writer.write("  <xcoord>" + j + "</xcoord>\n");
					writer.write("  <ycoord>" + i + "</ycoord>\n");				
					writer.write(" </position>\n");
					
					String typeString ="";
					if(boardObjects[i][j].getType() == 1){
						typeString = "RegularLokum";
					}else if(boardObjects[i][j].getType() == 2){
					switch ( boardObjects[i][j].getColor() ) {
					case 0:
						typeString = "StripedLokum";
						break;
					case 1:
						typeString = "WrappedLokum";
						break;
					case 2:
						typeString = "ColorBombLokum";
						break;
					default:
						typeString = "";
					}
					}
					writer.write("<type>"+ typeString + "</type>\n");
					
					
					writer.write(" </lokum>\n");
				}
			}
			
			writer.write(" </lokums>\n");
			
			writer.write(" <obstacles>\n");
			writer.write(" <obstacle>\n");
			writer.write(" <color> </color>\n" );
			writer.write(" <position>\n");
			writer.write("  <xcoord></xcoord>\n");
			writer.write("  <ycoord></ycoord>\n");				
			writer.write(" </position>\n");
			writer.write(" </obstacle>\n");
			writer.write(" </obstacles>\n");
		
			writer.write(" </board>\n");
			writer.write(" <goalscore>" + goalScore + "</goalscore>\n");
			writer.write(" <currentscore>" + score + "</currentscore>\n");
			writer.write(" <movesleft>" + movesLeft + "</movesleft>\n");
			writer.write(" <level>" + level + " </level>\n");

			writer.write("</game>\n");
			
		} catch (IOException ex){

		} finally {
			try {writer.close();} catch (Exception ex) {}
		}

		
	}
	
	private void XMLparsing(Document xmlDocument){

		NodeList nList1 = xmlDocument.getElementsByTagName("game");
		
		for (int temp = 0; temp < nList1.getLength(); temp++) {
			 
			Node nNode = nList1.item(temp);
	 
			
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				String level = eElement.getElementsByTagName("level").item(0).getTextContent();
				this.level = Integer.parseInt(level);
				String score = eElement.getElementsByTagName("currentscore").item(0).getTextContent();
				this.score = Integer.parseInt(score);
				String goalscore = eElement.getElementsByTagName("goalscore").item(0).getTextContent();
				this.goalScore = Integer.parseInt(goalscore);
				String movesLeft = eElement.getElementsByTagName("movesleft").item(0).getTextContent();
				this.movesLeft = Integer.parseInt(movesLeft);
				
			}
		}
		
		NodeList nList = xmlDocument.getElementsByTagName("lokum");
		

		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				String color = eElement.getElementsByTagName("color").item(0).getTextContent();
				String xcoord = eElement.getElementsByTagName("xcoord").item(0).getTextContent();
				int x = Integer.parseInt(xcoord);
				String ycoord = eElement.getElementsByTagName("xcoord").item(0).getTextContent();
				int y = Integer.parseInt(ycoord);
				String type = eElement.getElementsByTagName("type").item(0).getTextContent();
				BoardObject object = null;
				if(type.equalsIgnoreCase("RegularLokum")){
					
					if(color.equalsIgnoreCase("green")){
						object = new GreenPistachioLokum(y,x);		
					}else if(color.equalsIgnoreCase("brown")){
						object = new BrownHazelnutLokum(y,x);
					}else if(color.equalsIgnoreCase("red")){
						object = new RedRoseLokum(y,x);
					}else if(color.equalsIgnoreCase("white")){
						object = new WhiteCoconutLokum(y,x);
					}
					
				}else if(type.equalsIgnoreCase("StripedLokum")){
					
					if(color.equalsIgnoreCase("green")){
						object = new StripedLokum(y,x,3,true);
					}else if(color.equalsIgnoreCase("brown")){
						object = new StripedLokum(y,x,0,true);
					}else if(color.equalsIgnoreCase("red")){
						object = new StripedLokum(y,x,1,true);
					}else if(color.equalsIgnoreCase("white")){
						object = new StripedLokum(y,x,2,true);
					}
					
				}if(type.equalsIgnoreCase("WrappedLokum")){
					
					if(color.equalsIgnoreCase("green")){
						object = new WrappedLokum(y,x,3);
					}else if(color.equalsIgnoreCase("brown")){
						object = new WrappedLokum(y,x,0);
					}else if(color.equalsIgnoreCase("red")){
						object = new WrappedLokum(y,x,1);
					}else if(color.equalsIgnoreCase("white")){
						object = new WrappedLokum(y,x,2);
					}
					
				}if(type.equalsIgnoreCase("ColorBombLokum")){
						object = new ColorBombLokum(y,x);
				}
				
				
				board.putObject(object);
			}
		}
		
	}
	
	public void loadGame(){		
		//ChooseXMLFile xmlChooser = new ChooseXMLFile();
		JFileChooser fileChooser = new JFileChooser(); 
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Choose an XML File");
		//fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		File xmlFile=null;   
		if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) { 
			xmlFile =  fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(null,"File selected successfully.");
		}
		else {
			JOptionPane.showMessageDialog(null,"No file Selected!");
			return;
		}
		//File xmlFile = new File("game.xml");
		
		if(xmlFile==null){
			return;
		}
		File xsdFile = new File("game.xsd.xml");
		Document xmlDocument=XMLvalidation(xmlFile,xsdFile);
		XMLparsing(xmlDocument);
		//scoreListener.OnScoreEvent();
	}
	
private Document XMLvalidation(File xmlFile,File xsdFile){
		
		Schema schema=null;
		Document xmlDocument=null;

		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace(); 
			System.exit(0);
		}

		try {
			xmlDocument = builder.parse(xmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
			loadGame();
		} catch (IOException e) {
			e.printStackTrace();
			loadGame();
		}

		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(xsdFile);
		} catch (Exception e) {
			e.printStackTrace();
			loadGame();
		}

		Validator validator = schema.newValidator();

		try {
			validator.validate(new DOMSource(xmlDocument));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadGame();
		}
		return xmlDocument;		
	}	
	
	public Board getBoard(){
		return board;
	}


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}


	public Integer getGoalScore() {
		return goalScore;
	}


	public void setGoalScore(Integer goalScore) {
		this.goalScore = goalScore;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public Integer getMovesLeft() {
		return movesLeft;
	}


	public void setMovesLeft(Integer movesLeft) {
		this.movesLeft = movesLeft;
	}
	

}
