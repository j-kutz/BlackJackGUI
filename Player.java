import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

public class Player {
	private ArrayList<Card> cards;
	private int count;
	private int cardCounter, xStart, yStart;
	private JLayeredPane panel;
	
	public Player() {
		count = 0;
		cardCounter = 0;
		xStart = 50;
		yStart = 50;
		cards = new ArrayList<Card>();
		buildPanel();
		addCard();
		addCard();
	}
	
	public JLayeredPane getPanel() {
		return panel;
	}

	public void setPanel(JLayeredPane panel) {
		this.panel = panel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void addToCount(int c) {
		this.count += c;
	}
	
	public void buildPanel() {
		panel = new JLayeredPane();
		panel.setSize(800, 300);
		panel.setOpaque(true);
		panel.setBackground(new Color(7, 145, 27));
	}
	
	public void setPanelLocation(int x, int y) {
		panel.setLocation(x, y);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void addCard() {
		Integer i = new Integer(cardCounter);
		cards.add(new Card(xStart+ (25*cardCounter), yStart));
		panel.add(cards.get(cardCounter).getLabel(), i);
		cardCounter++;
	}
	
}
