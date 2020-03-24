package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Model.Game;

public class BoutonPause implements ActionListener{
	private JFrame f;
	private Game game;

	public BoutonPause(JFrame f, Game game) {
		this.f = f;
		this.game = game;
	}
	
	//quand on clique sur  le bouton du menu pause, le jeu reprend
	public void actionPerformed(ActionEvent arg0) {
		f.dispose();
		game.reStart();
	}

}
