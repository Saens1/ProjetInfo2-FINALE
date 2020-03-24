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
public class Dead extends JFrame{
	private BufferedImage image;
	private JLabel label;
	
	public Dead(Game game){ //cr�ation du Frame lorsqu'on meurt
		this.setTitle("                                                                                     END");
		this.setSize(800, 600);   
	    this.setLocation(500,240);   
	    this.setResizable(false); //empeche le redimensionnement
	    this.setAlwaysOnTop(true);  //laisse la fenetre au dessus quoiqu'il se passe
	    this.setVisible(false);
 
	    try {
	    	image = ImageIO.read(new File("Images/Dead.png"));
	    }
	    catch(IOException e) {
	    	System.out.println("eeeh");
	    }
	    
	    label = new JLabel((new ImageIcon(image.getScaledInstance(700, 350, java.awt.Image.SCALE_SMOOTH))));		
	    
	    JLabel label_title = new JLabel("You're dead !");
	    label_title.setFont(new Font("Times New Roman", Font.BOLD, 35));
		label_title.setOpaque(true);
		label_title.setForeground(Color.BLACK);
		this.add(label_title);
		label_title.setBounds(300, 10, 250, 50);
		
		label.add(label_title);
		
		JLabel label_money = new JLabel("-100$");
		label_money.setFont(new Font("Times New Roman", Font.BOLD, 35));
		label_money.setOpaque(true);
		label_money.setForeground(Color.BLACK);
		this.add(label_money);
		label_money.setBounds(350, 50, 250, 50);
			
		label.add(label_money);
		
		
		JButton bouton1 = new JButton("Continue");
		bouton1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		bouton1.setBackground(Color.gray);
		bouton1.setBounds(280, 480, 230, 50); 
		label.add(bouton1);
		bouton1.addActionListener(new BoutonPause(this,game));

		this.add(label);    
	}

}