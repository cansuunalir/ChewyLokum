import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationWindow extends JFrame implements ActionListener{

		/**
		 * 
		 */

		protected JButton loadButton;
		protected JButton startButton;
		public JPanel scorepanel;
		JLabel scorelabel;
		JLabel levellabel;
		JLabel movelabel;
		JLabel goallabel;
		public int moveleft;
		public int score;
		public int goalscore;
		public int levelno ;
		Window w;
		
		
		private static final long serialVersionUID = 1L;
		public ApplicationWindow(){
			
			super("Chewy Lokum Legend");
			setLayout(new BorderLayout());
			//setPreferredSize(new Dimension(1000, 1000));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//	levelno = 1;
		//	goalscore = 5000;
		//	moveleft = 30;
			//score = 0;
			
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			  int height = screenSize.height;
			  int width = screenSize.width;
			  setSize(width/4, height*3/4);
			  
			setLocationRelativeTo(null);
			setResizable(false);
			
			startButton = new JButton("Play A New Game");
			loadButton = new JButton("Load The Saved Game");
			
		  	w = new Window();

			
			
			startButton.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			  
					startButton.setVisible(false);
					loadButton.setVisible(false);
				//	setVisible(false);
			//		scorepanel = new JPanel();
			//		scorepanel.setLayout(new BoxLayout(scorepanel,BoxLayout.Y_AXIS));
					
					
			//		levellabel = new JLabel("     Level: "+levelno);
				//	goallabel = new JLabel("     Goal Score: "+goalscore);
			//		scorelabel = new JLabel("     Score: "+score);
			//		movelabel = new JLabel("     Move Left: "+moveleft);
			//		scorepanel.add(scorelabel);
			//		scorepanel.add(levellabel);
			//		scorepanel.add(goallabel);
			//		scorepanel.add(movelabel);
			//		score = w.ge.score;
			//		
					w.setVisible(true);
					add(w,BorderLayout.CENTER);
					//add(scorepanel,BorderLayout.EAST);
					pack();
			      }
			    });
			
			
			loadButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					w.ge.loadGame();
					startButton.setVisible(false);
					loadButton.setVisible(false);
					w.setVisible(true);
					add(w,BorderLayout.CENTER);
					
					pack();
				}
			});
			
			add(startButton,BorderLayout.NORTH);
			add(loadButton,BorderLayout.SOUTH);
		//	add(levellabel,BorderLayout.EAST);
		//	add(goallabel,BorderLayout.EAST);
		//	add(scorelabel);
		//	add(movelabel);
			
			
			
			
			
		
	}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
}
