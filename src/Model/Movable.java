package Model;



public abstract class Movable extends GameObject implements Directable, Runnable{
    private int direction = EAST;
    private int nextX;
    private int nextY;
    private Thread thread;
	private boolean thread_active = true;
	private Game game;
	private int BLOC_SIZE;
	private int step;
	private int waitingTime = 100;

    public Movable(int x, int y, int image,int stepX,int stepY,Game game) {
    	super(x, y, image,stepX,stepY);
    	this.game = game;
    }

    public void move(int nextX, int nextY) {
    	this.nextX = nextX;
    	this.nextY = nextY;
    	if (this instanceof Player || this instanceof Policier || this instanceof Citizen) {
    		thread_active = true;    		
        	this.thread = new Thread(this);
    		thread.start();
    	}
    	else {
    		setPosX(getPosX()+ nextX);    // pour les ThreadShoot qui ne profitent donc pas de l' "animation"
    		setPosY(getPosY()+ nextY);
    	}
    }
    
    //Thread qui gère l'affichage des deplacements, on enchaine 3 différentes images par direction pour une impression de marche et on demande à map d'afficher
    // l'image à plusieurs endroits intermédiaires entre 2 blocs via stepX et stepY
    public void run() {
    	synchronized(this) {
    	int[] Img_droite = new int[]{0,0,0}; 
		int[] Img_gauche = new int[]{0,0,0}; 
		int[] Img_devant = new int[]{0,0,0}; 
		int[] Img_derriere = new int[]{0,0,0}; 
    	if (this == game.getPlayer1()) {
    		Img_droite[0] = 23; Img_droite[1] = 24; Img_droite[2] = 22; 
    		Img_gauche[0] = 17; Img_gauche[1] = 18; Img_gauche[2] = 16; 
    		Img_devant[0] = 14; Img_devant[1] = 15; Img_devant[2] = 13; 
    		Img_derriere[0] = 20;Img_derriere[1] = 21;Img_derriere[2] = 19;
    	}else if (this == game.getPlayer3()) {
    		Img_droite[0] = 35; Img_droite[1] = 36;Img_droite[2] = 34;
    		Img_gauche[0] = 29; Img_gauche[1] = 30;Img_gauche[2] = 28;  
    		Img_devant[0] = 26; Img_devant[1]= 27; Img_devant[2] = 25; 
    		Img_derriere[0] = 32;Img_derriere[1] = 33;Img_derriere[2] = 31;
    	}else if (this == game.getPlayer2()) {
    		Img_droite[0] = 52; Img_droite[1] = 53;Img_droite[2] = 51;
    		Img_gauche[0] = 46;Img_gauche[1] = 47; Img_gauche[2] = 45;  
    		Img_devant[0] = 43; Img_devant[1] = 44; Img_devant[2] = 42; 
    		Img_derriere[0] = 49;Img_derriere[1] = 50;Img_derriere[2] = 48;
    	}else if (this instanceof Policier) {
    		Img_droite[0] = 64; Img_droite[1] = 65;Img_droite[2] = 63;
    		Img_gauche[0] = 58; Img_gauche[1] = 59;Img_gauche[2] = 57;
    		Img_devant[0] = 55; Img_devant[1] = 56;Img_devant[2] = 54;
    		Img_derriere[0] = 61;Img_derriere[1] = 62;Img_derriere[2] = 60;
    	}else if (this instanceof Citizen) {
    		Img_droite[0] = 79; Img_droite[1] = 80;Img_droite[2] = 81;
    		Img_gauche[0] = 73; Img_gauche[1] = 74;Img_gauche[2] = 75;
    		Img_devant[0] = 70; Img_devant[1] = 71;Img_devant[2] = 72;
    		Img_derriere[0] = 77;Img_derriere[1] = 76;Img_derriere[2] = 78;		
    	}
    	int iteration = 1;
   
    	try {
			int droite= 0;
			int gauche= 0;
			int bas= 0;
			int haut= 0;
			if (game.getZoom() == 1) {
				BLOC_SIZE = 30;
				step = 5;
			}else if (game.getZoom() == 2) {
				BLOC_SIZE = 48;
				step = 8;
			}
    		while(thread_active) {
    			iteration += 1;
    			Thread.sleep(waitingTime);
    			switch(nextX) {
    			case 1:
    				if (droite == 0 ) {
    				   droite+=1; 
    				   setImage(Img_droite[iteration%3]);
    				   setPosX(getPosX()+1);
    				   setStepX(-(BLOC_SIZE - step));
    				}
    				else if (getStepX() < -step ) {
    					setImage(Img_droite[iteration%3]);
    					setStepX( getStepX()+ step);
    				}else {
    					setImage(Img_droite[iteration%3]);
    					droite = 0; 
    					setStepX(0);
						thread_active = false;
    				}break;
    			case -1:
    				if (gauche == 0) {
    					gauche+=1;
    					setPosX(getPosX()-1);
    					setStepX(BLOC_SIZE - step);
    					setImage(Img_gauche[iteration%3]);
    					}
    				else if(getStepX() > step) {
    					setStepX( getStepX()- step);
    					setImage(Img_gauche[iteration%3]);
    				}
    				 else {
    					gauche = 0;
    					setStepX(0);
    					setImage(Img_gauche[iteration%3]);
						thread_active = false;
    				}break;
    			case 0:
    				if (this.nextY == 1) {
    					if (haut == 0) {
    						haut+=1;
    						setPosY(getPosY()+1);
    						setStepY(-(BLOC_SIZE - step));
    						setImage(Img_devant[iteration%3]);
    					}
    					else if(getStepY() < -step) {
    							setStepY( getStepY()+ step);
            					setImage(Img_devant[iteration%3]);
    					
    					}else {
    						setStepY(0);
    						setImage(Img_devant[iteration%3]);
    						thread_active = false;
    					}
    				}else {
    					if (bas == 0) {
    						bas +=1;
    						setPosY(getPosY()-1);
    						setStepY(BLOC_SIZE - step);
    						setImage(Img_derriere[iteration%3]);
    					}
    					if (getStepY() > step ) {
    						setStepY( getStepY()- step);
        					setImage(Img_derriere[iteration%3]);
    					}
    				
    					else {
    						setStepY(0);
    						setImage(Img_derriere[iteration%3]);
    						thread_active= false;
    					}
    				}break;
    			}
    			
    			game.notifyView();
    		}
    		
    	}catch(Exception e){
    		System.out.println("Exception in Movable");
    	}	
    	}
    }

    public void rotate(int x, int y) {
        if(x == 0 && y == -1)
            direction = NORTH;
        else if(x == 0 && y == 1)
            direction = SOUTH;
        else if(x == 1 && y == 0)
            direction = EAST;
        else if(x == -1 && y == 0)
            direction = WEST;
    }

   ////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public int getDirection() {
    return direction;
    }

    public int getFrontX() {
        int delta = 0;
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return getPosX() + delta;
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return getPosY() + delta;
    }
    public void addWaitingTime(int waitingTime) {
    	this.waitingTime += waitingTime;
    }
}
