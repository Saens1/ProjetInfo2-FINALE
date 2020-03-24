package Model;

import java.io.File;


import View.Sound;

public class Player extends Perso implements TimeObserver{  // Player est un observeur de Time
	private int[] inventory = new int[]{0,0,0,0,0,0};   // Liste d'inventaire, chaque type d' objet de l'inventaire est censé agir de la même manière,
														// c'est pourquoi seul des int sont stockés dans l'inventaire
	private double timeStars3 = 100;
	private double timeStars2 = 100;
	private double timeStars1 = 100;
	private double energy = 100;
	private double anger = 100;
	private double hunger = 100;
	private double hygiene = 100;
	private int numeroMap = 1;
	private int age;
	private Game game;
	private int secReference = 0;
	private boolean run;
	private static int money = 50;   // Mis en statique pour que tous les membres de la famille partage la même argent

	public Player(int x,int y, int couleur,Game game,int age) {
		super(x,y,couleur,0,0,game);
		this.age = age;
		this.game = game;
	}

	public void dead() {
		setLife(100); 
    	setPosX(11);
    	setPosY(19);
    	hunger = 100;
    	hygiene = 100;
    	anger = 100;
    	money -= 100;
	}
	//remet les différentes barres au max
	public void newGame() {
		setLife(100);
    	hunger = 100;
    	hygiene = 100;
    	anger = 100;
    	energy = 100;
	}
	
	public void updateChrono(int seconde) {
		if (seconde - secReference > 30) {    // Si 30 secondes sont passées, les personnages vieillissent d'un an
			secReference = seconde;
			age += 1;
			if (age > 40) {
				addWaitingTime(5);
			}	
		}
	}
	
	public void usePotionEnergy(PotionEnergy p) {
		if(inventory[4]>0) {
			addPotionEnergy(-1);
			p.activate();
		}				
	}
	public void usePotionLife(PotionLife p) {
		if(inventory[0]>0) {
			addPotionLife(-1);
			p.activate();
		}
	}
	public void useBomb(Bomb b) {
		if (inventory[1] > 0) {
		    if (game.Obstacle(getFrontX(),getFrontY()) != true) {
		    	 Bomb bomb = new Bomb(getFrontX(),getFrontY(),12,game);
		    	 game.addListeObject(bomb);
		    	 bomb.activate();
		    	 addBomb(-1);
		    }
		}
	}
	public void useGrenade(Grenade g) {
		if (inventory[2]>0) {
			int x = 0;
			int y = 0;
			
			switch (getDirection()) {
			case 0: x = 2; break;
			case 1 : y = -2; break;
			case 2 : x = -2; break;
			case 3 : y = 2; break;
			}
			
			if (!(game.Obstacle(getFrontX()+x,getFrontY()+y))) {
				Grenade grenade = new Grenade(getFrontX() + x,getFrontY() + y,40,game);
		    	game.addListeObject(grenade);
		    	grenade.activate();
		    	addGrenade(-1);
				
			}
		}
	}
	public void useTeleportation(int tpx, int tpy) {
		if (inventory[5]>0) {
			addTeleportation(-1);
			setPosX(tpx);
			setPosY(tpy);
			
			if (game.getSound()) {
				Sound Sound1 = new Sound(new File("Son/tp.wav")); 
				Sound1.playSound();
			}
		}
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Les méthodes GET
	
	public double getHunger() {
    	return hunger/100.0;
    }
	public double getHygiene() {
    	return hygiene/100.0;
    }
	public double getAnger() {
    	return anger/100.0;
    }
	public double getEnergy() {
    	return energy/100.0;
    }
	
	
	public double getTimeStars1() {
    	return timeStars1/100.0;
    }
	public double getTimeStars2() {
    	return timeStars2/100.0;
    }
	public double getTimeStars3() {
    	return timeStars3/100.0;
    }
	
	public boolean getRun() {
		return run;
	}
	public int getNumberPotionLife() {
		return inventory[0];
	}
	public int getNumberBomb() {
		return inventory[1];
		}
	public int getNumberGrenade() {
		return inventory[2];
	}
	public int getNumberMunition() {
		return inventory[3];
	}
	public int getNumberPotionEnergy(){
		return inventory[4];
	}
	public int getNumberTeleportation() {
		return inventory[5];
	}
	@SuppressWarnings("static-access")
	public int getMoney() {
		return this.money;
	}
	public int getNumeroMap() {
		return this.numeroMap;
	}
	public int getAge() {
		return age;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	// les méthodes SET
	
	public void setRun(boolean run) {
		this.run = run;
	}
	public void setHunger(double hunger) {
    	this.hunger= hunger;
    }
	public void setHygiene(double hygiene) {
    	this.hygiene= hygiene;
    }
	public void setAnger(double anger) {
    	this.anger= anger;
    }
	public void setEnergy(double energy) {
    	this.energy= energy;
    }
	
	public void setTeleportation(int numberTeleportation) {
		this.inventory[5] = numberTeleportation;
	}
	public void setPotionEnergy(int numberPotionEnergy) {
		this.inventory[4] = numberPotionEnergy;
	}
	public void setPotionLife(int numberPotionLife) {
		this.inventory[0] = numberPotionLife;
	}
	public void setGrenade(int numberGrenade) {
		this.inventory[2] = numberGrenade;
	}
	public void setBomb(int numberBomb) {
		this.inventory[1] = numberBomb;
	}
	public void setMunition(int numberMunition) {
		this.inventory[3] = numberMunition;
	}
	@SuppressWarnings("static-access")
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void setTimeStars1(double timeStars1) {
		this.timeStars1 = timeStars1 ;
		
    }
	public void setTimeStars2(double timeStars2) {
		this.timeStars2 = timeStars2 ;
		
    }
	public void setTimeStars3(double timeStars3) {
		this.timeStars3 = timeStars3 ;
    }
	public void setNumeroMap(int numeroMap) {
		this.numeroMap = numeroMap;
	}
	
///////////////////////////////////////////////////////////////////////////////////////
	
	// Les méthodes ADD					(elles ne sont pas nécessaires, les SET + GET sont suffisants mais ont été utilisés pour la clarté du code
	
	public void addHunger(double hunger) {
		this.hunger+= hunger;
	}
	public void addHygiene(double hygiene) {
		this.hygiene+= hygiene;
	}
	public void addAnger(double anger) {
		this.anger+= anger;
	}
	public void addEnergy(double energy) {
		this.energy+= energy;
	}
	
	public void addTeleportation(int numberTeleportation) {
		this.inventory[5] += numberTeleportation;
	}
	public void addPotionEnergy(int numberPotionEnergy) {
		this.inventory[4] += numberPotionEnergy;
	}
	public void addPotionLife(int numberPotionLife) {
		this.inventory[0] += numberPotionLife;
	}
	public void addGrenade(int numberGrenade) {
		this.inventory[2] += numberGrenade;
	}
	public void addBomb(int numberBomb) {
		this.inventory[1] += numberBomb;
	}
	public void addMunition(int numberMunition) {
		this.inventory[3] += numberMunition;
	}
	@SuppressWarnings("static-access")
	public  void addMoney(int money) {
		this.money += money;
	}
	
	public void addTimeStars1(int timeStars1) {
		this.timeStars1 += timeStars1 ;
	
	}
	public void addTimeStars2(int timeStars2) {
		this.timeStars2 += timeStars2 ;
	
	}
	public void addTimeStars3(int timeStars3) {
		this.timeStars3 += timeStars3 ;
	}

	
}
