import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card {
	private int value;
	private String suit;
	private ImageIcon img;
	private JLabel label;
	
	public Card(int x, int y) {
		Random rand = new Random();
		int[] Values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		String[] Suits = {"c", "d", "h", "s"};
		
		int valIndex = rand.nextInt(13);
		value = Values[valIndex];
		suit = Suits[rand.nextInt(4)];
		
		img = new ImageIcon("cards/" + (valIndex+1) + suit + ".png");
		label = new JLabel(img);
		label.setBounds(x, y, 138, 211);
	}
	
	public int getValue() {
		return value;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public void setPosition(int x, int y) {
		label.setLocation(x, y);
	}
	
}
