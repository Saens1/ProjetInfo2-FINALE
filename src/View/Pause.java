package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.BoutonPause;
import Model.Game;

@SuppressWarnings("serial")
public class Pause extends JFrame{//création du Frame du menu pause
	private BufferedImage image;
	private JLabel label;
	
	public Pause(Game game){
		this.setTitle("                                                                                     Pause");
		this.setSize(800, 600);   
	    this.setLocation(500,240);   
	    this.setResizable(false); //empeche le redimensionnement
	    this.setAlwaysOnTop(true);  //laisse la fenetre au dessus quoiqu'il se passe
	    this.setVisible(false);
 
	    try {
	    	image = ImageIO.read(new File("Images/Pause.png"));
	    }
	    catch(IOException e) {
	    	System.out.println("exception lecture image Pause");
	    }
	    
	    label = new JLabel((new ImageIcon(image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH))));		
	    this.add(label);
		
		JButton bouton1 = new JButton("Continue");
		bouton1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		bouton1.setBackground(Color.pink);
		bouton1.setBounds(270, 480, 230, 50); 
		label.add(bouton1);
		
		this.add(label);
		
		bouton1.addActionListener(new BoutonPause(this,game));
		  
	    
	}

}
