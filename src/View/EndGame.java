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

import Controller.BoutonEnd;

import Model.Game;

@SuppressWarnings("serial")
public class EndGame extends JFrame{//création du Frame lorsqu'on perd
	private BufferedImage image;
	private JLabel label;
	
	public EndGame(Game game){
		this.setTitle("                                                                                     END");
		this.setSize(800, 600);   
	    this.setLocation(500,240);   
	    this.setResizable(false); //empeche le redimensionnement
	    this.setAlwaysOnTop(true);  //laisse la fenetre au dessus quoiqu'il se passe
	    this.setVisible(false);
 
	    try {
	    	image = ImageIO.read(new File("Images/endGame.png"));
	    }
	    catch(IOException e) {
	    }
	    
	    label = new JLabel((new ImageIcon(image.getScaledInstance(700, 350, java.awt.Image.SCALE_SMOOTH))));		
	    
	    JLabel label_title = new JLabel("You Lose !");
	    label_title.setFont(new Font("Times New Roman", Font.BOLD, 45));
		label_title.setOpaque(true);
		label_title.setForeground(Color.BLACK);
		this.add(label_title);
		label_title.setBounds(300, 30, 250, 50);
		
		label.add(label_title);
		
		
		JButton bouton1 = new JButton("   END  ");
		bouton1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		bouton1.setBackground(Color.gray);
		bouton1.setBounds(280, 480, 230, 50); 
		label.add(bouton1);
		bouton1.addActionListener(new BoutonEnd(this,game));

		this.add(label);    
	}

}