package Model;

import java.util.ArrayList;
import View.Sound;
import java.io.File;

public class ThreadShoot extends Movable implements Deletable, Runnable{   // Deplace les tirs de balle et vérifie si quelqu'un est touché
	private boolean active_thread = true ;
	private int x;
	private int y;
	private Thread thread;
	private Game game;
	private long time = System.currentTimeMillis();
	private Perso p;
	
	public ThreadShoot(int x, int y, int image,Game game,Perso p) {
		super(x, y, image,0,0,game);	
		this.game = game;
		this.p = p;
		switch (image) {
		case 66: this.x = 1; this.y = 0; this.rotate(1, 0);  break;  
		case 67 : this.x = 0; this.y = 1; this.rotate(0, 1); break;
		case 68 : this.x = 0; this.y = -1;this.rotate(0, -1);  break;
		case 69 : this.x = -1; this.y = 0; this.rotate(-1, 0); break;
		}
		this.thread = new Thread(this);
		thread.start();
		if (game.getSound() == true && game.getPause() == false) {
			Sound Sound = new Sound(new File("Son/shoot.wav"));
			Sound.playSound();
		}
	}
	
	public void run() {
		synchronized(this) {
			
		try{
			while (active_thread == true) {				
				Thread.sleep(70);
				if (game.getPause() == false) {
				if (game.continueShoot(this) == false) {
				this.move(x,y); 
				}else {
					game.actionShoot(this,p);
					delete();
					active_thread = false;
				}
				if (System.currentTimeMillis() - time > 1500) {   // La balle a un temps de vie de 1.5 secondes
					active_thread = false;
					delete();
				}
				}
			}
			
		
		}
		catch(Exception e) {
			System.out.println("Exception in threadShoot");
		}
		}
	}
	
	private void delete(){
        notifyDeletableObserver();
    }
	public void notifyDeletableObserver(){
        ArrayList<GameObject> loot = null;
        game.delete(this,loot); //supprime la bombe
    }
}
