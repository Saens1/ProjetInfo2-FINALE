package Model;

import java.util.ArrayList;


public class Citizen extends Perso implements Deletable{    // Le fait qu'il soit Deletable n'est pas utilisé car la mort des citizens est une teleportation de ceux-ci
	   														// Mais il est intéressant de garder l'option
	private Game game;

	public Citizen(int x, int y, Game game, int numeroMap) {
		super(x, y, 70, 0, 0, game);
		this.game = game;
		new ThreadCitizen(this,game,numeroMap);	
	}
	
	public void delete(){
        notifyDeletableObserver();
    }
	public void notifyDeletableObserver(){
        ArrayList<GameObject> loot = null;
        game.delete(this,loot); 
    }

}
