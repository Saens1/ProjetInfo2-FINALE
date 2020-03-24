package Model;

public class Money extends Inventory{
	private Game game;

	public Money(int X, int Y, int image,Game game) {
		super(X, Y, image, game);
		this.game = game;
		this.canWeCrush = true;
	}

	public void loot() {
		game.getActivePlayer().addMoney(50);            // ramasser un sac d'argent donne 50$
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public void activate() { 
	}

}
