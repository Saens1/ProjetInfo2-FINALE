package Model;

import java.io.File;

import View.Sound;

public class Teleportation extends Inventory{
	private Game game;

	public Teleportation(int X, int Y, int color,Game game) {
		super(X, Y, color, game);
		this.game = game;
		this.canWeCrush = true;
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
		game.getActivePlayer().addTeleportation(4);   // ramasser une teleportation en ajoute 4 dans l'inventaire
	}
}
