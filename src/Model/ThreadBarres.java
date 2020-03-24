package Model;

public class ThreadBarres implements Runnable{                    // s'occupe de faire diminuer les différentes jauges des personnages au fur et à mesure que le temps passe
	private double lossAnger = 0.05;
	private double lossHunger = 0.05;
	private double lossHygiene = 0.05;
	private Player p;
	private Thread thread;
	private Game game;
	private long time = System.currentTimeMillis();
	private boolean active_thread = true;
	private static boolean end = false;

	public ThreadBarres(int waitingTime, Player p,Game game,double lossAnger, double lossHunger, double lossHygiene) {
		this.p = p;		
		this.game = game;
		this.lossAnger = lossAnger;
		this.lossHunger = lossHunger;
		this.lossHygiene = lossHygiene;
		this.thread = new Thread(this);
		thread.start();
	}
	
	public void threadEtoile() { 				// Permet de dimuner le nombre d'étoile de recherche
		
		if (System.currentTimeMillis() - time > 400 && p == game.getPlayer1()) {
			time = System.currentTimeMillis();
			
			
			if (game.getEtoiles() == 3) {
				p.setTimeStars1(100);
				p.setTimeStars2(100);
				p.addTimeStars3(-1);
				if (p.getTimeStars3() <=0) {
					game.setEtoiles(2);
				}
			}
			else if (game.getEtoiles() == 2) {
				p.setTimeStars3(100);
				p.setTimeStars1(100);
				p.addTimeStars2(-1);
				if (p.getTimeStars2() <= 0) {
					game.setEtoiles(1);
				}
			}
			else if (game.getEtoiles() == 1) {
				p.setTimeStars2(100);
				p.addTimeStars1(-1);
				if (p.getTimeStars1() <= 0) {
					p.setTimeStars1(100);
					game.setEtoiles(0);
				}
			}else if (game.getEtoiles() == 0) {
				p.setTimeStars1(100);
				p.setTimeStars2(100);
				p.setTimeStars3(100);
			}
		}
	}
	
	public void run() {
		try {
			while (active_thread == true) {
				Thread.sleep(100);
				if (game.getPause() == false) {
					
					if (p.getMoney() < 0 && end  == false) {
						end = true;
						game.End();
					}
					
					if (p.getRun()) {
						p.addEnergy(-0.1);
					}

					if (p == game.getActivePlayer()) {
						threadEtoile();
						if (p.getHunger() > 0) {
							p.addHunger(-lossHunger);
						}else {
							if (p.getLife() > 0) {
								p.addLife(-0.05);
							}
						}
						
						if (p.getHygiene() > 0) {
							p.addHygiene(-lossHygiene);
						}else {
							if (p.getLife() > 0) {
								p.addLife(-0.05);
							}
						}
						
						if (p.getLife() > 0 && p == game.getPlayer1()) {
							p.addAnger(-lossAnger);
						} else if (p == game.getPlayer1()) {
							if (p.getLife() > 0) {
								p.addLife(-0.05);
							}
						}
						if (p.getLife() < 0  &&  p.getMoney() > 0) {
							game.dead();
						}
						game.notifyView();
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception in ThreadBarres");
		}
	}
	
	public void stopThread() {
		active_thread = false;
	}
	
}
