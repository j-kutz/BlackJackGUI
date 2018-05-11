import javax.swing.JLayeredPane;

public class Game {
	private Player player;
	private Player dealer;
	private GameState gameState;
	
	public Game() {
		gameState = GameState.PLAYER_TURN;
		player = new Player();
		player.setPanelLocation(0, 500);
		dealer = new Player();
		dealer.setPanelLocation(0, 100);
	}
	
	public GameState getGameState() {
		return gameState;
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
	
	public void playerHit() {
		player.addCard();
		if(player.is21()) {
			gameState = GameState.PLAYER_WINS;
			System.out.println("Player Wins");
		}
		else if(player.isBust()) {
			gameState = GameState.DEALER_WINS;
			System.out.println("Player busts");
		}
		else gameState = GameState.DEALER_TURN;
	}
	
	public void playerStand() {
		gameState = GameState.DEALER_TURN;
		player.setStand(true);
		dealerTurn();
	}
	
	public void dealerHit() {
		dealer.addCard();
		if(dealer.is21()) {
			gameState = GameState.DEALER_WINS;
			System.out.println("Dealer Wins");
		}
		else if(dealer.isBust()) {
			gameState = GameState.PLAYER_WINS;
			System.out.println("Dealer busts");
		}
		else if(player.isStand()) dealerTurn();
		else gameState = GameState.PLAYER_TURN;
	}
	
	public void dealerStand() {
		gameState = GameState.PLAYER_TURN;
		dealer.setStand(true);
		if(player.isStand()) {
			if(player.getCount() == dealer.getCount()) System.out.println("Draw");
			else if(player.getCount() < dealer.getCount()) System.out.println("Dealer Wins");
			else System.out.println("Player wins");
		}
	}

	public void dealerTurn() {
		if(dealer.getCount() < 17) dealerHit();
		else dealerStand();
	}
}
