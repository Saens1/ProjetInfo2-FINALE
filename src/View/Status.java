package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Player;
import Model.Policier;
import Controller.Mouse;


@SuppressWarnings("serial")
public class Status extends JPanel {//création du Panel qui gère les besoins,argent,différents perso
	private Player p1;
	private Player p2;
	private Player p3;
	private Player active_player;
	private String time = "0:0";
	private int age;
	private int numeroMap;
	@SuppressWarnings("unused")
	private Policier p;
	private int BAR_LENGTH = 150;
	private int BAR_WIDTH = 30;
	private int AVATAR_SIZE = 100;
	private String name = "joueur";
	private BufferedImage homer_pdp = null;
	private BufferedImage marge_pdp = null;
	private BufferedImage bart_pdp = null;
	private BufferedImage fond = null;
	private BufferedImage dollar = null;
    private BufferedImage grenade = null;
    private BufferedImage bombe = null;
    private BufferedImage heal = null;
    private BufferedImage munition = null;
    private BufferedImage potionEnergy = null;
    private BufferedImage teleportation = null;
    private BufferedImage etoile = null;
	private Mouse mouseController ;
	private String bestScore;

    public Status() {
        this.setPreferredSize(new Dimension(450, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
        addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent b) {
				int x = b.getX();
				int y = b.getY();				
				mouseController.statusEvent(x, y);
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
        
        try {
        	homer_pdp = ImageIO.read(new File("Images/homer_pdp.png"));
        	marge_pdp = ImageIO.read(new File("Images/marge_pdp.png"));
        	bart_pdp = ImageIO.read(new File("Images/bart_pdp.png"));
        	fond = ImageIO.read(new File("Images/fond.png"));
        	bombe = ImageIO.read(new File("Images/bombe.png"));
        	grenade = ImageIO.read(new File("Images/grenade.png"));
        	munition = ImageIO.read(new File("Images/munition.png"));
        	potionEnergy = ImageIO.read(new File("Images/potionEnergy.png"));
        	teleportation = ImageIO.read(new File("Images/teleportation.png"));
        	heal  = ImageIO.read(new File("Images/heal.png"));
        	dollar  = ImageIO.read(new File("Images/dollar.png"));
        	etoile = ImageIO.read(new File("Images/etoile.png"));
        }
        catch (IOException e){
        	
        }

    }
    
    
    
	public void paint(Graphics g) {  // affichage du status
		
		
		try {
			
		g.drawImage(fond, 0, 0,2000,1000, null);    // L'image en fond

		g.setColor(Color.BLACK);
		
		g.setFont(new Font("Times New Roman", Font.BOLD,25));    // BESTSCORE
		g.drawString("Best Score  "+ bestScore, 250, 900);
		
		g.setFont(new Font("Times New Roman", Font.BOLD,17));	// PRESS R TO RUN
		g.drawString("PRESS R TO RUN", 280, 500);
		g.drawString("PRESS S TO SAVE", 280, 525);			// PRESS S TO SAVE
		
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 30)); 		// NAME
		g.drawString(name, 10, 30);
		g.drawString("Time  " + time, 15 , 900);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 23));		// Age
		g.drawString("Age : "+ Integer.toString(age)+ " ans", 300, 110);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 40));    // Nom de la map
		if (numeroMap == 1) {
			g.setColor(Color.BLACK);
		    g.drawString("House", 150, 80);
		}else if (numeroMap == 2) {
			g.setColor(Color.BLACK);
		    g.drawString("Police Station", 90, 80);
		}else if (numeroMap == 3) {
			g.setColor(Color.BLACK);
		    g.drawString("Hospital / Shopping", 30, 80);
		}else if (numeroMap == 4) {
			g.setColor(Color.BLACK);
		    g.drawString("Bank", 150, 80);
		}else if (numeroMap == 5) {
			g.setColor(Color.BLACK);
		    g.drawString("City", 150, 80);
		}
		g.setFont(new Font("Times New Roman", Font.BOLD, 35));
		g.setColor(Color.BLACK);
	    g.drawString("Player : ", 30, 115);
	    
	    g.setFont(new Font("Gabriola", Font.BOLD, 30));    // Argent
	    int numberDollar = active_player.getMoney();
        g.drawString(Integer.toString(numberDollar), 345, 50);
		
        //////////////////////////////////////////////////////
        // AVATAR
        
		if (active_player == p1) {
			g.setColor(Color.YELLOW);
	        g.fillRect(30, 130, AVATAR_SIZE+20, AVATAR_SIZE+20);
	        g.drawImage(dollar,  400, 20,50,42, null);
			
		}else if (active_player == p2) {
			g.setColor(Color.YELLOW);
	        g.fillRect(160, 130, AVATAR_SIZE+20, AVATAR_SIZE+20);
	        g.drawImage(dollar,  400, 20,50,42, null);
		}else if (active_player == p3) {
			g.setColor(Color.YELLOW);
	        g.fillRect(290, 130, AVATAR_SIZE+20, AVATAR_SIZE+20);
	        g.drawImage(dollar,  400, 20,50,42, null);
		}

        g.drawImage(marge_pdp,  170, 140,AVATAR_SIZE,AVATAR_SIZE, null);
        g.drawImage(homer_pdp,  40, 140,AVATAR_SIZE,AVATAR_SIZE, null);
        g.drawImage(bart_pdp,  300, 140,AVATAR_SIZE,AVATAR_SIZE, null);

        
        g.setFont(new Font("Times New Roman", Font.BOLD, 35));
		g.setColor(Color.BLACK);
	    g.drawString("Inventory : ", 30, 290);
	    g.setFont(new Font("Gabriola", Font.BOLD, 20));
	    

     
	    /////////////////////////////////////////////////////
	    // inventaire
	   
	    g.setColor(Color.BLACK);
        g.fillRect(30, 310, 3, 163);
        g.fillRect(30, 310, 350, 3);
        g.fillRect(380, 310, 3, 163);
        g.fillRect(30, 390, 350, 3);
        g.fillRect(30, 470, 350, 3);
        g.fillRect(145, 310, 3, 163);
        g.fillRect(265, 310, 3, 163);
        
        g.drawImage(heal,  45, 317,70,70, null);
        int numberPotion = (int) active_player.getNumberPotionLife();
        g.setFont(new Font("Times New Roman", Font.BOLD, 35));
        g.drawString(Integer.toString(numberPotion), 107, 385);
        
        g.drawImage(bombe, 160, 395, 65, 65, null);
        int numberBomb = (int) active_player.getNumberBomb();
        g.drawString(Integer.toString(numberBomb), 225, 465);
        g.drawString("E", 225, 430);
        
        g.drawImage(grenade, 280, 323, 60, 60, null);
        int numberGrenade = (int) active_player.getNumberGrenade();
        g.drawString(Integer.toString(numberGrenade), 342, 385);
        g.drawString("G", 345, 345);
        
        g.drawImage(munition,  42, 395,70,70, null);
        int numberMunition = (int) active_player.getNumberMunition();
        g.drawString(Integer.toString(numberMunition), 107, 465);
        g.drawString("T", 105, 430);
        
        g.drawImage(potionEnergy,  150, 317,70,70, null);
        int numberPotionEnergy = (int) active_player.getNumberPotionEnergy();
        g.drawString(Integer.toString(numberPotionEnergy), 225, 385);
        
        
        g.drawImage(teleportation,  270, 395,70,70, null);
        int numberTeleportation = (int) active_player.getNumberTeleportation();
        g.drawString(Integer.toString(numberTeleportation), 340, 465);
        g.drawString("Z", 345, 430);
        
        
        ////////////////////////////////////////////////////////////////////////////////////
        // Etoile de recherche
        
        g.setFont(new Font("Gabriola", Font.BOLD, 20));
        int time1 = (int) Math.round(70*active_player.getTimeStars1());
        int time2 = (int) Math.round(70*active_player.getTimeStars2());
        int time3 = (int) Math.round(70*active_player.getTimeStars3());
        
        if(time1 < 70 || time2 < 70 || time3 < 70) {
        g.drawImage(etoile,  395, 560,33,33, null);
        g.setColor(Color.BLACK);
        g.setColor(Color.RED);
        g.fillRect(250, 570,70, 15);
        g.setColor(Color.GREEN);
        g.fillRect(250, 570, time1, 15);
        }
        if (time2 < 70 || time3 < 70) {
        g.drawImage(etoile,  395, 600,33,33, null); g.drawImage(etoile,  360, 600,33,33, null);
        g.setColor(Color.BLACK);
        g.setColor(Color.RED);
        g.fillRect(250, 610,70, 15);
        g.setColor(Color.GREEN);
        g.fillRect(250, 610, time2, 15);
        }
        if (time3 < 70) {
        g.drawImage(etoile,  395,640,33,33, null); g.drawImage(etoile,  360, 640,33,33, null);g.drawImage(etoile,325,640,33,33, null);
     
        g.setColor(Color.BLACK);
        g.setColor(Color.RED);
        g.fillRect(250, 650,70, 15);
        g.setColor(Color.GREEN);
        g.fillRect(250, 650, time3, 15);
        }
        
        
        //////////////////////////////////////////////////////////////////
        // Les jauges
        
        
        
      // Energy 
        g.setColor(Color.BLACK);
        g.drawString("Energy", 0, 520);
        g.setColor(Color.RED);
        g.fillRect(0, 530, BAR_LENGTH, BAR_WIDTH);  // (x,y, longueur, largeur)
        g.setColor(Color.GREEN);
        int length_ok = (int) Math.round(BAR_LENGTH*active_player.getEnergy());
        //System.out.println(p.getEnergy());// permet de modifier la taille du vert
        g.fillRect(0, 530, length_ok, BAR_WIDTH);
        
     // Vie 
        g.setColor(Color.BLACK);  // couleur
        g.drawString("Life", 0, 580);
        g.setColor(Color.RED);
        g.fillRect(0, 590, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(BAR_LENGTH*active_player.getLife());
        g.fillRect(0,590, length_ok, BAR_WIDTH);
        
     // Faim
       
        g.setColor(Color.BLACK);  // couleur
        g.drawString("Faim", 0, 640);
        g.setColor(Color.RED);
        g.fillRect(0, 650, 150, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(150*active_player.getHunger());
        g.fillRect(0, 650, length_ok, BAR_WIDTH);
        
     // Hygiène
        g.setColor(Color.BLACK);  // couleur
        g.drawString("Hygiène", 0, 700);
        g.setColor(Color.RED);
        g.fillRect(0, 710, 150, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(150*active_player.getHygiene());
        g.fillRect(0, 710, length_ok, BAR_WIDTH);
        
        if (active_player == p1) {
        	g.setColor(Color.BLACK);  // couleur
            g.drawString("Colère", 0, 760);
            g.setColor(Color.RED);
            g.fillRect(0, 770, 150, BAR_WIDTH);
            g.setColor(Color.GREEN);
            length_ok = (int) Math.round(150*p1.getAnger());
            g.fillRect(0, 770, length_ok, BAR_WIDTH);
        }
		}
		catch (Exception e){
			System.out.println("Exception in status");
		}
        
    }

    public void redraw() {
        this.repaint();
    }
	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	// Les méthodes SET

	public void setPlayer(Player p1,Player p2,Player p3,Player active_player,int numeroMap) {
		this.active_player = active_player;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.numeroMap = numeroMap;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setBestScore(int bestScore) {
		String secScore = null;
		String minScore = null;
		int minBestScore = bestScore/60;
		int secBestScore = bestScore%60;
		if (secBestScore < 10) {
			secScore = "0"+ Integer.toString(secBestScore);
		}else {
			secScore = Integer.toString(secBestScore);
		}
		if (minBestScore < 10) {
			minScore = "0"+ Integer.toString(minBestScore);
		}else {
			minScore = Integer.toString(minBestScore);
		}
			
	
		this.bestScore = minScore + ":" + secScore ;
	}
	
	public void setChrono(String time) {
		this.time = time;
	}
}