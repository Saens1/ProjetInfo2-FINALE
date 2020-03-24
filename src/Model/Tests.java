package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import View.Window;


class Tests{//pour les différents tests unitaires
	
	
	@Test 
	void testAddPotionEnergy() {//permet de vérifier que le loot d'une potion d'énergie l'ajoute correctement dans l'inventaire
		int numberPotionEnergy = 0;
		int increment = 1;
		Window window = new Window("Game",31,30);
		Game game = Game.getInstance(window, false,false, 1,"name");
		Player p = new Player(10, 10,13,game, 40);
				
		p.addPotionEnergy(1);
		
		assertEquals(numberPotionEnergy + increment, p.getNumberPotionEnergy());		
	}
	
	
	@Test
	void testSizeList() {//permet de checker qu'on diminue correctement la taille de la liste des GameObject lorsqu'on loot une potion
		int Size1;
		int Size2;
		
		Window window = new Window("Game",31,30);
		
		Game game = Game.getInstance(window, false,false, 1,"name");
		
		PotionEnergy pot = new PotionEnergy(11,10,41,game);
				
		game.addListeObject(pot);
				
		Size1 = game.getListeObject().size();
					
		game.movePlayer(1, 0);
	
		Size2 = game.getListeObject().size();
													
		assertEquals(1,  Size1 - Size2);

		
	}
	

}
