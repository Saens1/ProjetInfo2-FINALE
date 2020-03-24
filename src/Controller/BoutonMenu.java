package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Model.Game;
import View.Window;

public class BoutonMenu implements ActionListener {
	private JFrame f;
	private JTextField name;
	private JComboBox<String> Difficulty;
	private JComboBox<String> Sound;
	private JComboBox<String> Backup;
	private String Name;
	private boolean load;
	private boolean sound;
	private double loss;
	
	public BoutonMenu(JFrame f,JTextField name,JComboBox<String> Difficulty,JComboBox<String> Sound,JComboBox<String> Backup) {
		this.f=f;
		this.name = name;
		this.Difficulty = Difficulty;
		this.Sound = Sound;
		this.Backup = Backup;
	}

	//Gere les différentes option des boutons du menu
	public void actionPerformed(ActionEvent arg0) {
		Name = name.getText();
		if (Backup.getSelectedItem() =="   New Game") {
			load = false;
		}else {
			load = true;
		}
		
		if (Sound.getSelectedItem() == "   Sound ON") {
			sound = true;
		}else {
			sound = false;
		}
		
		if(Difficulty.getSelectedItem() == "   Easy") {
			loss = 0.05; 
		}else if (Difficulty.getSelectedItem() == "   Medium") {
			loss = 0.1; 
		}else {
			loss = 0.2; 
		}
		System.out.println(sound); 
		f.dispose();
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// LANCE LE JEU avec les bons arguments ( paramètres)
		
		
		Window window = new Window("Game",31,30);
		Game game = Game.getInstance(window,load,sound, loss,Name);
        Keyboard keyboard = new Keyboard(game);
        Mouse mouse = new Mouse(game);
        window.setKeyListener(keyboard);
        window.setMouseListener(mouse);
        
		
	}

}
