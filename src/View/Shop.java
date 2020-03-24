package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.BoutonAchat;

import java.awt.Color;
import java.awt.Font;

import Model.Game;

@SuppressWarnings("serial")
public class Shop extends JFrame{//création du Frame du Shop

	public Shop(Game game) {
		this.setTitle("Shop");
	    this.setSize(455, 435);   //taille en concordance avec l'affichage du jeu
	    this.setLocation(1136,585); // position a tout a droite   
	    this.setResizable(false); //empeche le redimensionnement
	    //this.setAlwaysOnTop(true);  //laisse la fenetre au dessus quoiqu'il se passe
	    this.setVisible(false);
		
		JLabel label_title = new JLabel("Buy what you want !");
	    label_title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		label_title.setOpaque(true);
		label_title.setForeground(Color.BLACK);
		this.add(label_title);
		label_title.setBounds(90, 10, 250, 30);
		
		
		// BUY MUNITION
 
	    JLabel label1 = new JLabel("Munition : 1$");
	    label1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label1.setOpaque(true);
		label1.setForeground(Color.BLACK);
		this.add(label1);
		label1.setBounds(10, 70, 110, 20);
		
		JTextField textField = new JTextField();
		textField.setBounds(190, 73, 50, 20);
		this.add(textField);
		
		JLabel label_unit1 = new JLabel("units");
	    label_unit1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_unit1.setOpaque(true);
		label_unit1.setForeground(Color.BLACK);
		this.add(label_unit1);
		label_unit1.setBounds(250, 70, 50, 20);
		
		JButton boutonA1 = new JButton("Achat");  //Apple
        boutonA1.addActionListener(new BoutonAchat(textField,1, game));
        
        this.setLayout(null);
        this.add(boutonA1);
        boutonA1.setBounds(300, 65, 75, 30);
        
     // BUY POTIONLIFE
        
	    JLabel label2 = new JLabel("Potion Life : 15$");
	    label2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label2.setOpaque(true);
		label2.setForeground(Color.BLACK);
		this.add(label2);
		label2.setBounds(10, 120, 130, 20);
		
		JTextField textField2 = new JTextField();
		textField2.setBounds(190, 123, 50, 20);
		this.add(textField2);
		
		JLabel label_unit2 = new JLabel("units");
	    label_unit2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_unit2.setOpaque(true);
		label_unit2.setForeground(Color.BLACK);
		this.add(label_unit2);
		label_unit2.setBounds(250, 120, 50, 20);
		
		JButton boutonA2 = new JButton("Achat");  //Apple
        boutonA2.addActionListener(new BoutonAchat(textField2,2,game));
        
        this.setLayout(null);
        this.add(boutonA2);
        boutonA2.setBounds(300, 115, 75, 30);

        // BUY POTIONENERGY
        
	    JLabel label3 = new JLabel("Potion Energy: 10$");
	    label3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label3.setOpaque(true);
		label3.setForeground(Color.BLACK);
		this.add(label3);
		label3.setBounds(10, 170, 150, 20);
		
		JTextField textField3 = new JTextField();
		textField3.setBounds(190, 173, 50, 20);
		this.add(textField3);
		
		JLabel label_unit3 = new JLabel("units");
	    label_unit3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_unit3.setOpaque(true);
		label_unit3.setForeground(Color.BLACK);
		this.add(label_unit3);
		label_unit3.setBounds(250, 170, 50, 20);
		
		JButton boutonA3 = new JButton("Achat");  //Apple
        boutonA3.addActionListener(new BoutonAchat(textField3,3,game));
        
        this.setLayout(null);
        this.add(boutonA3);
        boutonA3.setBounds(300, 165, 75, 30);
        
        // BUY GRENADE
        
	    JLabel label4 = new JLabel("Grenade : 3$");
	    label4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label4.setOpaque(true);
		label4.setForeground(Color.BLACK);
		this.add(label4);
		label4.setBounds(10, 220, 150, 20);
		
		JTextField textField4 = new JTextField();
		textField4.setBounds(190, 223, 50, 20);
		this.add(textField4);
		
		JLabel label_unit4 = new JLabel("units");
	    label_unit4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_unit4.setOpaque(true);
		label_unit4.setForeground(Color.BLACK);
		this.add(label_unit4);
		label_unit4.setBounds(250, 220, 50, 20);
		
		JButton boutonA4 = new JButton("Achat");  //Apple
        boutonA4.addActionListener(new BoutonAchat(textField4,4, game));
        
        this.setLayout(null);
        this.add(boutonA4);
        boutonA4.setBounds(300, 215, 75, 30);
        
        // BUY BOMB
        
  	    JLabel label5 = new JLabel("Bomb : 50$");
  	    label5.setFont(new Font("Times New Roman", Font.BOLD, 18));
  		label5.setOpaque(true);
  		label5.setForeground(Color.BLACK);
  		this.add(label5);
  		label5.setBounds(10, 270, 150, 20);
  		
  		JTextField textField5 = new JTextField();
  		textField5.setBounds(190, 273, 50, 20);
  		this.add(textField5);
  		
  		JLabel label_unit5 = new JLabel("units");
  	    label_unit5.setFont(new Font("Times New Roman", Font.BOLD, 20));
  		label_unit5.setOpaque(true);
  		label_unit5.setForeground(Color.BLACK);
  		this.add(label_unit5);
  		label_unit5.setBounds(250, 270, 50, 20);
  		
  		JButton boutonA5 = new JButton("Achat");  //Apple
          boutonA5.addActionListener(new BoutonAchat(textField5,5, game));
          
        this.setLayout(null);
        this.add(boutonA5);
        boutonA5.setBounds(300, 265, 75, 30);
        
        // BUY B
        
  	    JLabel label6 = new JLabel("Teleportation : 20$");
  	    label6.setFont(new Font("Times New Roman", Font.BOLD, 18));
  		label6.setOpaque(true);
  		label6.setForeground(Color.BLACK);
  		this.add(label6);
  		label6.setBounds(10, 320, 150, 20);
  		
  		JTextField textField6 = new JTextField();
  		textField6.setBounds(190, 323, 50, 20);
  		this.add(textField6);
  		
  		JLabel label_unit6 = new JLabel("units");
  	    label_unit6.setFont(new Font("Times New Roman", Font.BOLD, 20));
  		label_unit6.setOpaque(true);
  		label_unit6.setForeground(Color.BLACK);
  		this.add(label_unit6);
  		label_unit6.setBounds(250, 320, 50, 20);
  		
  		JButton boutonA6 = new JButton("Achat");  //Apple
        boutonA6.addActionListener(new BoutonAchat(textField6,6,game));
          
        this.setLayout(null);
        this.add(boutonA6);
        boutonA6.setBounds(300, 315, 75, 30);

	} 
	
}
