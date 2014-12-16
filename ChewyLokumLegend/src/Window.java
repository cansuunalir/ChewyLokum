

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.RegularLokum;
import Model.SpecialLokum;
import Model.StripedLokum;
import Model.WrappedLokum;


public class Window extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameEngine ge;
	
	public static final int OBJECT_WIDTH = 50;
	public static final int OBJECT_HEIGHT = 56;
	private Image GreenPistachioLokum;
	private Image BrownHazelnutLokum;
	private Image WhiteCoconutLokum;
	private Image RedRoseLokum;
	private Image ColorBombLokum;
	private Image WrappedGreenLokum;
	private Image WrappedWhiteLokum;
	private Image WrappedRedLokum;
	private Image WrappedBrownLokum;
	private Image HorizontalStripedGreenLokum;
	private Image HorizontalStripedWhiteLokum;
	private Image HorizontalStripedRedLokum;
	private Image HorizontalStripedBrownLokum;
	private Image VerticalStripedGreenLokum;
	private Image VerticalStripedWhiteLokum;
	private Image VerticalStripedRedLokum;
	private Image VerticalStripedBrownLokum;
	private Image Empty;
	public JPanel corepanel;
	JLabel scorelabel;
	JLabel goallabel;
	JLabel movelabel;
	JLabel levellabel;
	JLabel emptylabel;
	protected JButton saveButton;
	public static int[] mousechecker = new int[4];
	
	public Window(){
		super();
		setVisible(true);
		this.setLayout(new BorderLayout());
		ge = new GameEngine();
		//ge.populateBoard();
		ge.t();
		ge.a();
		ge.b();
		ge.fillEmptySpaces();
		System.out.println(ge.vg.checkVerticalGroup(ge.getBoard()).size());
		ArrayList<GroupObject> verticalGroups = ge.vg.checkVerticalGroup(ge.getBoard());
		System.out.println("size:" + verticalGroups.size());
		for(int i = 0; i< verticalGroups.size(); i++){
			System.out.println("size of i"+verticalGroups.get(i).getGroupObjects().size());
			for(int j=0; j<verticalGroups.get(i).getGroupObjects().size(); j++){
				System.out.println(verticalGroups.get(i).getGroupObjects().get(j).getRow());
			}
		}
		setPreferredSize(new Dimension(ge.getBoard().getBoardObjects()[0].length*OBJECT_WIDTH+180, 
				ge.getBoard().getBoardObjects().length*OBJECT_HEIGHT));

		corepanel = new JPanel();
		corepanel.setLayout(new BoxLayout(corepanel,BoxLayout.Y_AXIS));
		//corepanel.setLayout(new BoxLayout(corepanel,BoxLayout.Y_AXIS));
		//corepanel.setLocation(ge.getBoard().getBoardObjects()[0].length*OBJECT_WIDTH, 0);
		scorelabel = new JLabel("  Score is: "+0+"  ");
		goallabel = new JLabel("  Goal score is: "+ge.getGoalScore()+"  ");
		levellabel = new JLabel("  Level No is: "+ge.getLevel()+"  ");
		movelabel = new JLabel("  Number of moves left is: "+ge.getMovesLeft()+"  ");
		saveButton = new JButton("Save the game!");
		emptylabel = new JLabel("  ");
		
		corepanel.add(levellabel);
		corepanel.add(goallabel);
		corepanel.add(scorelabel);
		corepanel.add(movelabel);
		corepanel.add(emptylabel);

		
		corepanel.add(saveButton);
		add(corepanel, BorderLayout.EAST);
	//	add(corepanel, new BoxLayout(corepanel, BoxLayout.Y_AXIS));
		//deneme.setHorizontalAlignment(JLabel.RIGHT);
	//	deneme.setLocation(100, 10);
	//	deneme.setLocation(ge.getBoard().getBoardObjects()[0].length*OBJECT_WIDTH, 0);
	//	add(deneme);
		
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ge.saveGame();
				
			}
		});
		
		//remove(deneme);
		try {
			 GreenPistachioLokum = ImageIO.read(new File("GreenPistachioLokum.png"));
			 BrownHazelnutLokum = ImageIO.read(new File("BrownHazelnutLokum.png"));
			 WhiteCoconutLokum = ImageIO.read(new File("WhiteCoconutLokum.png"));
			 RedRoseLokum = ImageIO.read(new File("RedRoseLokum.png"));
			 ColorBombLokum = ImageIO.read(new File("ColorBombLokum.png"));
			 WrappedGreenLokum = ImageIO.read(new File("WrappedGreenPistachioLokum.png"));
			 WrappedWhiteLokum = ImageIO.read(new File("WrappedWhiteCoconutLokum.png"));
			 WrappedRedLokum = ImageIO.read(new File("WrappedRedRoseLokum.png"));
			 WrappedBrownLokum = ImageIO.read(new File("WrappedBrownHazelnutLokum.png"));
			 HorizontalStripedGreenLokum = ImageIO.read(new File("HorizontalStripedGreenPistachioLokum.png"));
			 HorizontalStripedWhiteLokum = ImageIO.read(new File("HorizontalStripedWhiteCoconutLokum.png"));
			 HorizontalStripedRedLokum = ImageIO.read(new File("HorizontalStripedRedRoseLokum.png"));
			 HorizontalStripedBrownLokum = ImageIO.read(new File("HorizontalStripedBrownHazelnutLokum.png"));
			 VerticalStripedGreenLokum = ImageIO.read(new File("VerticalStripedGreenPistachioLokum.png"));
			 VerticalStripedWhiteLokum = ImageIO.read(new File("VerticalStripedWhiteCoconutLokum.png"));
			 VerticalStripedRedLokum = ImageIO.read(new File("VerticalStripedRedRoseLokum.png"));
			 VerticalStripedBrownLokum = ImageIO.read(new File("VerticalStripedBrownHazelnutLokum.png"));
			 Empty = ImageIO.read(new File("a.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<mousechecker.length;i++){
			mousechecker[i] = -1;
		}
		//super.addMouseListener(this);
        addMouseListener(this);
	}
	

	
	@Override
	public void paint(Graphics g) {
	super.paint(g);
	drawBoard(g);
	}
	
public void drawBoard(Graphics g){
		
	
	
		for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
			for(int j=0; j<ge.getBoard().getBoardObjects()[0].length; j++){
				
				if(ge.getBoard().getObject(i,j).getType() == 1){
					
							if(((RegularLokum) ge.getBoard().getObject(i,j)).getColor() == 0){
								g.drawImage(BrownHazelnutLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
							}else if(((RegularLokum) ge.getBoard().getObject(i,j)).getColor() == 1){
								g.drawImage(RedRoseLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
							}else if(((RegularLokum) ge.getBoard().getObject(i,j)).getColor() == 2){
								g.drawImage(WhiteCoconutLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
							}else if(((RegularLokum) ge.getBoard().getObject(i,j)).getColor() == 3){
								g.drawImage(GreenPistachioLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
							}
					
				}else if(ge.getBoard().getObject(i,j).getType() == 2){
					
							if(((SpecialLokum) ge.getBoard().getObject(i,j)).getSpecialType() == 1){
		
									if(((WrappedLokum) ge.getBoard().getObject(i,j)).getColor() == 0){
										g.drawImage(WrappedBrownLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
									}else if(((WrappedLokum) ge.getBoard().getObject(i,j)).getColor() == 1){
										g.drawImage(WrappedRedLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
									}else if(((WrappedLokum) ge.getBoard().getObject(i,j)).getColor() == 2){
										g.drawImage(WrappedWhiteLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
									}else if(((WrappedLokum) ge.getBoard().getObject(i,j)).getColor() == 3){
										g.drawImage(WrappedGreenLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
									}
							}else if(((SpecialLokum) ge.getBoard().getObject(i,j)).getSpecialType() == 0){
									if(((StripedLokum) ge.getBoard().getObject(i,j)).getColor() == 0){
										if(((StripedLokum) ge.getBoard().getObject(i,j)).isOrientation() == true){
											g.drawImage(VerticalStripedBrownLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);	
											}else{
											g.drawImage(HorizontalStripedBrownLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
											}
									}else if(((StripedLokum) ge.getBoard().getObject(i,j)).getColor() == 1){
										if(((StripedLokum) ge.getBoard().getObject(i,j)).isOrientation() == true){
											g.drawImage(VerticalStripedRedLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);	
											}else{
											g.drawImage(HorizontalStripedRedLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
											}
									}else if(((StripedLokum) ge.getBoard().getObject(i,j)).getColor() == 2){
										if(((StripedLokum) ge.getBoard().getObject(i,j)).isOrientation() == true){
											g.drawImage(VerticalStripedWhiteLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);	
											}else{
											g.drawImage(HorizontalStripedWhiteLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
											}
									}else if(((StripedLokum) ge.getBoard().getObject(i,j)).getColor() == 3){
										if(((StripedLokum) ge.getBoard().getObject(i,j)).isOrientation() == true){
											g.drawImage(VerticalStripedGreenLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);	
											}else{
											g.drawImage(HorizontalStripedGreenLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
											}
									}
							}else{
								g.drawImage(ColorBombLokum, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
							}
				}else if(ge.getBoard().getObject(i,j).getType() == 4){
					g.drawImage(Empty, j*OBJECT_WIDTH, i*OBJECT_HEIGHT, null);
				}
					
				}
			}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int lokkumsX = e.getX()/OBJECT_WIDTH;
		int lokkumsY = e.getY()/OBJECT_HEIGHT;
		
		
		
		if(mousechecker[0] == (-1)){
			mousechecker[0] = lokkumsX;
			mousechecker[1] = lokkumsY;	
		} else if ((mousechecker[0] != lokkumsX)  ||  (mousechecker[1] != lokkumsY)){
			mousechecker[2] = lokkumsX;
			mousechecker[3] = lokkumsY;
			//
			int row1, row2, col1, col2;
			col1 = mousechecker[0];
			col2 = mousechecker[2];
			row1 = mousechecker[1];
			row2 = mousechecker[3];
			System.out.println(ge.getBoard().toString());
//			for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
//				for(int j=0; j<ge.getBoard().getBoardObjects()[0].length; j++){
//					System.out.print(((RegularLokum)ge.getBoard().getBoardObjects()[i][j]).getColor()+" ");
//				}
//				System.out.println();
//			}
			ge.handleSelections(ge.getBoard().getObject(row1, col1),ge.getBoard().getObject(row2, col2));
			scorelabel.setText("  Score is: "+ge.getScore()+"  ");
			movelabel.setText("  Number of moves left is: "+ge.getMovesLeft()+"  ");
//			BoardObject a= ge.getBoard().getObject(row2, col2);
//			a.setRow(row1);
//			a.setColumn(col1);
//			ge.getBoard().putObject(a);
			System.out.println("SWAP");
			
//			for(int i=0; i<ge.getBoard().getBoardObjects().length; i++){
//				for(int j=0; j<ge.getBoard().getBoardObjects()[0].length; j++){
//					System.out.print(((RegularLokum)ge.getBoard().getBoardObjects()[i][j]).getColor()+" ");
//				}
//				System.out.println();
//			}
			repaint();
			//ge.a();
			System.out.println(ge.getBoard().toString());
			
			repaint();
			//
			for(int i=0;i<mousechecker.length;i++){
				mousechecker[i] = -1;
			}
		} else {
			mousechecker[0] = -1;
			mousechecker[1] = -1;
		}
		System.out.println("1-------"+mousechecker[0]);
		System.out.println("2-------"+mousechecker[1]);
		System.out.println("3-------"+mousechecker[2]);
		System.out.println("4-------"+mousechecker[3]);
				
	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
