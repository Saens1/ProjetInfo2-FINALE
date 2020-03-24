package Model;

import java.util.Random;



public class ThreadPolicier implements Runnable{
	private Thread thread;
	private Policier p;
	private Game game;
	private int numeroMap;
	private long waitingTimeShoot;
	private boolean thread_active = true;
	private long time = System.currentTimeMillis();

	
	public ThreadPolicier(Policier p,Game game,int numeroMap) {
		this.p = p;
		this.game = game;
		this.numeroMap = numeroMap;
		waitingTimeShoot = System.currentTimeMillis();
		this.thread = new Thread(this);
		thread.start();	
	}
	
	
	private void dead() {              // au lieu de faire disparaitre ke policier, on le réanime autre part
		//thread_active = false;
		//p.delete();
		game.policierDead(p.getPosX(),p.getPosY());
		p.setLife(100);
    	p.setPosX(2);
    	p.setPosY(10);
	}
	
	
	public void run() {
		try {
			while(thread_active == true) {
			Thread.sleep(500);
			if (game.getPause() == false) {
			if (numeroMap == game.getNumeroMap()) {
				if (p.getLife() <= 0) {
					dead();
				}
				if (game.getEtoiles() > 0 && game.getActivePlayer() == game.getPlayer1()) {
				GoToPlayer();
				}
				else {
					balader();
				}
				game.notifyView();
				}
			}
			}
		}catch(Exception e) {
			System.out.println("Exception in ThreadPolicier");
		}
	}
	
	private void balader() {            
		Random rand = new Random();	
		
		if (System.currentTimeMillis() - time > 1500) {
			int direction = rand.nextInt(4);
			switch (direction) {
				case 0 : if (game.Obstacle(p.getPosX()+1,p.getPosY())==false) {
					 p.rotate(1, 0); p.move(1,0);  break;
				}
				case 1 : if (game.Obstacle(p.getPosX(),p.getPosY()-1)==false) {
					p.move(0,-1); p.rotate(0, -1);  break;
				}
				case 2 : if (game.Obstacle(p.getPosX()-1,p.getPosY())==false) {
					p.move(-1,0); p.rotate(-1, 0); break;
				}
				case 3 : if (game.Obstacle(p.getPosX(),p.getPosY()+1)==false) {
					p.move(0,1); p.rotate(0, 1); break;
				}
			} time = System.currentTimeMillis();					
		}
		
	}
	
	
	private void GoToPlayer() {		//Petite IA des policiers, lorsque le player a des étoiles, les policiers vont vers lui et tire s'il est dans la ligne de mir
		int waitingTime = 0;
		switch (game.getEtoiles()) {
		case 1 : waitingTime = 2000; break;   // plus il y a d'étoile, plus les policiers sont rapides
		case 2 : waitingTime = 1000; break;
		case 3 : waitingTime = 500; break;
		}
		if (System.currentTimeMillis() - time > waitingTime) {
			int deltaX0=game.getActivePlayer().getPosX() - p.getPosX(); 
	        int deltaY0=game.getActivePlayer().getPosY() - p.getPosY();
		        
		        if (deltaX0 == 0 && deltaY0 > 0 && System.currentTimeMillis()-waitingTimeShoot > 1000) {
		        	p.rotate(0, 1); game.shoot(p);
		        	waitingTimeShoot = System.currentTimeMillis();
		        }else if (deltaY0 == 0 && deltaX0 > 0 && System.currentTimeMillis()-waitingTimeShoot > 1000) {
		           	p.rotate(1, 0); game.shoot(p);
		        	waitingTimeShoot = System.currentTimeMillis();
		        }else if (deltaX0 == 0 && deltaY0 < 0 && System.currentTimeMillis()-waitingTimeShoot > 1000) {
		           	p.rotate(0, -1); game.shoot(p);
		        	waitingTimeShoot = System.currentTimeMillis();
		        }else if (deltaY0 == 0 && deltaX0 < 0 && System.currentTimeMillis()-waitingTimeShoot > 1000) {
		           	p.rotate(-1, 0); game.shoot(p);
		        	waitingTimeShoot = System.currentTimeMillis();
		        }
		    
					
			  time = System.currentTimeMillis();
			
        int x = 0;
        int y = 0;
        
        if (deltaX0 > deltaY0) {
        	if (deltaX0 == 0  && game.Obstacle(p.getPosX(),p.getPosY()-1)==false) {
        		x = 0; y = -1;
        	}
        	else if (deltaX0 > 0) {
        		if (game.Obstacle(p.getPosX()+1,p.getPosY())==false) {
        			x = 1; y = 0;
        		}else if (deltaY0 < 0 && game.Obstacle(p.getPosX(),p.getPosY()-1)==false) {
        			x = 0; y = -1;
        		}else if (deltaY0 > 0 && game.Obstacle(p.getPosX(),p.getPosY()+1)==false) {
        			x = 0; y = 1;
        		}
        	}else if (deltaX0 < 0){
        		if (game.Obstacle(p.getPosX()-1,p.getPosY())==false) {
        			x = -1; y = 0;
        		}else if (deltaY0 < 0 && game.Obstacle(p.getPosX(),p.getPosY()-1)==false) {
        			x = 0; y = -1;
        		}
        
        	}
        }else if (deltaY0 > deltaX0) {
        	if (deltaY0 == 0 && game.Obstacle(p.getPosX()-1,p.getPosY())==false ) {
        		x = -1; y = 0;
        	}
        	else if (deltaY0 > 0) {
        		if (game.Obstacle(p.getPosX(),p.getPosY()+1)==false) {
        			x = 0; y = 1;
        		}else if (deltaX0 < 0 && game.Obstacle(p.getPosX()-1,p.getPosY())==false) {
        			x = -1; y = 0;
        		}else if (deltaX0 > 0 && game.Obstacle(p.getPosX(),p.getPosY()+1)==false) {
        			x = 1; y = 0;
        		}
        	}else if (deltaY0 < 0) {
        		if (game.Obstacle(p.getPosX(),p.getPosY()-1)==false) {
        			x = 0; y = -1;
        		}else if (deltaX0 < 0 && game.Obstacle(p.getPosX()-1,p.getPosY())==false) {
        			x = -1; y = 0;
        		}
        	}
        }else {
        	if (deltaX0 > 0) {
        		if (game.Obstacle(p.getPosX()+1,p.getPosY()+1)==false) {
        			x = 1; y = 0;
        		}else if (game.Obstacle(p.getPosX(),p.getPosY()+1)==false) {
        			x = 0; y = 1;
        		}
        	}else if (deltaX0 <0) {
        		if (game.Obstacle(p.getPosX()-1,p.getPosY()+1)==false) {
        			x = -1; y = 0;
        		}else if (game.Obstacle(p.getPosX(),p.getPosY()-1)==false) {
        			x = 0; y = -1;
        		} 
        	}
        }p.move(x,y);	
        
		}
	}
	
}