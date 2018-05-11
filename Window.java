import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	private final int WINDOW_HEIGHT=960, WINDOW_WIDTH=800;
	private Game game;
	private JPanel gamePanel, gameStatePanel;
	private JButton newRoundButton, hitButton, standButton, exitButton;
	
	public Window() {
		setTitle("BlackJackGUI");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //centers window
		setUndecorated(true);
		buildGamePanel();
		setVisible(true);
	}
	
	private void buildGameStatePanel() {
		gameStatePanel = new JPanel();
		gameStatePanel.setBounds(0, 400, 800, 100);
		gameStatePanel.setBackground(Color.CYAN);
	}
	
	private void buildGamePanel(){
		game = new Game();
		gamePanel = new JPanel();
		gamePanel.setLayout(null); 
		gamePanel.setBackground(new Color(7, 145, 27));
		gamePanel.add(game.getPlayerPanel());
		gamePanel.add(game.getDealerPanel());
		buildButtons();
		gamePanel.add(newRoundButton);
		gamePanel.add(hitButton);
		gamePanel.add(standButton);
		gamePanel.add(exitButton);
		buildGameStatePanel();
		gamePanel.add(gameStatePanel);
		add(gamePanel);
		invalidate();
		validate();
	}
	
	private void buildButtons(){
		ButtonListener bl = new ButtonListener();
		newRoundButton = new JButton("New Round");
		newRoundButton.addActionListener(bl);
		newRoundButton.setBounds(10, 900, 180, 50);
		newRoundButton.setBackground(Color.WHITE);
		newRoundButton.setForeground(Color.BLACK);
		newRoundButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		hitButton = new JButton("Hit");
		hitButton.addActionListener(bl);
		hitButton.setBounds(210, 900, 180, 50);
		hitButton.setBackground(Color.WHITE);
		hitButton.setForeground(Color.BLACK);
		hitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		standButton = new JButton("Stand");
		standButton.addActionListener(bl);
		standButton.setBounds(410, 900, 180, 50);
		standButton.setBackground(Color.WHITE);
		standButton.setForeground(Color.BLACK);
		standButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		exitButton = new JButton("Exit");
		exitButton.addActionListener(bl);
		exitButton.setBounds(610, 900, 180, 50);
		exitButton.setBackground(Color.WHITE);
		exitButton.setForeground(Color.BLACK);
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == newRoundButton) {
				remove(gamePanel);
				buildGamePanel();
			}
			else if(e.getSource() == hitButton) {
				game.playerHit();
				if(game.getGameState() == GameState.DEALER_WINS) {
					hitButton.setEnabled(false);
					standButton.setEnabled(false);
					//display that player busts and dealer wins
				}
				else if(game.getGameState() == GameState.DEALER_TURN) {
					game.dealerTurn();
				}
			}
            else if(e.getSource() == standButton) {
            	hitButton.setEnabled(false);
				standButton.setEnabled(false);
				game.playerStand();
			}
            else if(e.getSource() == exitButton) {
            	setVisible(false);
				dispose();
			}
		}
	}
}
