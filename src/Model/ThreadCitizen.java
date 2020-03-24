package Model;

import java.util.Random;

public class ThreadCitizen implements Runnable {
	private Thread thread;
	private Citizen c;
	private Game game;
	private boolean thread_active = true;
	private int numeroMap;
	private long time = System.currentTimeMillis();
	
	public ThreadCitizen(Citizen c,Game game,int numeroMap) {
		this.c = c;
		this.game = game;
		this.numeroMap=numeroMap;
		this.thread = new Thread(this);
		thread.start();	
		
	}
	private void dead() {          // Au lieu de les supprimer de la map, on les téléporte avec les jauges de vie au maximum
		//thread_active = false;
		//c.delete();
		game.citizenDead(c.getPosX(),c.getPosY());
		c.setLife(100);
    	c.setPosX(2);
    	c.setPosY(7);
	}
	public void run() {				//déplacement aléatoire des citoyens
		try {
			Random rand = new Random();
			while (thread_active == true) {
				Thread.sleep(100);
				if (game.getPause() == false) {
					if (numeroMap == game.getNumeroMap()) {
						if (c.getLife() <= 0) {
							dead();
						}
						if (System.currentTimeMillis() - time > 1500) {
							int direction = rand.nextInt(4);
							switch (direction) {
								case 0 : if (game.Obstacle(c.getPosX()+1,c.getPosY())==false) {
									 c.rotate(1, 0); c.move(1,0);  break;
								}
								case 1 : if (game.Obstacle(c.getPosX(),c.getPosY()-1)==false) {
									c.move(0,-1); c.rotate(0, -1);  break;
								}
								case 2 : if (game.Obstacle(c.getPosX()-1,c.getPosY())==false) {
									c.move(-1,0); c.rotate(-1, 0); break;
								}
								case 3 : if (game.Obstacle(c.getPosX(),c.getPosY()+1)==false) {
									c.move(0,1); c.rotate(0, 1); break;
								}
							} time = System.currentTimeMillis();					
						}
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception in ThreadCitizen");
		}
	}

}
