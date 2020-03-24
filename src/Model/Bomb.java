package Model;

import java.io.File;
import java.util.ArrayList;

import View.Sound;
import Model.Game;

public class Bomb extends Inventory implements Runnable{
	private boolean thread_active = true;
	private Thread thread;
	private Game game;

	public Bomb(int X, int Y, int image,Game game) {
		super(X, Y, image, game);
		this.game = game;
		this.canWeCrush = true;
	}

	//ramasse la bombe
	public void loot() {
		if (game.getSound() == true) {
			Sound Sound = new Sound(new File("Son/loot.wav"));
			Sound.playSound();
		}
		game.getActivePlayer().addBomb(3);   // Quand on ramasse une bombe, on en a 3 en plus dans l'inventaire
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public void activate() {
		this.canWeCrush = false;
		this.thread = new Thread(this);
		thread.start();
		}
		
	public void run() {
		try {
			while(thread_active == true && game.getPause() == false) {
				if (game.getSound() == true) {
					Sound Sound1 = new Sound(new File("Son/bomb.wav")); 
					Sound1.playSound();
				}
				Thread.sleep(2500);
				thread_active = false;	
				canWeCrush = true;
				crush(); //detruit le bloc si il est breakable
				explosion();
			}
			
			
		}
		catch(Exception e){
			System.out.println("Exception in Bomb");
		}
	}
	
	//explosion de la bombe, fait des degats de proximité et augmente les etoiles si on touche un civil/policier
	private void explosion() {
		ArrayList<GameObject> liste = new ArrayList<GameObject>();
		
		for (GameObject object: game.getListeObject()) {
			int distX = object.getPosX() - this.getPosX();
        	int distY = object.getPosY()- this.getPosY();
			if ((-2< distX  && 2> distX)&&(-2<distY && 2> distY)) {
				if(object instanceof Perso) {
					((Perso)object).addLife(-40);	
					liste.add(object);
					if(object instanceof Citizen || object instanceof Policier) {
						if(game.getEtoiles() < 3) {
							game.addEtoiles();
						}
					}
				}
				else if(object instanceof BlockBreakable) {				
				} 
				else {
					liste.add(object);
				}
			}
			else {
				liste.add(object);
			}
		}
		game.setListeObject(liste);     // on a recréé la liste des objects de la map sur laquellle on se trouve pour y supprimer les blocs cassé et on demande à game de l'update
	}

}
