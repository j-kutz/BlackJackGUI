/*
	TODO: - add text during game (total, round counter, possible dealer AI)
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BlackJackGUI extends JFrame{
	private JPanel resultsPanel;
	private JPanel secondPanel;
	private JPanel startImagePanel;
	private JPanel boardPanel;
	private JLabel startImageLabel;
	private JLabel startTitleLabel;
	private JLabel newRoundLabel;
	private JButton hitButton;
	private JButton startButton;
	private JButton standButton;
	private JButton newRoundButton;
	private JButton exitButton;
	private final int WINDOW_HEIGHT=960, WINDOW_WIDTH=800;
	
	private JLabel[] cardImageLabels;
	private int[] cardValues;
	private ImageIcon[] cardImages;
	
	private int numberOfCards=0;
	private int total=0;  //contains the player's current total 
	private boolean firstRound=true; 
	private int[] cardGameValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	
	public BlackJackGUI(){
		setTitle("Blackjack");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //centers window
		setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		newRound();
		setVisible(true);
	}
	
	private void newRound(){
		valueArrayInit();
		imageArrayInit();
		labelArrayInit();
		buildStartImagePanel();
		buildSecondPanel();
		buildBoardPanel();
		buildResultsPanel();
		hitButton.setEnabled(true);
		standButton.setEnabled(true);
		if(firstRound){
			add(startImagePanel, BorderLayout.CENTER);
		}
		else{
			add(resultsPanel, BorderLayout.PAGE_START);
			add(secondPanel, BorderLayout.SOUTH);
			add(boardPanel, BorderLayout.CENTER);
			invalidate();
			validate();
			//hitButton.setEnabled(true);
			//standButton.setEnabled(true);
		}
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
	
	private void buildStartImagePanel(){
		startImagePanel = new JPanel();
		ImageIcon startImage = new ImageIcon("aces-small.png");
		startImageLabel = new JLabel(startImage);
		startImagePanel.setBackground(new Color(0, 0, 0));
		startTitleLabel = new JLabel("Blackjack by Jordan Kutz", JLabel.CENTER);
		startTitleLabel.setForeground(Color.WHITE);
		startTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		startButton = new JButton("Start");
		startButton.addActionListener(new startButtonListener());
		startButton.setBackground(Color.WHITE);
		startButton.setForeground(Color.BLACK);
		startButton.setPreferredSize(new Dimension(500, 50));
		startButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		startImagePanel.add(startImageLabel);
		startImagePanel.add(Box.createVerticalStrut(500));
		startImagePanel.add(startTitleLabel);
		startImagePanel.add(Box.createVerticalStrut(100));
		startImagePanel.add(startButton);
	}
	
	private void buildSecondPanel(){
		secondPanel = new JPanel();
		secondPanel.setBackground(new Color(7, 145, 27));
		hitButton = new JButton("Hit");
		hitButton.addActionListener(new hitButtonListener());
		standButton = new JButton("Stand");
		standButton.addActionListener(new standButtonListener());
		newRoundButton = new JButton("New Round");
		newRoundButton.addActionListener(new newRoundButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new exitButtonListener());
		buildButtons();
		secondPanel.add(newRoundButton);
		secondPanel.add(hitButton);
		secondPanel.add(standButton);
		secondPanel.add(exitButton);
	}
	
	private void buildBoardPanel(){
		boardPanel = new JPanel();
		boardPanel.setBackground(new Color(7, 145, 27));
		numberOfCards = 0;
		boardPanel.add(cardImageLabels[numberOfCards]);
		total = cardGameValues[cardValues[numberOfCards]-1];
		numberOfCards++;
		boardPanel.add(cardImageLabels[numberOfCards]);
		total += cardGameValues[cardValues[numberOfCards]-1];
		numberOfCards++;
	}
	
	private void buildResultsPanel(){
		resultsPanel = new JPanel();
		resultsPanel.setBackground(new Color(7, 145, 27));
		newRoundLabel = new JLabel();
		newRoundLabel.setText("Result: ");
		newRoundLabel.setForeground(Color.WHITE);
		newRoundLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		resultsPanel.add(newRoundLabel);
	}
	
	private void buildButtons(){
		hitButton.setPreferredSize(new Dimension(180, 50));
		hitButton.setBackground(Color.WHITE);
		hitButton.setForeground(Color.BLACK);
		hitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		standButton.setPreferredSize(new Dimension(180, 50));
		standButton.setBackground(Color.WHITE);
		standButton.setForeground(Color.BLACK);
		standButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		newRoundButton.setPreferredSize(new Dimension(180, 50));
		newRoundButton.setBackground(Color.WHITE);
		newRoundButton.setForeground(Color.BLACK);
		newRoundButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		exitButton.setPreferredSize(new Dimension(180, 50));
		exitButton.setBackground(Color.WHITE);
		exitButton.setForeground(Color.BLACK);
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
	
	private void displayNewRound(String result){
		
		switch(result){
			case   "win": newRoundLabel.setText("Results: You win");
			              break;
			case  "bust": newRoundLabel.setText("Results: You bust");
			              break;
			case "stand": newRoundLabel.setText("Results: You withdrew the game with a total of " + total);
			              break;
		}
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		invalidate();
		validate();
	}
	
	private class hitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			boardPanel.add(cardImageLabels[numberOfCards]);
			total += cardGameValues[cardValues[numberOfCards]-1];
			if(total == 21)
				displayNewRound("win");
			else if(total > 21)
				displayNewRound("bust");
			System.out.println(total);
			numberOfCards++;
			invalidate();
			validate();
		}
	}
	
	private class startButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			add(resultsPanel, BorderLayout.PAGE_START);
			add(secondPanel, BorderLayout.SOUTH);
			add(boardPanel, BorderLayout.CENTER);
			remove(startImagePanel);
			invalidate();
			validate();
			firstRound=false;
		}
	}
	
	private class standButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			displayNewRound("stand");
		}
	}
	
	private class newRoundButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){ 
			remove(secondPanel);
			newRound();
		}
	}
	
	private class exitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
			dispose();
		}
	}
	
	public static void main(String[] args){
		new BlackJackGUI();
	}
}