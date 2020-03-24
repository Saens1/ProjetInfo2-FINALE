package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Model.Game;


public class BoutonEnd implements ActionListener{
	private JFrame f;
	private Game game;

	public BoutonEnd(JFrame f, Game game) {
		this.f = f;
		this.game = game;
	}
	
	//quand on clique sur  le bouton du menu end, le jeu s'arrete
	public void actionPerformed(ActionEvent arg0) {
		f.dispose();
		game.stop();
	}
}
