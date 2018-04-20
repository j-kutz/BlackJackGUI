import javax.swing.JLayeredPane;

public class Game {
	private Player player;
	private Player dealer;
	
	public Game() {
		player = new Player();
		player.setPanelLocation(0, 500);
		dealer = new Player();
		dealer.setPanelLocation(0, 100);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getDealer() {
		return dealer;
	}

	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}
	
	public JLayeredPane getPlayerPanel() {
		return player.getPanel();
	}
	
	public JLayeredPane getDealerPanel() {
		return dealer.getPanel();
	}
	
	public void newRound() {
		
	}
	
	public void hit() {
		player.addCard();
	}
}
