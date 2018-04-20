import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	private final int WINDOW_HEIGHT=960, WINDOW_WIDTH=800;
	private Game game;
	private JPanel gamePanel;
	
	public Window() {
		setTitle("BlackJackGUI");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //centers window
		setUndecorated(true);
		buildGamePanel();
		add(gamePanel);
		setVisible(true);
	}
	
	public void buildGamePanel (){
		game = new Game();
		gamePanel = new JPanel();
		gamePanel.setLayout(null); 
		gamePanel.setBackground(Color.CYAN);
		gamePanel.add(game.getPlayerPanel());
		gamePanel.add(game.getDealerPanel());
		
		/*
		 * new round button
		 */
		JButton newRoundButton = new JButton("NEW ROUND");
		newRoundButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//game
			}
		});
		newRoundButton.setBounds(10, 900, 180, 50);
		gamePanel.add(newRoundButton);
		
		/*
		 * hit button
		 */
		JButton hitButton = new JButton("HIT");
		hitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				game.hit();
			}
		});
		hitButton.setBounds(210, 900, 180, 50);
		gamePanel.add(hitButton);
		
		/*
		 * stand button
		 */
		JButton standButton = new JButton("STAND");
		standButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Button pressed");
			}
		});
		standButton.setBounds(410, 900, 180, 50);
		gamePanel.add(standButton);
		
		/*
		 * exit button
		 */
		JButton exitButton = new JButton("EXIT");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		exitButton.setBounds(610, 900, 180, 50);
		gamePanel.add(exitButton);
	}
}
