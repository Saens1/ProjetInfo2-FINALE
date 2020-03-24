package Model;

import java.io.File;

import View.Sound;

public class Munition extends Inventory{
	
	private Game game;

	public Munition(int X, int Y, int image,Game game) {
		super(X, Y, image, game);
		this.game = game;
		this.canWeCrush = true;
		// TODO Auto-generated constructor stub
	}

	public void activate() {
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public void loot() {
		if (game.getSound() == true) {
			Sound Sound = new Sound(new File("Son/loot.wav"));
			Sound.playSound();
		}
		game.getActivePlayer().addMunition(30);   // ramasser un pack de munition en rapporte 30 dans l'inventaire
	}

}
