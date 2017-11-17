/*
	firstPanel - splash screen with a start button that displays image of cards
	secondPanel - displayed after start button from firstPanel is pressed
	              and hit button screen with new round button will be displayed
				  after that.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BlackJackGUI extends JFrame{
	private JPanel panel;
	private JButton hitButton;
	private JButton startButton;
	private final int WINDOW_HEIGHT=500, WINDOW_WIDTH=500;
	
	public BlackJackGUI(){
		setTitle("Blackjack");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //centers window
		buildPanel();
		add(panel);
		setVisible(true);
	}
	
	private void buildPanel(){
		panel = new JPanel();
		panel.setBackground(new Color(7, 145, 27));
		hitButton = new JButton("Hit");
		hitButton.addActionListener(new ButtonListener());
		startButton = new JButton("Start");
		startButton.addActionListener(new ButtonListener());
		panel.add(hitButton);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			panel.setBackground(Color.RED);
		}
	}
	
	public static void main(String[] args){
		new BlackJackGUI();
	}
}