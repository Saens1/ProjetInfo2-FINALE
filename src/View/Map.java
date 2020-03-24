package View;

import Model.GameObject;
import Model.Perso;
import Model.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.Mouse;

@SuppressWarnings("serial")
public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    private int zoom = 1;
    private int MAP_SIZE = 0;
    private int BLOC_SIZE = 0;
    private Mouse mouseController = null;
    @SuppressWarnings("unused")
	private Player p1;
	@SuppressWarnings("unused")
	private Player p2;
	@SuppressWarnings("unused")
	private Player p3;
	private Player active_player;
	private int numeroMap;
	private int depX;
	private int depY;
	private int stepMainX;
	private int stepMainY;
    private BufferedImage feu = null;
    private BufferedImage mechant = null;
    private BufferedImage lit1 = null;
    private BufferedImage lit2 = null;
    private BufferedImage parquet = null;
    private BufferedImage mur = null;
    private BufferedImage porte_ferme = null;
    private BufferedImage porte_ouverte = null;
    private BufferedImage plante = null;
    private BufferedImage bain = null;
    private BufferedImage armoire = null;
    private BufferedImage four = null;
    private BufferedImage bureau = null;
    private BufferedImage miroir = null;
    private BufferedImage armoirecuisine = null;
    private BufferedImage cercle_vert = null;
    private BufferedImage murgris = null;
    private BufferedImage sol_police = null;
    private BufferedImage ville = null;
    private BufferedImage money = null;
    private BufferedImage grenade = null;
    private BufferedImage bombe = null;
    private BufferedImage heal = null;
    private BufferedImage munition = null;
    private BufferedImage potionEnergy = null;
    private BufferedImage teleportation = null;
    
    private BufferedImage homer_devant_1 = null;private BufferedImage homer_devant_2 = null;private BufferedImage homer_devant_3 = null;
    private BufferedImage homer_droite_1 = null;private BufferedImage homer_droite_2 = null;private BufferedImage homer_droite_3 = null;
    private BufferedImage homer_derriere_1 = null;private BufferedImage homer_derriere_2 = null;private BufferedImage homer_derriere_3 = null;
    private BufferedImage homer_gauche_1 = null;private BufferedImage homer_gauche_2 = null;private BufferedImage homer_gauche_3 = null;
    
    private BufferedImage Bart_devant_1 = null;private BufferedImage Bart_devant_2 = null;private BufferedImage Bart_devant_3 = null;
    private BufferedImage Bart_droite_1 = null;private BufferedImage Bart_droite_2 = null;private BufferedImage Bart_droite_3 = null;
    private BufferedImage Bart_derriere_1 = null;private BufferedImage Bart_derriere_2 = null;private BufferedImage Bart_derriere_3 = null;
    private BufferedImage Bart_gauche_1 = null;private BufferedImage Bart_gauche_2 = null;private BufferedImage Bart_gauche_3 = null;
    
    private BufferedImage Marge_devant_1 = null;private BufferedImage Marge_devant_2 = null;private BufferedImage Marge_devant_3 = null;
    private BufferedImage Marge_droite_1 = null;private BufferedImage Marge_droite_2 = null;private BufferedImage Marge_droite_3 = null;
    private BufferedImage Marge_derriere_1 = null;private BufferedImage Marge_derriere_2 = null;private BufferedImage Marge_derriere_3 = null;
    private BufferedImage Marge_gauche_1 = null;private BufferedImage Marge_gauche_2 = null;private BufferedImage Marge_gauche_3 = null;
    
    private BufferedImage Policier_devant_1 = null;private BufferedImage Policier_devant_2 = null;private BufferedImage Policier_devant_3 = null;
    private BufferedImage Policier_droite_1 = null;private BufferedImage Policier_droite_2 = null;private BufferedImage Policier_droite_3 = null;
    private BufferedImage Policier_derriere_1 = null;private BufferedImage Policier_derriere_2 = null;private BufferedImage Policier_derriere_3 = null;
    private BufferedImage Policier_gauche_1 = null;private BufferedImage Policier_gauche_2 = null;private BufferedImage Policier_gauche_3 = null;
    
    private BufferedImage Flanders_devant_1 = null;private BufferedImage Flanders_devant_2 = null;private BufferedImage Flanders_devant_3 = null;
    private BufferedImage Flanders_droite_1 = null;private BufferedImage Flanders_droite_2 = null;private BufferedImage Flanders_droite_3 = null;
    private BufferedImage Flanders_derriere_1 = null;private BufferedImage Flanders_derriere_2 = null;private BufferedImage Flanders_derriere_3 = null;
    private BufferedImage Flanders_gauche_1 = null;private BufferedImage Flanders_gauche_2 = null;private BufferedImage Flanders_gauche_3 = null;
    
    private BufferedImage munition_gauche = null;private BufferedImage munition_droite = null;
    private BufferedImage munition_haut = null; private BufferedImage munition_bas = null;
    
    private BufferedImage barreau = null;
    private BufferedImage comptoir = null;
    private BufferedImage tablech = null;
    private BufferedImage pizza = null;
    private BufferedImage wc = null;
    private BufferedImage hospi = null;
    private BufferedImage hospi2 = null;
    private BufferedImage siege = null;
    private BufferedImage prison = null;
    private BufferedImage prison1 = null;
    private BufferedImage tele = null;
    private BufferedImage sol_bank = null;
    private BufferedImage sol_shop = null;
    private BufferedImage vendeur = null;
    
    public Map(int MAP_SIZE, int BLOC_SIZE) {
    	this.BLOC_SIZE = BLOC_SIZE;
    	this.MAP_SIZE = MAP_SIZE;
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(30*31, 30*31));
        addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/BLOC_SIZE;
				int y = e.getY()/BLOC_SIZE;
				mouseController.mapEvent(x, y);
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
        
        try {
        	sol_shop = ImageIO.read(new File("Images/sol_shopp.PNG"));
        	feu = ImageIO.read(new File("Images/boule.png"));
        	mechant = ImageIO.read(new File("Images/mechant.png"));
        	lit1 = ImageIO.read(new File("Images/bed1.png"));
        	lit2 = ImageIO.read(new File("Images/bed2.png"));
        	parquet = ImageIO.read(new File("Images/parquet.png"));
        	mur = ImageIO.read(new File("Images/mur.png"));
        	porte_ferme = ImageIO.read(new File("Images/porterferme.png"));
        	porte_ouverte = ImageIO.read(new File("Images/porteouverte.png"));
        	plante = ImageIO.read(new File("Images/plante.png"));
        	bain = ImageIO.read(new File("Images/bain.png"));
        	armoire = ImageIO.read(new File("Images/armoire.png"));
        	four = ImageIO.read(new File("Images/four.png"));
        	bureau = ImageIO.read(new File("Images/bureau.png"));
        	miroir = ImageIO.read(new File("Images/miroir.png"));
        	armoirecuisine = ImageIO.read(new File("Images/armoirecuisine.png"));
        	cercle_vert = ImageIO.read(new File("Images/cercle_vert.png"));
        	murgris = ImageIO.read(new File("Images/murgris.png"));
        	sol_police = ImageIO.read(new File("Images/sol_police.png"));
            ville = ImageIO.read(new File("Images/ville.png"));
            money = ImageIO.read(new File("Images/money.png"));
        	bombe = ImageIO.read(new File("Images/bombe.png"));
        	grenade = ImageIO.read(new File("Images/grenade.png"));
        	munition = ImageIO.read(new File("Images/munition.png"));
        	potionEnergy = ImageIO.read(new File("Images/potionEnergy.png"));
        	teleportation = ImageIO.read(new File("Images/teleportation.png"));
        	heal  = ImageIO.read(new File("Images/heal.png"));
        	
        	homer_devant_1 = ImageIO.read(new File("Images/Homer/homer_devant_1.png"));
        	homer_devant_2 = ImageIO.read(new File("Images/Homer/homer_devant_2.png"));
        	homer_devant_3  = ImageIO.read(new File("Images/Homer/homer_devant_3.png"));
        	homer_derriere_1 = ImageIO.read(new File("Images/Homer/homer_derriere_1.png"));
        	homer_derriere_2 = ImageIO.read(new File("Images/Homer/homer_derriere_2.png"));
        	homer_derriere_3  = ImageIO.read(new File("Images/Homer/homer_derriere_3.png"));
        	homer_gauche_1 = ImageIO.read(new File("Images/Homer/homer_gauche_1.png"));
        	homer_gauche_2 = ImageIO.read(new File("Images/Homer/homer_gauche_2.png"));
        	homer_gauche_3  = ImageIO.read(new File("Images/Homer/homer_gauche_3.png"));
        	homer_droite_1 = ImageIO.read(new File("Images/Homer/homer_droite_1.png"));
        	homer_droite_2 = ImageIO.read(new File("Images/Homer/homer_droite_2.png"));
        	homer_droite_3  = ImageIO.read(new File("Images/Homer/homer_droite_3.png"));
        	
        	Marge_devant_1 = ImageIO.read(new File("Images/Marge/Marge_avant_1.png"));
        	Marge_devant_2 = ImageIO.read(new File("Images/Marge/Marge_avant_2.png"));
        	Marge_devant_3  = ImageIO.read(new File("Images/Marge/Marge_avant_3.png"));
        	Marge_derriere_1 = ImageIO.read(new File("Images/Marge/Marge_arriere_1.png"));
        	Marge_derriere_2 = ImageIO.read(new File("Images/Marge/Marge_arriere_2.png"));
        	Marge_derriere_3  = ImageIO.read(new File("Images/Marge/Marge_arriere_3.png"));
        	Marge_gauche_1 = ImageIO.read(new File("Images/Marge/Marge_gauche_1.png"));
        	Marge_gauche_2 = ImageIO.read(new File("Images/Marge/Marge_gauche_2.png"));
        	Marge_gauche_3  = ImageIO.read(new File("Images/Marge/Marge_gauche_3.png"));
        	Marge_droite_1 = ImageIO.read(new File("Images/Marge/Marge_droite_1.png"));
        	Marge_droite_2 = ImageIO.read(new File("Images/Marge/Marge_droite_2.png"));
        	Marge_droite_3  = ImageIO.read(new File("Images/Marge/Marge_droite_3.png"));
        	
        	
        	Bart_devant_1 = ImageIO.read(new File("Images/Bart/Bart_devant_1.png"));
        	Bart_devant_2 = ImageIO.read(new File("Images/Bart/Bart_devant_2.png"));
        	Bart_devant_3  = ImageIO.read(new File("Images/Bart/Bart_devant_3.png"));
        	Bart_derriere_1 = ImageIO.read(new File("Images/Bart/Bart_derriere_1.png"));
        	Bart_derriere_2 = ImageIO.read(new File("Images/Bart/Bart_derriere_2.png"));
        	Bart_derriere_3  = ImageIO.read(new File("Images/Bart/Bart_derriere_3.png"));
        	Bart_gauche_1 = ImageIO.read(new File("Images/Bart/Bart_gauche_1.png"));
        	Bart_gauche_2 = ImageIO.read(new File("Images/Bart/Bart_gauche_2.png"));
        	Bart_gauche_3  = ImageIO.read(new File("Images/Bart/Bart_gauche_3.png"));
        	Bart_droite_1 = ImageIO.read(new File("Images/Bart/Bart_droite_1.png"));
        	Bart_droite_2 = ImageIO.read(new File("Images/Bart/Bart_droite_2.png"));
        	Bart_droite_3  = ImageIO.read(new File("Images/Bart/Bart_droite_3.png"));
        	
        	Policier_devant_1 = ImageIO.read(new File("Images/Policier/policier_devant_1.png"));
        	Policier_devant_2 = ImageIO.read(new File("Images/Policier/policier_devant_2.png"));
        	Policier_devant_3  = ImageIO.read(new File("Images/Policier/policier_devant_3.png"));
        	Policier_derriere_1 = ImageIO.read(new File("Images/Policier/policier_derriere_1.png"));
        	Policier_derriere_2 = ImageIO.read(new File("Images/Policier/policier_derriere_2.png"));
        	Policier_derriere_3  = ImageIO.read(new File("Images/Policier/policier_derriere_3.png"));
        	Policier_gauche_1 = ImageIO.read(new File("Images/Policier/policier_gauche_1.png"));
        	Policier_gauche_2 = ImageIO.read(new File("Images/Policier/policier_gauche_2.png"));
        	Policier_gauche_3  = ImageIO.read(new File("Images/Policier/policier_gauche_3.png"));
        	Policier_droite_1 = ImageIO.read(new File("Images/Policier/policier_droite_1.png"));
        	Policier_droite_2 = ImageIO.read(new File("Images/Policier/policier_droite_2.png"));
        	Policier_droite_3  = ImageIO.read(new File("Images/Policier/policier_droite_3.png"));
        	
        	Flanders_devant_1 = ImageIO.read(new File("Images/Flanders/Flanders_devant_1.png"));
        	Flanders_devant_2 = ImageIO.read(new File("Images/Flanders/Flanders_devant_2.png"));
        	Flanders_devant_3  = ImageIO.read(new File("Images/Flanders/Flanders_devant_3.png"));
        	Flanders_derriere_1 = ImageIO.read(new File("Images/Flanders/Flanders_derriere_1.png"));
        	Flanders_derriere_2 = ImageIO.read(new File("Images/Flanders/Flanders_derriere_2.png"));
        	Flanders_derriere_3  = ImageIO.read(new File("Images/Flanders/Flanders_derriere_3.png"));
        	Flanders_gauche_1 = ImageIO.read(new File("Images/Flanders/Flanders_gauche_1.png"));
        	Flanders_gauche_2 = ImageIO.read(new File("Images/Flanders/Flanders_gauche_2.png"));
        	Flanders_gauche_3  = ImageIO.read(new File("Images/Flanders/Flanders_gauche_3.png"));
        	Flanders_droite_1 = ImageIO.read(new File("Images/Flanders/Flanders_droite_1.png"));
        	Flanders_droite_2 = ImageIO.read(new File("Images/Flanders/Flanders_droite_2.png"));
        	Flanders_droite_3  = ImageIO.read(new File("Images/Flanders/Flanders_droite_3.png"));
        	
        	munition_haut  = ImageIO.read(new File("Images/Munition/munition_haut.png"));
        	munition_bas  = ImageIO.read(new File("Images/Munition/munition_bas.png"));
        	munition_droite  = ImageIO.read(new File("Images/Munition/munition_droite.png"));
        	munition_gauche  = ImageIO.read(new File("Images/Munition/munition_gauche.png"));
        	
        	barreau  = ImageIO.read(new File("Images/barreau.png"));
        	comptoir  = ImageIO.read(new File("Images/comptoir.png"));
        	tablech  = ImageIO.read(new File("Images/tablech.png"));
        	pizza  = ImageIO.read(new File("Images/pizza.png"));
        	wc  = ImageIO.read(new File("Images/wc.png"));
        	hospi  = ImageIO.read(new File("Images/hospi.png"));
        	hospi2  = ImageIO.read(new File("Images/hospi2.png"));
        	siege  = ImageIO.read(new File("Images/siege.png"));
        	prison  = ImageIO.read(new File("Images/prison.png"));
        	prison1  = ImageIO.read(new File("Images/prison1.png"));
        	tele  = ImageIO.read(new File("Images/tele.png"));
        	sol_bank = ImageIO.read(new File("Images/sol_bank.png"));
        	vendeur = ImageIO.read(new File("Images/vendeur.png"));
        	
            	
        }
        catch (IOException e) {
        	System.out.println("exception lecture image Map");
        }
    }   

    public void paint(Graphics g) {
    	if (zoom == 1) {
    		depX = 0; 
    		depY = 0;
    		stepMainX = 0;
    		stepMainY = 0;
    	}else if (zoom == 2) {
    	depX = active_player.getPosX()-10; 
		depY = active_player.getPosY()-10;
		stepMainX = active_player.getStepX();
		stepMainY = active_player.getStepY();
    	}
    	if (numeroMap == 1) {
        	//g.drawImage(lit1, 7*BLOC_SIZE,4*BLOC_SIZE,19*BLOC_SIZE,17*BLOC_SIZE, null);
        	for (int i = 0; i < MAP_SIZE; i++) { 
                for (int j = 0; j < MAP_SIZE; j++) {
                	int x = i;
                    int y = j;
                    g.drawImage(parquet,(x-depX)*BLOC_SIZE-12 -stepMainX, (y-depY)*BLOC_SIZE -stepMainY,BLOC_SIZE,BLOC_SIZE, null);
                }
        	}
        	g.drawImage(lit1, BLOC_SIZE*3 -depX*BLOC_SIZE -stepMainX, BLOC_SIZE-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*4, null);
        	g.drawImage(lit2,  BLOC_SIZE*26-33 -depX*BLOC_SIZE -stepMainX, BLOC_SIZE-depY*BLOC_SIZE -stepMainY,BLOC_SIZE+65,BLOC_SIZE+75, null);
        	g.drawImage(bain,  BLOC_SIZE*14 -depX*BLOC_SIZE -stepMainX, BLOC_SIZE-28-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*4+10,BLOC_SIZE*3+25, null);
        	g.drawImage(armoire,  BLOC_SIZE -depX*BLOC_SIZE -stepMainX, BLOC_SIZE-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE*2, null);
        	g.drawImage(four,  BLOC_SIZE*28 -depX*BLOC_SIZE -stepMainX, BLOC_SIZE*17-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE*2, null);
        	g.drawImage(bureau, BLOC_SIZE*13 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*14-depY*BLOC_SIZE -stepMainY,BLOC_SIZE,BLOC_SIZE*2,null);
        	g.drawImage(miroir, BLOC_SIZE*10 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE-depY*BLOC_SIZE -stepMainY,BLOC_SIZE,BLOC_SIZE*2,null);
        	g.drawImage(armoirecuisine, BLOC_SIZE*24 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*14-15-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2+10,null);
        	g.drawImage(cercle_vert, BLOC_SIZE*17 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*29-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE,null);  
        	
        	g.drawImage(tablech, BLOC_SIZE*5 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*25-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
        	g.drawImage(wc, BLOC_SIZE*20 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*1,BLOC_SIZE*2,null);
        	g.drawImage(tele, BLOC_SIZE*6 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*22-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE*2,null);
        } else if (numeroMap == 2) {
        	//g.drawImage(lit1, 7*BLOC_SIZE,4*BLOC_SIZE,19*BLOC_SIZE,17*BLOC_SIZE, null);
        	for (int i = 0; i < MAP_SIZE; i++) { 
                for (int j = 0; j < MAP_SIZE; j++) {
                	int x = i;
                    int y = j;
                    g.drawImage(sol_police,  x*BLOC_SIZE-12-depX*BLOC_SIZE -stepMainX, y*BLOC_SIZE-depY*BLOC_SIZE -stepMainY,BLOC_SIZE,BLOC_SIZE, null);
                }
        	}
        	g.drawImage(cercle_vert, BLOC_SIZE*16-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*29-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE,null);    
        	g.drawImage(comptoir, BLOC_SIZE*23-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*21-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*4,BLOC_SIZE*2,null);
        	g.drawImage(tablech, BLOC_SIZE*5-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*25-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
        	g.drawImage(wc, BLOC_SIZE*24-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*14-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*1,BLOC_SIZE*2,null);
        	g.drawImage(wc, BLOC_SIZE*27-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*14-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*1,BLOC_SIZE*2,null);
        	g.drawImage(prison, BLOC_SIZE*11-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*1,BLOC_SIZE*2,null);
        	g.drawImage(prison, BLOC_SIZE*21-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*1,BLOC_SIZE*2,null);
        	g.drawImage(prison, BLOC_SIZE*1-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*1,BLOC_SIZE*2,null);
        }else if (numeroMap == 3) {
        	for (int i = 0; i < MAP_SIZE; i++) { 
                for (int j = 0; j < MAP_SIZE; j++) {
                	int x = i;
                    int y = j;
                    g.drawImage(sol_shop,  (x-depX)*BLOC_SIZE-12 -stepMainX, (y-depY)*BLOC_SIZE - stepMainY,BLOC_SIZE,BLOC_SIZE, null);
                }
        	}
        	g.drawImage(cercle_vert, BLOC_SIZE*13-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*29-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE,null);  
        	g.drawImage(cercle_vert, BLOC_SIZE*15-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE,null);  
        	g.drawImage(comptoir, BLOC_SIZE*3-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*3-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*4,BLOC_SIZE*2,null);
        	g.drawImage(comptoir, BLOC_SIZE*23-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*3-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*4,BLOC_SIZE*2,null);
        	g.drawImage(comptoir, BLOC_SIZE*20-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*27-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*4,BLOC_SIZE*2,null);
        	g.drawImage(tablech, BLOC_SIZE*5-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*25-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
        	g.drawImage(pizza, BLOC_SIZE*8-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE*3,null);
        	g.drawImage(armoirecuisine, BLOC_SIZE*20-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*1-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2+10,null);
        	g.drawImage(hospi, BLOC_SIZE*1-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*15-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
        	g.drawImage(hospi, BLOC_SIZE*4-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*15-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
        	g.drawImage(hospi2, BLOC_SIZE*13-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*20-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE*2,null);
        	g.drawImage(hospi2, BLOC_SIZE*13-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*18-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE*2,null);
        	g.drawImage(tablech, BLOC_SIZE*2-depX*BLOC_SIZE -stepMainX,BLOC_SIZE*8-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
    	}else if (numeroMap == 4) {
    		for (int i = 0; i < MAP_SIZE; i++) { 
                for (int j = 0; j < MAP_SIZE; j++) {
                	int x = i;
                    int y = j;
                    g.drawImage(sol_bank,  x*BLOC_SIZE-12- BLOC_SIZE*depX -stepMainX, y*BLOC_SIZE-depY*BLOC_SIZE -stepMainY,BLOC_SIZE,BLOC_SIZE, null);
                }
        	}
    		g.drawImage(cercle_vert, BLOC_SIZE*13 - BLOC_SIZE*depX -stepMainX,BLOC_SIZE*29-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE,null); 
    		g.drawImage(comptoir, BLOC_SIZE*18- BLOC_SIZE*depX -stepMainX,BLOC_SIZE*21-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*4,BLOC_SIZE*2,null);
    		g.drawImage(tablech, BLOC_SIZE*5- BLOC_SIZE*depX -stepMainX,BLOC_SIZE*10-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*3,BLOC_SIZE*2,null);
    	}else if (numeroMap == 5) {
    		g.drawImage(ville, BLOC_SIZE-30-depX*BLOC_SIZE -stepMainX, BLOC_SIZE-30-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*31+5,BLOC_SIZE*31, null);
    		g.drawImage(cercle_vert, BLOC_SIZE*7 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*5-depY*BLOC_SIZE -stepMainY,BLOC_SIZE*2,BLOC_SIZE,null);   
    		g.drawImage(cercle_vert, BLOC_SIZE*17 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*4-depY*BLOC_SIZE -stepMainY ,BLOC_SIZE*2,BLOC_SIZE,null); 
    		g.drawImage(cercle_vert, BLOC_SIZE*9+10 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*22+5-depY*BLOC_SIZE -stepMainY ,BLOC_SIZE,BLOC_SIZE*2,null); 
    		g.drawImage(cercle_vert, BLOC_SIZE*10 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*18-depY*BLOC_SIZE -stepMainY,BLOC_SIZE,BLOC_SIZE,null); 
    		g.drawImage(cercle_vert, BLOC_SIZE*20 -depX*BLOC_SIZE -stepMainX,BLOC_SIZE*17+10-depY*BLOC_SIZE -stepMainY ,BLOC_SIZE,BLOC_SIZE*2,null); 
    	}
    	
    	try {
	        for (GameObject object : this.objects) {
	        	int x = 0; int y = 0; int stepX = 0; int stepY = 0;
	        
	        	x = object.getPosX();
	            y = object.getPosY();	
	            stepX = object.getStepX(); 
	            stepY = object.getStepY();
	                
	            int image = object.getImage();
	            
	            dessiner(image,x,y,stepX,stepY,g,object); 
	            }
	    	}
        catch (Exception e){
        	//System.out.println("exception");
        }
    }
        	 	
    private void dessiner(int image,int x, int y, int stepX, int stepY,Graphics g,GameObject object) {
    	Perso  perso = null;
    	if (object instanceof Perso) {
    		perso = (Perso)object;
    	}
    	switch(image) {
    	
        case 0: draw(mur,x,y,stepX,stepY,g); break;
        case 1: break;
        case 2 : draw(heal,x,y,stepX,stepY,g); break;
        case 5: draw(plante,x,y,stepX,stepY,g); break;
        case 7: draw(mechant,x,y,stepX,stepY,g);break;
        case 8: draw(feu,x,y,stepX,stepY,g); break;
        case 6 :draw(mur,x,y,stepX,stepY,g); break;
        case 9 : draw(porte_ouverte,x,y,stepX,stepY,g); break;
        case 10 : draw(porte_ferme,x,y,stepX,stepY,g); break;
        case 11 : draw(murgris,x,y,stepX,stepY,g); break;
        case 12 : draw(bombe,x,y,stepX,stepY,g); break;
        
        case 13 : draw(homer_devant_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,g,perso);break;
        case 14 :draw(homer_devant_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,g,perso);break;
        case 15 : draw(homer_devant_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,g,perso);break;
        case 16 : draw(homer_gauche_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 17 : draw(homer_gauche_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 18 : draw(homer_gauche_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 19 : draw(homer_derriere_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 20 : draw(homer_derriere_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 21 : draw(homer_derriere_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 22 : draw(homer_droite_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 23 : draw(homer_droite_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 24 : draw(homer_droite_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        
        case 25 : draw(Bart_devant_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 26 : draw(Bart_devant_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 27 : draw(Bart_devant_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 28 : draw(Bart_gauche_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 29 : draw(Bart_gauche_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 30 : draw(Bart_gauche_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 31 : draw(Bart_derriere_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 32 : draw(Bart_derriere_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 33 : draw(Bart_derriere_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 34 : draw(Bart_droite_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 35 : draw(Bart_droite_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 36 : draw(Bart_droite_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        
        case 37 : draw(teleportation,x,y,stepX,stepY,g); break;
        case 41 : draw(potionEnergy,x,y,stepX,stepY,g); break;
        case 39 : draw(munition,x,y,stepX,stepY,g); break;
        case 40 : draw(grenade,x,y,stepX,stepY,g); break;
        case 38 : draw(bombe,x,y,stepX,stepY,g); break;
        
        case 42 : draw(Marge_devant_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 43 : draw(Marge_devant_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 44 : draw(Marge_devant_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 45 : draw(Marge_gauche_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 46 : draw(Marge_gauche_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 47 : draw(Marge_gauche_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 48 : draw(Marge_derriere_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 49 : draw(Marge_derriere_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 50 : draw(Marge_derriere_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 51 : draw(Marge_droite_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 52 : draw(Marge_droite_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        case 53 : draw(Marge_droite_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY, g,perso);break;
        
        case 54 : draw(Policier_devant_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 55 : draw(Policier_devant_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 56 : draw(Policier_devant_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 57 : draw(Policier_gauche_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 58 : draw(Policier_gauche_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 59 : draw(Policier_gauche_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 60 : draw(Policier_derriere_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 61 : draw(Policier_derriere_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 62 : draw(Policier_derriere_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 63 : draw(Policier_droite_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 64 : draw(Policier_droite_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 65 : draw(Policier_droite_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        
        case 68 : draw(munition_haut,x,y,stepX,stepY,g); break;
        case 67 : draw(munition_bas,x,y,stepX,stepY,g); break;
        case 66 : draw(munition_droite,x,y,stepX,stepY,g); break;
        case 69 : draw(munition_gauche,x,y,stepX,stepY,g); break;
        
        case 70 : draw(Flanders_devant_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 71 : draw(Flanders_devant_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 72 : draw(Flanders_devant_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 73 : draw(Flanders_gauche_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 74 : draw(Flanders_gauche_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 75 : draw(Flanders_gauche_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 76 : draw(Flanders_derriere_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 77 : draw(Flanders_derriere_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 78 : draw(Flanders_derriere_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 79 : draw(Flanders_droite_1,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 80 : draw(Flanders_droite_2,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        case 81 : draw(Flanders_droite_3,x,y,stepX,stepY,g);barLife(x,stepX,y,stepY,  g,perso);break;
        
        case 82 : draw(siege,x,y,stepX,stepY,g); break;
        case 83 : draw(barreau,x,y,stepX,stepY,g); break;
        case 84 : draw(prison1,x,y,stepX,stepY,g); break;
        
        case 85 : draw(money,x,y,stepX,stepY,g); break;
        case 86 : draw(vendeur,x,y,stepX,stepY,g);break;
        
    	}
    }
    
    private void draw(BufferedImage image, int x, int y,int stepX,int stepY,Graphics g) {                   // Dessine les images
    	g.drawImage(image,(x-depX)*BLOC_SIZE + stepX -stepMainX,(y-depY)*BLOC_SIZE + stepY -stepMainY,BLOC_SIZE,BLOC_SIZE,null);   // dep et stepMain sont utiles en mode fenêtrage
    }
    
    private void barLife(int x,int stepX,int y,int stepY,Graphics g,Perso p) {
    	g.setColor(Color.RED);
    	g.fillRect((x-depX)*BLOC_SIZE + stepX -stepMainX, (y-depY)*BLOC_SIZE + stepY -stepMainY - 13 , 30, 4);
    	g.setColor(Color.GREEN);
    	int length_ok = (int) Math.round(30*p.getLife());
        g.fillRect((x-depX)*BLOC_SIZE + stepX -stepMainX ,(y-depY)*BLOC_SIZE + stepY -stepMainY - 13 , length_ok, 4);
    }
    
    public void redraw() {
        this.repaint();
    }

	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // Les méthodes SET ET GET

    public void setObjects(ArrayList<GameObject> objects, int numeroMap) {
    	this.numeroMap = numeroMap;
        this.objects = objects;
    }
    public void setBlocSize(int BLOC_SIZE) {
    	this.BLOC_SIZE = BLOC_SIZE;
    }

	public void setPlayer(Player p1,Player p2,Player p3,Player active_player,int numeroMap) {
		this.active_player = active_player;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.numeroMap = numeroMap;
	}
	public void setZoom(int zoom) {
		this.zoom= zoom;
	}
	public int getMapSize() {
		return this.MAP_SIZE;		
	}
}
