package Model;

import java.io.File;

import View.Sound;

public class PotionEnergy extends Inventory implements Runnable{
	private Game game;
	private boolean active_thread=true;
	private double time= 0;
	private Thread thread;

	public PotionEnergy(int X, int Y, int color,Game game) {
		super(X, Y, color, game);
		this.game = game;
		this.canWeCrush = true;
		game.setPotionEnergy(this);
	}

	public void activate() {
		time = System.currentTimeMillis();
		this.thread = new Thread(this);
		thread.start();
	}

	@Override
	public boolean isObstacle() {
		return false;
	}
	
	public void run() {	
		try{
			while (active_thread == true && (System.currentTimeMillis()-time)<2000) {
				Thread.sleep(50);
				if (game.getPause() == false) {
					if (game.getActivePlayer().getEnergy() < 1  ){
						game.getActivePlayer().addEnergy(0.5);
					}else {
						active_thread = false;
					}
					game.notifyView();
				}
			}
		}
		catch(Exception e) {
			System.out.println("Exception in PotionEnergy");
		}
	
	}

	@Override
	public void loot() {
		if (game.getSound() == true) {
			Sound Sound = new Sound(new File("Son/loot.wav"));
			Sound.playSound();
		}
		game.getActivePlayer().addPotionEnergy(2);    // Ramasser une potion d'énergie en rajoute 2 dans l'inventaire
	}
}
