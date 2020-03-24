package Model;

import java.io.File;


import View.Sound;

public class Grenade extends Inventory implements Runnable{
	private boolean thread_active = true;
	private Thread thread;
	private Game game;


	public Grenade(int X, int Y, int image,Game game) {
		super(X, Y, image, game);
		this.game = game;
		this.canWeCrush = true;
	}

	public void activate() {
		this.thread = new Thread(this);
		thread.start();
		this.canWeCrush = false;
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override //ramasse la grenade
	public void loot() {
		if (game.getSound() == true) {
			Sound Sound = new Sound(new File("Son/loot.wav"));
			Sound.playSound();
		}
		game.getActivePlayer().addGrenade(3);     // ramasser une grenade en donne 3 dans l'inventaire
	}
	
	public void run() {
		try {
			while(thread_active == true) {
				if (game.getPause() == false) {
					if (game.getSound() == true) {
						Sound Sound1 = new Sound(new File("Son/bomb.wav")); 
						Sound1.playSound();
					}
					Thread.sleep(2500);
					thread_active = false;	
					canWeCrush = true;
					crush();
					explosion();
				}
			}	
		}
		catch(Exception e){
			System.out.println("Exception in Grenade");
		}
	}
	
	private void explosion() { 
	
		for (GameObject object: game.getListeObject()) {
			int distX = object.getPosX()- this.getPosX();
        	int distY = object.getPosY()- this.getPosY();
			if ((-3< distX  && 3> distX)&&(-3<distY && 3> distY)) {
				if(object instanceof Perso) {
					((Perso)object).addLife(-20);
					if(object instanceof Citizen || object instanceof Policier) {
						if(game.getEtoiles() < 3) {
							game.addEtoiles();							
						}
					}
				}
			}
		}
	}

	
}
