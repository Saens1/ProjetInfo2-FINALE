package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Game;



public class BoutonAchat implements ActionListener {
	private JTextField text;
	private Game game;
	private int numeroButton;
	
	public BoutonAchat(JTextField text, int numeroButton, Game game) {
		this.text = text;
		this.game = game;
		this.numeroButton = numeroButton;
	}
	
	//Gere les achats avec les boutons de la boutique, gère les erreurs d'entrée utilisateur
	public void actionPerformed(ActionEvent arg0) {
		int montant;
		try{
		switch(numeroButton) {
		case 1: 
			montant = Integer.parseInt(text.getText()) * 1;
			if (game.getActivePlayer().getMoney() > montant && montant > 0) {
				game.getActivePlayer().addMoney(-montant);
				game.getActivePlayer().addMunition(Integer.parseInt(text.getText()));
			}else if (montant < 0) {
				JOptionPane.showMessageDialog(null,"Veuillez écrire un montant positif.");
			}else {
				JOptionPane.showMessageDialog(null,"Désolé mais vous n'avez pas assez d'argent.");
			}break;
		case 2: 
			montant = Integer.parseInt(text.getText()) * 15;
			if (game.getActivePlayer().getMoney() > montant  && montant > 0) {
				game.getActivePlayer().addMoney(-montant);
				game.getActivePlayer().addPotionLife(Integer.parseInt(text.getText()));
			}else if (montant < 0) {
				JOptionPane.showMessageDialog(null,"Veuillez écrire un montant positif.");
			}else {
					JOptionPane.showMessageDialog(null,"Désolé mais vous n'avez pas assez d'argent.");
				}break;
		case 3: 
			montant = Integer.parseInt(text.getText()) * 10;
			if (game.getActivePlayer().getMoney() > montant && montant > 0) {
				game.getActivePlayer().addMoney(-montant);
				game.getActivePlayer().addPotionEnergy(Integer.parseInt(text.getText()));
			}else if (montant < 0) {
				JOptionPane.showMessageDialog(null,"Veuillez écrire un montant positif.");
			}else {
					JOptionPane.showMessageDialog(null,"Désolé mais vous n'avez pas assez d'argent.");
				}break;
		case 4: 
			montant = Integer.parseInt(text.getText()) * 3;
			if (game.getActivePlayer().getMoney() > montant  && montant > 0) {
				game.getActivePlayer().addMoney(-montant);
				game.getActivePlayer().addGrenade(Integer.parseInt(text.getText()));
			}else if (montant < 0) {
				JOptionPane.showMessageDialog(null,"Veuillez écrire un montant positif.");
			}else {
					JOptionPane.showMessageDialog(null,"Désolé mais vous n'avez pas assez d'argent.");
				}break;
		case 5: 
			montant = Integer.parseInt(text.getText()) * 50;
			if (game.getActivePlayer().getMoney() > montant  && montant > 0) {
				game.getActivePlayer().addMoney(-montant);
				game.getActivePlayer().addBomb(Integer.parseInt(text.getText()));
			}else if (montant < 0) {
				JOptionPane.showMessageDialog(null,"Veuillez écrire un montant positif.");
			}else {
					JOptionPane.showMessageDialog(null,"Désolé mais vous n'avez pas assez d'argent.");
				}break;
		case 6: 
			montant = Integer.parseInt(text.getText()) * 20;
			if (game.getActivePlayer().getMoney() > montant  && montant > 0) {
				game.getActivePlayer().addMoney(-montant);
				game.getActivePlayer().addTeleportation(Integer.parseInt(text.getText()));
			}else if (montant < 0) {
				JOptionPane.showMessageDialog(null,"Veuillez écrire un montant positif.");
			}else {
					JOptionPane.showMessageDialog(null,"Désolé mais vous n'avez pas assez d'argent.");
				}break;
			
		}
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Vous n'avez pas écrit un nombre entier !");
		}

	}
	
	
}