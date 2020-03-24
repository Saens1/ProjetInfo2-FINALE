package Model;

import View.Window;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import View.Sound;
import View.Dead;
import View.EndGame;
import View.Jail;
import View.Pause;
import java.io.File;


public class Game implements DeletableObserver, TimeObserver {
    private ArrayList<GameObject> objects1 = new ArrayList<GameObject>();   // Chaque étage/map du jeu correspond à une liste d'objets
    private ArrayList<GameObject> objects2 = new ArrayList<GameObject>();
    private ArrayList<GameObject> objects3 = new ArrayList<GameObject>();
    private ArrayList<GameObject> objects4 = new ArrayList<GameObject>();
    private ArrayList<GameObject> objects5 = new ArrayList<GameObject>();
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();   // Cette liste sera en tout temps synchronisée avec la liste d'objet de la map affichée
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player active_player = null;   // joueur joué actuellement
    private Player player1 = null;
    private Player player2 = null;
    private Player player3 = null;
    private Window window;
    private int zoom = 1;
    private int numeroMap = 1;
    private int size;
    private int etoiles = 0;
    private long timeShoot = System.currentTimeMillis() ;
    private int tpx=20;
    private int tpy=20;
    private boolean run = false;
    private int bestScore = 0;
    
    private double lossAnger = 0;
    private double lossHunger = 0;
    private double lossHygiene = 0;
    
    private Grenade grenade;
    private Bomb bomb;
    private PotionLife potionLife;
    private PotionEnergy potionEnergy;
    
    private boolean sound;
    private boolean pause = false;
    private Sound Sound1;
    
    private static Game instance = null;


    private Game(Window window,boolean load,boolean sound,double loss, String name) {
        this.window = window;
        lossAnger = loss;
        lossHygiene = loss;
        lossHunger = loss;
        
        size = window.getMapSize();
        
        //Crée les différents players et les rajoute dans la map 1
        Player p1 = new Player(10, 10,13,this,40); player1 = p1;
        objects1.add(player1); players.add(player1);  
        Player p2 = new Player(11,11,49,this,35); player2 = p2;
        objects1.add(player2); players.add(player2);
        Player p3 = new Player(20,20,26,this,15); player3 = p3;
        objects1.add(player3); players.add(player3);
             
        
        for (Player player : players) {
        	new ThreadBarres(100,player,this, lossAnger,  lossHunger,  lossHygiene);  //thread des différents besoins pour chaque perso
        }
        active_player = player1;  
        window.setPlayer(player1,player2,player3,active_player,numeroMap);

        // Créé les blocs unbreakable qui limite les maps
        for (int i = 0; i < size; i++) {
            objects1.add(new BlockUnbreakable(i, 0,0));objects2.add(new BlockUnbreakable(i, 0,0));
            objects3.add(new BlockUnbreakable(i, 0,0));objects4.add(new BlockUnbreakable(i, 0,0));
            objects5.add(new BlockUnbreakable(i, 0,1));
            
            objects1.add(new BlockUnbreakable(0, i,0));objects2.add(new BlockUnbreakable(0, i,0));
            objects3.add(new BlockUnbreakable(0, i,0));objects4.add(new BlockUnbreakable(0, i,0));
            objects5.add(new BlockUnbreakable(0, i,1));
            
            objects1.add(new BlockUnbreakable(i, size - 1,0));objects2.add(new BlockUnbreakable(i, size - 1,0));
            objects3.add(new BlockUnbreakable(i, size - 1,0));objects4.add(new BlockUnbreakable(i, size - 1,0));
            objects5.add(new BlockUnbreakable(i, size - 1,1));
            
            objects1.add(new BlockUnbreakable(size - 1, i,0));objects2.add(new BlockUnbreakable(size - 1, i,0));
            objects3.add(new BlockUnbreakable(size - 1, i,0));objects4.add(new BlockUnbreakable(size - 1, i,0));  
            objects5.add(new BlockUnbreakable(size - 1, i,1));       
        }
        
        for (int i = 1;i<6;i++) {
        	MapCreator(i);             // Methode qui lit dans les doc txt et remplit les listes objects des 5 maps
        }

        window.setGameObjects(objects1,1);
        window.setName(name);
        recuperationBestScore();
        window.setBestScore(bestScore);
        
        objects = objects1;    // on commence le jeu avec la map 1 
        
        if (load) {  //lance la partie à la dernière sauvegarde
            recuperation();
            changeMap(active_player.getNumeroMap(),false);
            if (active_player.getTimeStars3() < 100) {
            	etoiles = 3;
            }else if (active_player.getTimeStars2() < 100) {
        		etoiles = 2;
        	}else if (active_player.getTimeStars1() < 100) {
        		etoiles = 1;
        	}
        }
        
        window.setAge(active_player.getAge());
        
        Chrono c = new Chrono(this);   // créé un objet Chrono qui est une observable et rajoute dans la liste des observeurs les players et la game
        c.addObserver(this);
        c.addObserver(player1);
        c.addObserver(player2);
        c.addObserver(player3); 
        
        if (sound == true) { //lance le son 
	        Sound1 = new Sound(new File("Son/son1.wav")); 
			Sound1.playLoop();
        } 
        notifyView();
    }
    
    // Singleton de Game, permet d'être sûr qu'on ne créera jamais qu'un seul objet de la classe Game
    public static Game getInstance(Window window,boolean load,boolean sound,double loss,String name) {
    	if(Game.instance == null) {
    		Game.instance = new Game(window,load,sound,loss,name);
    	}
    	return Game.instance;    	
    }
    
    //Créer la map en lisant des fichiers txt dans le dossier Cartes
    private void MapCreator(int numeroMap) {
    	try {
    		FileReader file;
    		file = new FileReader("Cartes/Map"+numeroMap+".txt");
    		BufferedReader br = new BufferedReader(file);
    		String line;   		
    		while ((line = br.readLine())!=null) {
    			String[] data = line.split(" ");
    			if (data.length > 1) {
    				MapReader(data,numeroMap);
    				}
    			}
    		br.close(); 		
    	}
    	catch(FileNotFoundException ex) {
            System.out.println("File not found");
        } catch(IOException ex) {
            System.out.println("Error in the file");
        }
    	notifyView();
    }
    
    //lit les lignes dans le fichier txt et créer l'objet en fonction des chiffres de la ligne et le rajoute dans la bonne liste d'object
    private void MapReader(String[] data,int numeroMap) {
    	if (numeroMap==1) {
    		objects = objects1;
    	}else if (numeroMap==2) {
    		objects = objects2;
    	}else if (numeroMap == 3) {
    		objects = objects3;
    	}else if (numeroMap == 4) {
    		objects = objects4;
    	}else if (numeroMap == 5) {
    		objects = objects5;
    	}
    	switch(Integer.parseInt(data[0])) {
    	case 0: objects.add(new BlockBreakable(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this));break;
    	case 1: objects.add(new BlockUnbreakable(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]))); break;
    	case 2 : objects.add(new PotionLife(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 3 : objects.add(new Grenade(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 5 : objects.add(new Bomb(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 4 : objects.add(new PotionEnergy(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 6 : objects.add(new Munition(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 7 : objects.add(new Teleportation(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 8 : objects.add(new Money(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 9 : objects.add(new Bed(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 10 : objects.add(new Kitchen(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 11 : objects.add(new WC(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 12 : objects.add(new Work(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 13 : objects.add(new Store(Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]),this)); break;
    	case 14 : objects.add(new Citizen(Integer.parseInt(data[1]), Integer.parseInt(data[2]),this,numeroMap)); break;
    	case 15 : objects.add(new Policier(Integer.parseInt(data[1]), Integer.parseInt(data[2]),this,numeroMap)); break;
    	}
    }
    
  //Permet de Zoomer sur la map et de passer en mode fenêtrage
    public void zoom() {
    	if (zoom == 1) {
    		window.setBlocSize(48);
    		zoom = 2;
    		window.setZoom(zoom);
    	}else if (zoom == 2) {
    		window.setBlocSize(30);
    		zoom = 1;
    		window.setZoom(zoom);	
    	}	
    }
    
    // Méthode utilisé pour gérer les clicks sur la Map 
    public void mouseEvent(int x, int y) {  
    	int distX = active_player.getPosX() - x;
    	int distY = active_player.getPosY() - y;
    	
        for (GameObject object : objects) {
            if (object.isAtPosition(x, y) && object instanceof Needs) {
            	if((-2< distX  && 2> distX)&&(-2<distY && 2> distY)){
            		((Needs) object).fillTheNeed();    // Si un objet needs est à proximité et qu'on clique dessus, il remplit le besoin (exemple : dormir)   
            		if (sound == true) {
            			Sound SoundRefill = new Sound(new File("Son/refill.wav"));
            			SoundRefill.playSound();
            		}
            	}         
            }
	        else if(object.isAtPosition(x, y) && object instanceof Activable && object instanceof Store){   
	        	if((-4< distX  && 4> distX)&&(-4<distY && 4> distY)){
	        		((Activable) object).activate();    //ouvre la Frame du store si on clique sur un comptoir à proximité du player
	        	}
	        }
	        if(Obstacle(x,y)==false) {   // Memorise le lieu du clique s'il n'y a pas d'obstacle pour la téléportation
	        	tpx=x;
	        	tpy=y;
	        }
        }
    }
    
    //lorsqu'un citoyen meurt, il laisse de l'argent
    public void citizenDead(int x,int y) {
    	objects.add(new Money(x,y,85,this));	
    }
    
  //lorsqu'un policier meurt, il laisse des munitions
    public void policierDead(int x,int y) {
    	objects.add(new Munition(x,y,39,this));
    }
    
    //permet de passer les portes pour changer de map en se positionnant dans les cercles verts
    public void passagePorte() {
    	if (numeroMap == 1 && ((active_player.getPosX() == 17 || active_player.getPosX() == 18)  && active_player.getPosY()==29)) {
    		active_player.setPosX(7); active_player.setPosY(5);
    		changeMap(5,false); 		
    	}else if (numeroMap == 2 && ((active_player.getPosX() == 16 ||active_player.getPosX() == 17) && active_player.getPosY()==29)) {
    		active_player.setPosX(18); active_player.setPosY(4);
    		changeMap(5,false); 		
    	}else if (numeroMap == 3 && ((active_player.getPosX() == 13 ||active_player.getPosX() == 14) && active_player.getPosY()==29)) {
    		active_player.setPosX(9); active_player.setPosY(23);
    		changeMap(5,false);	
    	}else if (numeroMap == 3 && ((active_player.getPosX() == 15 ||active_player.getPosX() == 16) && active_player.getPosY()==1)) {
    		active_player.setPosX(10); active_player.setPosY(18);
    		changeMap(5,false); 		
    	}else if (numeroMap == 4 && ((active_player.getPosX() == 13 ||active_player.getPosX() == 14) && active_player.getPosY()==29)) {
    		active_player.setPosX(20); active_player.setPosY(18);
    		changeMap(5,false); 		
    	}else if (numeroMap == 5) {
    		if ((active_player.getPosX() == 7 || active_player.getPosX()== 8)&& active_player.getPosY() == 5) {
    			active_player.setPosX(17); active_player.setPosY(29);
    			changeMap(1, false);
    		}else if ((active_player.getPosX() == 17 || active_player.getPosX()==18) && active_player.getPosY() == 4) {
    			active_player.setPosX(16); active_player.setPosY(29);
    			changeMap(2, false);
    		}else if (active_player.getPosX() == 9 && (active_player.getPosY() == 23 || active_player.getPosY()== 22)) {
    			active_player.setPosX(13); active_player.setPosY(29);
    			changeMap(3,false);
    		}else if (active_player.getPosX() == 10 && active_player.getPosY() == 18) {
    			active_player.setPosX(15); active_player.setPosY(1);
    			changeMap(3,false);
    		}else if (active_player.getPosX() == 20 && (active_player.getPosY() == 18 || active_player.getPosY()==17)) {
    			active_player.setPosX(13); active_player.setPosY(29);
    			changeMap(4,false);
    		}
    	}
    }
    
    //permet d'afficher la nouvelle map en synchronisant objects avec la bonne liste     
    public void changeMap(int numeroMap, boolean changePlayer) {
        objects.remove(active_player);
    	this.numeroMap= numeroMap;
    	active_player.setNumeroMap(numeroMap);
    	
    	switch(numeroMap) {
	    	case 1: window.setGameObjects(objects1,1); objects = objects1; break;
	    	case 2 : window.setGameObjects(objects2,2); objects = objects2; break;
	    	case 3 : window.setGameObjects(objects3, 3); objects = objects3; break;
	    	case 4 : window.setGameObjects(objects4, 4); objects = objects4; break;
	    	case 5 : window.setGameObjects(objects5, 5); objects = objects5; break;
    	}
    		
    	if(changePlayer == false) {    		    
    		objects.add(active_player);
    	}
    	notifyView();
    	}
    
    //permet de changer d'activePlayer
    //permet aussi de changer de map lorsqu'on change de player qui n'est pas dans la meme map que l'ancien active_player
    public void changeActivePlayer(int numeroPlayer) {  
    	active_player = players.get(numeroPlayer-1); 
    	window.setPlayer(player1,player2,player3,active_player,numeroMap);
    	window.setAge(active_player.getAge());
    	
    	if (active_player.getNumeroMap() != numeroMap) {
	    	switch(active_player.getNumeroMap()) {
		    	case 1: changeMap(1,true); break;
		    	case 2 : changeMap(2,true);break;
		    	case 3 : changeMap(3,true);break;
		    	case 4 : changeMap(4,true);break;
		    	case 5: changeMap(5,true);break;
	    	}
    	}
    	notifyView();
    }
    
    //vérifie si l'objet à la position x et y est un obstacle
    public boolean Obstacle(int x,int y) {
    	boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(x, y)) {
                obstacle = object.isObstacle();
            }
            if (obstacle == true) {
                break;
            }         
        }
    	return obstacle;
    }

    //permet de placer le player et de ramasser des items lorsqu'on marche dessus
    public void movePlayer(int x, int y) {
        int nextX = active_player.getPosX() + x;
        int nextY = active_player.getPosY() + y;
       
        boolean obstacle = Obstacle(nextX, nextY);
        active_player.rotate(x, y);
        if (obstacle == false) {
            active_player.move(x, y);
            Inventory bonus = null;
            for (GameObject object : objects) {
                if (object.isAtPosition(nextX, nextY) && object instanceof Inventory) {
                	 bonus = (Inventory) object;
                	 if (bonus.getCanWeCrush() == true) { // Pour éviter de pouvoir prendre des grenades ou des bombes quand on les a déposé et qu'elles vont exploser
                		 bonus.loot();   	
                	 }
                }
           }if (bonus != null) {
        	   bonus.crush();
           }
        }
        notifyView();
    	}
    
    public void shoot() {
    	if (System.currentTimeMillis() - timeShoot > 800) {  // On limite la vitesse de tire du player
	    	shoot(active_player);
	    	timeShoot = System.currentTimeMillis();
    	}
    }
    
    //permet de lancer les thread des balles lorsqu'on tire
    public void shoot(Perso tireur) {
    	int image = 0;
    	switch(tireur.getDirection()) {
	    	case 0 : image = 66; break;  // DROITE
	    	case 1 : image = 68; break;  // HAUT
	    	case 2 : image = 69; break;  // GAUCHE
	    	case 3 : image = 67; break;  // BAS
    	}
    	if (tireur instanceof Player) { 
    		if ( (((Player) tireur).getNumberMunition()) > 0) {	
    			((Player) tireur).addMunition(-1);
    			objects.add(new ThreadShoot(tireur.getPosX(),tireur.getPosY(),image,this,tireur));
    		}
    	}else {
    			objects.add(new ThreadShoot(tireur.getPosX(),tireur.getPosY(),image,this,tireur));
    	}
    }
    
    // Gère les interactions des tirs de balle
	public void actionShoot(ThreadShoot shoot,Perso tireur) {
	    for(GameObject object : objects){
	    	if(object.isAtPosition(shoot.getFrontX(),shoot.getFrontY())){
	    		if(object instanceof Player && tireur instanceof Policier) {
	    			((Player)object).addLife(-20);
	    			if (sound) {
		    			Sound Sound = new Sound(new File("Son/pain.wav"));
						Sound.playSound();
	    			} break; 			
	    		}
	    		else if((object instanceof Policier || object instanceof Citizen) && tireur instanceof Player) {
	    			if (tireur == player1) {
	    				if(player1.getAnger() < 96) {
	    					player1.addAnger(5);
		    			}
	    				((Perso)object).addLife(-20);
	    				if (etoiles < 3) {
				    		etoiles += 1; 
	    				} break;
		    		}else{
	    				jail(); break;
	    			}
	    		}
	    	}
	    }
	}
	
	
	public void actionAttack() {
		actionAttack(active_player);
	}
	
	//Gère les interactions des attaques au corps à corps
	public void actionAttack(Perso perso) {  
	    Activable aimedObject = null;
	    
		for(GameObject object : objects){
			if(object.isAtPosition(perso.getFrontX(),perso.getFrontY())){
			    if(object instanceof Activable){
			        aimedObject = (Activable) object;
			    }
			    if(object instanceof Perso) {
			    	((Perso)object).addLife(-5);
			    	if (etoiles < 3 && (object instanceof Policier || object instanceof Citizen) && active_player == player1) {
				    	etoiles += 1; 
				    	if(player1.getAnger() < 96) {
	    					player1.addAnger(5);
		    			}
			    		}
			    	}if ((active_player == player3 || active_player == player2) && numeroMap != 1) {
			    		jail(); break;
			    	}
			    }
			}
		
		if(aimedObject != null){
		    aimedObject.activate();
	        notifyView();
		} 
	}

    //Le player2 ou le player3 est amené en prison
    private void jail() {
    	changeMap(2, false);
    	active_player.setPosX(18);
    	active_player.setPosY(2);
    	etoiles = 0;
     	Jail j = new Jail(this);   // Créé une frame
    	j.setVisible(true);
    }
    
    //lorsque l'activePlayer meurt, il revit à l'hopital
    public void dead() {
    	if (sound == true) {
    		Sound Sound = new Sound(new File("Son/die.wav"));
    		Sound.playSound();
    	}
    	changeMap(3,false);
    	active_player.dead();
    	etoiles = 0;
    	Dead d = new Dead(this);   // Créé une frame
    	d.setVisible(true);
    }
     
    public boolean continueShoot(Movable cara) {
        boolean obstacle = false;
        obstacle = Obstacle(cara.getFrontX(),cara.getFrontY());
		return obstacle;     
    }
	
	public void run() {
		if (!(run)) {
			active_player.addWaitingTime(-60);
			run = true;
			active_player.setRun(run);
		}else {
			active_player.addWaitingTime(60);
			run = false;
			active_player.setRun(run);
		}
	}
	
	public void teleportation() {
		active_player.useTeleportation(tpx,tpy);	
	}
	
	public void usePotionEnergy() {
		active_player.usePotionEnergy(potionEnergy);				
		}

	public void usePotionLife() {
		active_player.usePotionLife(potionLife);		
		}
	
	 public void useBomb() {
		 active_player.useBomb(bomb);
	 	}
	    
	 public void useGrenade() {
		active_player.useGrenade(grenade);
	    }
	  
	 public void Pause() {
		 pause = true;
		 sound = false;
		 Sound1.couperMusique();
		 Pause p = new Pause(this);  // créé une frame
		 p.setVisible(true);
	 }	
	 
	 public void reStart() {
		 pause = false;
		 sound = true;
		 Sound1.playLoop();
	 }
	 
	 public void End() {
		 EndGame p = new EndGame(this);   // créé une frame
		 p.setVisible(true);
	 }
	 
	 //met à jour la fenetre d'affichage
	 public void notifyView() {
		 window.update();
	 }
	    
	@SuppressWarnings("unlikely-arg-type")
	synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
	    objects.remove(ps);
	    if (loot != null) {      
	        objects.addAll(loot);
	    }
	    notifyView();
	}
	
	public void updateChrono(int seconde) {    
		String secString = null;
		String minString = null;
		int min = seconde/60;
		int sec = seconde%60;
		if (sec < 10) {
			secString = "0"+ Integer.toString(sec);
		}else {
			secString = Integer.toString(sec);
		}
		if (min < 10) {
			minString = "0"+ Integer.toString(min);
		}else {
			minString = Integer.toString(min);
		}
		String time = minString + ":" + secString;
		window.setTime(time); 
		window.setAge(active_player.getAge());
		if (seconde > bestScore) {      // Lorsque le meilleur score est dépassé, on le met à jour dans le fichier et sur le status
			bestScore = seconde;
			backupBestScore();
			window.setBestScore(bestScore);
		}
	}
	// ferme la fenêtre du jeu et le jeu
	public void stop() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	// Les méthodes de sauvegarde et de récupération pour le meilleur score et pour toute la partie ensuite
			
	 private void recuperationBestScore() {
		try {
		FileReader file;
 		file = new FileReader("Sauvegarde/BestScore.txt");
 		BufferedReader br = new BufferedReader(file);
        String line;
        while ((line = br.readLine()) != null) {    //lit chaque ligne
            String[] data = line.split(" ");    //decoupe chaque ligne là ou il y a des espaces
            if (data.length!=0) {
                bestScore = Integer.parseInt(data[0]);
            }
        }
        br.close(); 		
		}
    	catch(FileNotFoundException ex) {
            System.out.println("File not found");
        } catch(IOException ex) {
            System.out.println("Error in the file");
        }
	 }
	 
	 private void backupBestScore(){
	        try{
	            File file =new File("Sauvegarde/BestScore.txt"); //
	            file.createNewFile();
	            FileWriter ffw =new FileWriter(file);
	            ffw.write(String.valueOf(bestScore));  // Ecrire une ligne dans le fichier BestScore.txt
	            ffw.close();  						// ferme le fichier après le traitement des données
	        } catch (Exception e) {
	        	System.out.println("Exception in backupBestScore");
	        }
	    }

	 //lit le fichier de sauvegarde 
	 private void recuperation() {
	    	try {
	    		FileReader file;
	    		file = new FileReader("Sauvegarde/sauvegarde.txt");
	    		BufferedReader br = new BufferedReader(file);
	    		String line;
	    		int i = 0;
	    		
	    		while ((line = br.readLine())!=null) {
	    			String[] data = line.split(" ");
	    			if (data.length == 2) {
	    				if (i < 18) {
	    					BackupReader(data,player1);
	    					System.out.println(i);
	    				}
	    				else if (i > 11 && i < 36) {
	    					BackupReader(data,player2);
	    				}
	    				else if (i > 23 && i < 54) {
	    					BackupReader(data,player3);
	    				}
	    				i+=1;
	    				}
	    			}
	    		br.close(); 		
	    	}
	    	catch(FileNotFoundException ex) {
	            System.out.println("File not found");
	        } catch(IOException ex) {
	            System.out.println("Error in the file");
	        }
	    	notifyView();
	    } 
	 
	 //set les différentes "valeurs" des perso en fonction des donnée du fichier de sauvegarde
	 private void BackupReader(String[] data,Player player) {	
	    	switch(Integer.parseInt(data[1])) {
	    	case 1: player.setLife(Double.parseDouble(data[0])*100); break;
	    	case 2: player.setEnergy(Double.parseDouble(data[0])*100); break;
	    	case 3: player.setHunger(Double.parseDouble(data[0])*100); break;
	    	case 4: player.setHygiene(Double.parseDouble(data[0])*100); break;
	    	case 5: player.setAnger(Double.parseDouble(data[0])*100); break;
	    	case 6: player.setMoney(Integer.parseInt(data[0])); break;
	    	case 7: player.setPotionLife(Integer.parseInt(data[0])); break;
	    	case 8: player.setPotionEnergy(Integer.parseInt(data[0])); break;
	    	case 9: player.setBomb(Integer.parseInt(data[0])); break;
	    	case 10: player.setGrenade(Integer.parseInt(data[0])); break;
	    	case 11: player.setTeleportation(Integer.parseInt(data[0])); break;
	    	case 12: player.setMunition(Integer.parseInt(data[0])); break;
	    	case 13: if (Integer.parseInt(data[0]) == 2) {
	    				objects2.add(player); objects1.remove(player); player.setNumeroMap(2);
	    			}else if (Integer.parseInt(data[0]) == 3) {
	    				objects3.add(player); objects1.remove(player); player.setNumeroMap(3);
	    			}else if (Integer.parseInt(data[0]) == 4) {
	    				objects4.add(player); objects1.remove(player); player.setNumeroMap(4);
	    			}else if (Integer.parseInt(data[0]) == 5) {
	    				objects5.add(player); objects1.remove(player); player.setNumeroMap(5);
	    			}
	    			; break;
	    	case 14 : player.setPosX(Integer.parseInt(data[0])); break;
	    	case 15 : player.setPosY(Integer.parseInt(data[0])); break;
	    	case 16 : player.setTimeStars1(Double.parseDouble(data[0])*100); break;
	    	case 17 : player.setTimeStars2(Double.parseDouble(data[0])*100); break;
	    	case 18 : player.setTimeStars3(Double.parseDouble(data[0])*100); break;
	    	}
	    }
	 
	 //écrit la sauvegarde dans un fichier txt
	 public void backup() {
		 try{
			 	File backupFile = new File("Sauvegarde/sauvegarde.txt"); 
	            backupFile.createNewFile();
	            FileWriter file = new FileWriter(backupFile);
	            file.close();   // ferme le fichier 
			 
			    FileWriter fstream = new FileWriter("Sauvegarde/sauvegarde.txt", true);
			    BufferedWriter out = new BufferedWriter(fstream);
			    for(Player player : players) {
			    	out.write(String.valueOf(player.getLife()) + " 1");  out.newLine();
			    	out.write(String.valueOf(player.getEnergy()) + " 2"); out.newLine();
				    out.write(String.valueOf(player.getHunger()) + " 3"); out.newLine();
				    out.write(String.valueOf(player.getHygiene()) + " 4"); out.newLine(); 
				    out.write(String.valueOf(player.getAnger()) + " 5"); out.newLine();
				    out.write(String.valueOf(player.getMoney())+ " 6"); out.newLine();
				    out.write(String.valueOf(player.getNumberPotionLife())+ " 7"); out.newLine();
				    out.write(String.valueOf(player.getNumberPotionEnergy())+ " 8"); out.newLine();
				    out.write(String.valueOf(player.getNumberBomb())+ " 9"); out.newLine();
				    out.write(String.valueOf(player.getNumberGrenade())+ " 10"); out.newLine();
				    out.write(String.valueOf(player.getNumberTeleportation())+ " 11"); out.newLine();
				    out.write(String.valueOf(player.getNumberMunition())+ " 12"); out.newLine();
				    out.write(String.valueOf(player.getNumeroMap())+ " 13"); out.newLine();  
				    out.write(String.valueOf(player.getPosX())+ " 14"); out.newLine();  
				    out.write(String.valueOf(player.getPosY())+ " 15"); out.newLine();  
				    out.write(String.valueOf(player.getTimeStars1())+ " 16"); out.newLine();  
				    out.write(String.valueOf(player.getTimeStars2())+ " 17"); out.newLine();  
				    out.write(String.valueOf(player.getTimeStars3())+ " 18"); out.newLine();  
	            }
			    out.close(); 
	        } catch (Exception e) {
	        	System.out.println("exception");
	        }
	 }
	 
	 
	 
	 /////////////////////////////////////////////////////////////////////////////////////////////////////////
	 	 
	 // Les méthodes GET & SET
	 
 
	 public ArrayList<GameObject> getListeObject() {
	    	return objects;
	 }
	    
	 public void setListeObject(ArrayList<GameObject> liste) {
		objects = liste;
	 	window.setGameObjects(objects,numeroMap); 
	 	notifyView();
	  }
	    
	 public void addListeObject(GameObject obj) {
	     objects.add(obj);
	 }
	 
	 public boolean getPause() {
		 return this.pause;
	 }
	 public void setEtoiles(int etoiles) {
		 this.etoiles=etoiles;
	 }
	 public int getEtoiles() {
		 return etoiles;
	 }
	 public void addEtoiles() {
		 this.etoiles +=1;
	 }
	 public boolean getSound() {
		 return this.sound;
	 }
	 public void setPotionEnergy(PotionEnergy potionEnergy) {
		 this.potionEnergy=potionEnergy;
	 }
	 public void setPotionLife(PotionLife potionLife) {
		 this.potionLife=potionLife;
	 }
	 public int getZoom() {
		 return this.zoom;
	 }
	 public Player getActivePlayer() {
		 return this.active_player;
	 }
	 public Player getPlayer1() {
		 return this.player1;
	 }
	 public Player getPlayer2() {
		 return this.player2;
	 }
	 public Player getPlayer3() {
		 return this.player3;
	 }
	 public Window getWindow() {
		 return this.window;
	 }
	 public int getNumeroMap() {
	    return this.numeroMap;
	 }
	    
}	

