/*
	TODO: - update win/lose
	      - add round counter
		  - add split function
		  - add nonspade card images
   
    Bugs: - Check for dealer win/bust after hitButton
	      - player stand -> dealer hit -> dealer bust -> won because total higher
*/
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BlackJackGUI extends JFrame{
	private final int WINDOW_HEIGHT=960, WINDOW_WIDTH=800;
	private JLayeredPane gamePane;
	private JPanel buttonsPanel;
	private JPanel startImagePanel;
	private JPanel boardPanel;
	private JLabel startImageLabel;
	private JLabel startTitleLabel;
	private JLabel dealerUpdateLabel;
	private JLabel gameLabel;
	private JButton hitButton;
	private JButton startButton;
	private JButton standButton;
	private JButton newRoundButton;
	private JButton exitButton;
	
	private JLabel[] cardImageLabels;
	private int[] cardValues;
	private ImageIcon[] cardImages;
	private JLabel[] dealerImageLabels;
	private int[] dealerValues;
	private ImageIcon[] dealerImages;
	
	private int numberOfCards=0;
	private int dealerNumberOfCards=0;
	private int total=0;  //contains the player's current total 
	private int dealerTotal=0;
	private boolean firstRound=true; 
	private int[] cardGameValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	private String[] cardSuits = {"c", "d", "h", "s"};
	
	public BlackJackGUI(){
		setTitle("Blackjack");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //centers window
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
		newRound();
		setVisible(true);
	}
	
	private void newRound(){
		ArraysInit();
		labelArrayInit();
		dealerLabelArrayInit();
		buildStartImagePanel();
		buildSecondPanel();
		buildLayeredPane();
		if(firstRound){
			add(startImagePanel, BorderLayout.CENTER);
		}
		else{
			add(buttonsPanel, BorderLayout.SOUTH);
			add(gamePane, BorderLayout.CENTER);
			invalidate();
			validate();
		}
	}
	
	private void ArraysInit(){
		cardValues = new int[21];
		cardImages = new ImageIcon[21];
		dealerValues = new int[21];
		dealerImages = new ImageIcon[21];
		for(int i=0; i<21; i++){
			cardValues[i] = (new Random().nextInt(12)+1);
			cardImages[i] = new ImageIcon(String.valueOf(cardValues[i]) + String.valueOf(cardSuits[new Random().nextInt(3)]) + ".png");
			dealerValues[i] = (new Random().nextInt(12)+1);
			dealerImages[i] = new ImageIcon(String.valueOf(dealerValues[i]) + String.valueOf(cardSuits[new Random().nextInt(3)]) + ".png");
		}
	}
	
	private void labelArrayInit(){
		cardImageLabels = new JLabel[21];
		int x=50, y=600, width=138, height=211;
		for(int i=0; i<cardImageLabels.length; i++){
			cardImageLabels[i] = new JLabel(cardImages[i]);
			cardImageLabels[i].setBounds(x, y, width, height);
			x+=25;
		}
	}
	
	private void dealerLabelArrayInit(){
		dealerImageLabels = new JLabel[21];
		int x=50, y=50, width=138, height=211;
		for(int i=0; i<dealerImageLabels.length; i++){
			dealerImageLabels[i] = new JLabel(dealerImages[i]);
			dealerImageLabels[i].setBounds(x, y, width, height);
			x+=25;
		}
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
		startButton.addActionListener(new ButtonListener());
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
	
	private void buildLayeredPane(){
		gamePane = new JLayeredPane();
		gamePane.setBounds(100, 100, 800, 500);
		gamePane.setOpaque(true);
		gamePane.setBackground(new Color(7, 145, 27));
		dealerUpdateLabel = new JLabel(" ", JLabel.CENTER);
		dealerUpdateLabel.setForeground(Color.WHITE);
		dealerUpdateLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		dealerUpdateLabel.setBounds(0, 0, 800, 50);
		gamePane.add(dealerUpdateLabel);
		gameLabel = new JLabel(" ", JLabel.CENTER);
		gameLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		gameLabel.setForeground(Color.WHITE);
		gameLabel.setBounds(50, 350, 700, 100);
		gamePane.add(gameLabel);
		numberOfCards = 0;
		dealerNumberOfCards = 0;
		gamePane.add(cardImageLabels[numberOfCards], new Integer(numberOfCards));
		gamePane.add(dealerImageLabels[numberOfCards], new Integer(dealerNumberOfCards));
		total = cardGameValues[cardValues[numberOfCards]-1];
		dealerTotal = cardGameValues[dealerValues[numberOfCards]-1];
		numberOfCards++;
		dealerNumberOfCards++;
		gamePane.add(cardImageLabels[numberOfCards], new Integer(numberOfCards));
		gamePane.add(dealerImageLabels[numberOfCards], new Integer(dealerNumberOfCards));
		total += cardGameValues[cardValues[numberOfCards]-1];
		dealerTotal += cardGameValues[dealerValues[numberOfCards]-1];
		numberOfCards++;
		dealerNumberOfCards++;
	}
	
	private void buildSecondPanel(){
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(7, 145, 27));
		hitButton = new JButton("Hit");
		hitButton.addActionListener(new ButtonListener());
		standButton = new JButton("Stand");
		standButton.addActionListener(new ButtonListener());
		newRoundButton = new JButton("New Round");
		newRoundButton.addActionListener(new ButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ButtonListener());
		buildButtons();
		buttonsPanel.add(newRoundButton);
		buttonsPanel.add(hitButton);
		buttonsPanel.add(standButton);
		buttonsPanel.add(exitButton);
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
			case   "win": gameLabel.setText("You win");
			              dealerUpdateLabel.setText(" ");
			              break;
			case  "bust": gameLabel.setText("You bust");
			              dealerUpdateLabel.setText(" ");
			              break;
			case "stand": while(dealerTotal <= 16)
							  dealerTurn();
						  if(dealerTotal < total){
							  gameLabel.setText("You beat the dealer");
						      dealerUpdateLabel.setText(" ");
						  }
						  else if(dealerTotal == total){
							  gameLabel.setText("You tied with the dealer");
							  dealerUpdateLabel.setText(" ");
		                  }
						  else{
							  gameLabel.setText("Dealer Wins");
							  dealerUpdateLabel.setText(" ");
						  }
			              break;
		}
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		invalidate();
		validate();
	}
	
	private void dealerTurn(){
		if(dealerTotal < 21){
			if (dealerTotal <= 16){
				gamePane.add(dealerImageLabels[numberOfCards], new Integer(dealerNumberOfCards));
				dealerTotal += cardGameValues[dealerValues[numberOfCards]-1];
				dealerNumberOfCards++;
				dealerUpdateLabel.setText("dealer hits");
				if(dealerTotal == 21){
					dealerUpdateLabel.setText(" ");
					gameLabel.setText("dealer wins");
					hitButton.setEnabled(false);
					standButton.setEnabled(false);
				}
				else if(dealerTotal > 21){
					dealerUpdateLabel.setText("dealer busts");
					gameLabel.setText("You win!");
					hitButton.setEnabled(false);
					standButton.setEnabled(false);
				}
			}
			else{
				dealerUpdateLabel.setText("dealer stands");
			}
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == startButton){
				add(buttonsPanel, BorderLayout.SOUTH);
				add(gamePane, BorderLayout.CENTER);
				remove(startImagePanel);
				invalidate();
				validate();
				firstRound=false;
			}
		
			else if(e.getSource() == newRoundButton){
				remove(buttonsPanel);
				remove(gamePane);
				newRound();
			}
		
			else if(e.getSource() == hitButton){
				gamePane.add(cardImageLabels[numberOfCards], new Integer(numberOfCards));
				total += cardGameValues[cardValues[numberOfCards]-1];
				if(total == 21)
					displayNewRound("win");
				else if(total > 21)
					displayNewRound("bust");
				else
					dealerTurn();
				System.out.println(total);
				numberOfCards++;
				invalidate();
				validate();
			}
		
			else if(e.getSource() == standButton){
				displayNewRound("stand");
			}
		
			else if(e.getSource() == exitButton){
				setVisible(false);
				dispose();
			}
		}
	}
	
	public static void main(String[] args){
		new BlackJackGUI();
	}
}