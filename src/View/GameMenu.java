package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.BoutonMenu;

@SuppressWarnings("serial")
public class GameMenu extends JFrame{//création du Frame du Menu d'entrée
	private JPanel groupPanel1 = new JPanel(new BorderLayout());
	private JPanel groupPanel2 = new JPanel(new BorderLayout());
	private BufferedImage image;
	private JLabel label;
	
	public GameMenu() {
	this.setTitle("START GAME");
    this.setSize(1300, 900);   
    this.setLocation(230,80);  
    this.setResizable(false);
    this.setVisible(false);
    @SuppressWarnings("unused")
	GridLayout gl = new GridLayout(2, 0);
    this.setLayout(new GridLayout(2, 0,0,0));
    this.setPreferredSize(new Dimension(100,100));
    this.setBackground(Color.BLACK);
    
    groupPanel1.setLayout(new GridLayout(5,0,0,10));
    groupPanel2.setLayout(new GridLayout(0,2,30,0));
    
    try {
    	image = ImageIO.read(new File("Images/menu.png"));
    }
    catch(IOException e) {
    	System.out.println("exception lecture image dans GameMenu");
    }
    // TITLE
    
    label = new JLabel((new ImageIcon(image.getScaledInstance(1300, 450, java.awt.Image.SCALE_SMOOTH))));
	this.add(label);
	
	
	// NICKNAME
	
	JLabel label1 = new JLabel("   Choose your nickname : ");
    label1.setFont(new Font("Times New Roman", Font.BOLD, 30));
	label1.setOpaque(true);
	label1.setForeground(Color.BLACK);
	groupPanel2.add(label1);
	label1.setBounds(20, 100, 300, 40);
	
	JTextField textField = new JTextField();
	textField.setBounds(350, 100, 300, 40);
	Font bigFont = textField.getFont().deriveFont(Font.PLAIN, 30f);
	textField.setFont(bigFont);
	
	groupPanel2.add(textField);
	groupPanel1.add(groupPanel2);
	
	    
	// DIFFICULTY 
	
	String[] difficulty = new String[] {"   Easy", "   Medium","   Hard"};
	JComboBox<String> difficultyBox = new JComboBox<>(difficulty);
	difficultyBox.setFont(new Font("Times New Roman", Font.BOLD, 25));
	
	groupPanel1.add(difficultyBox);
	  
	// SOUND
	  
	String[] sound = new String[] {"   Sound ON", "   Sound OFF"};
	JComboBox<String> soundBox = new JComboBox<>(sound);
	soundBox.setFont(new Font("Times New Roman", Font.BOLD, 25));
	
	groupPanel1.add(soundBox);
	
	// NEW GAME / LOAD
	  
	String[] backup = new String[] {"   New Game", "   Load"};
	JComboBox<String> backupBox = new JComboBox<>(backup);
	backupBox.setFont(new Font("Times New Roman", Font.BOLD, 25));
	groupPanel1.add(backupBox);

	//START GAME
	JButton bouton1 = new JButton("Start Game");
	bouton1.setFont(new Font("Times New Roman", Font.BOLD, 35));
	bouton1.setBackground(Color.orange);
	bouton1.addActionListener(new BoutonMenu(this,textField,difficultyBox,soundBox,backupBox));
	
	groupPanel1.add(bouton1);
	 
	this.add(groupPanel1);

	    
	}
}

	
	
    
  
    
    

