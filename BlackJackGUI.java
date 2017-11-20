/*
	TODO: - add stand button
		  - add win/lose functionality
		  - add multiple rounds functionality
		  - polish layout
		  - add title text to stsrt screen
		  - add text during game (total, round counter, possible dealer AI)
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BlackJackGUI extends JFrame{
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel startImagePanel;
	private JPanel boardPanel;
	private JLabel startImageLabel;
	private JButton hitButton;
	private JButton startButton;
	private JButton standButton;
	private final int WINDOW_HEIGHT=600, WINDOW_WIDTH=800;
	
	private JLabel[] cardImageLabels;
	private int[] cardValues;
	private ImageIcon[] cardImages;
	
	private int numberOfCards=0;
	private int total=0;  //contains the player's current total 
	private int[] cardGameValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	
	public BlackJackGUI(){
		setTitle("Blackjack");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);		//centers window
		valueArrayInit();
		imageArrayInit();
		labelArrayInit();
		buildFirstPanel();
		buildStartImagePanel();
		buildSecondPanel();
		buildBoardPanel();
		add(firstPanel, BorderLayout.SOUTH);
		add(startImagePanel, BorderLayout.CENTER);
		//pack(); 
		setVisible(true);
	}
	
	private void valueArrayInit(){
		cardValues = new int[21];
		for(int i=0; i<cardValues.length; i++)
			cardValues[i] = (new Random().nextInt(12)+1);
	}
	
	private void imageArrayInit(){
		cardImages = new ImageIcon[21];
		for(int i=0; i<cardImages.length; i++)
			cardImages[i] = new ImageIcon(String.valueOf(cardValues[i]) + "s.png");
	}
	
	private void labelArrayInit(){
		cardImageLabels = new JLabel[21];
		for(int i=0; i<cardImageLabels.length; i++)
			cardImageLabels[i] = new JLabel(cardImages[i]);
	}
	
	private void buildFirstPanel(){
		firstPanel = new JPanel();
		firstPanel.setBackground(new Color(0, 0, 0));
		startButton = new JButton("Start");
		startButton.addActionListener(new startButtonListener());
		firstPanel.add(startButton);
	}
	
	private void buildStartImagePanel(){
		startImagePanel = new JPanel();
		ImageIcon startImage = new ImageIcon("aces-small.png");
		startImageLabel = new JLabel(startImage);
		startImagePanel.setBackground(new Color(0, 0, 0));
		startImagePanel.add(startImageLabel);
	}
	
	private void buildSecondPanel(){
		secondPanel = new JPanel();
		secondPanel.setBackground(new Color(7, 145, 27));
		hitButton = new JButton("Hit");
		hitButton.addActionListener(new hitButtonListener());
		standButton = new JButton("Stand");
		standButton.addActionListener(new standButtonListener());
		secondPanel.add(hitButton);
		secondPanel.add(standButton);
	}
	
	private void buildBoardPanel(){
		boardPanel = new JPanel();
		boardPanel.setBackground(new Color(7, 145, 27));
		boardPanel.add(cardImageLabels[numberOfCards]);
		total += cardGameValues[cardValues[numberOfCards]-1];
		numberOfCards++;
		boardPanel.add(cardImageLabels[numberOfCards]);
		total += cardGameValues[cardValues[numberOfCards]-1];
		numberOfCards++;
	}
	
	private class hitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			boardPanel.add(cardImageLabels[numberOfCards]);
			total += cardGameValues[cardValues[numberOfCards]-1];
			if(total >= 21)
				System.out.println("You win");
			System.out.println(total);
			numberOfCards++;
			invalidate();
			validate();
		}
	}
	
	private class startButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//setContentPane(secondPanel);
			add(secondPanel, BorderLayout.SOUTH);
			add(boardPanel, BorderLayout.CENTER);
			remove(firstPanel);
			remove(startImagePanel);
			invalidate();
			validate();
		}
	}
	
	private class standButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	public static void main(String[] args){
		new BlackJackGUI();
	}
}