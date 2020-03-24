package Model;
import java.util.ArrayList;



public class Policier extends Perso implements Deletable{          // Le fait qu'il soit Deletable n'est pas utilisé car la mort des policiers est une teleportation de ceux-ci
																   // Mais il est intéressant de garder l'option
	private Game game;
	
	public Policier(int x,int y,Game game, int numeroMap) {
		super(x,y,56,0,0,game);
		this.game = game;
		new ThreadPolicier(this,game,numeroMap);		
	}

	public void delete(){
        notifyDeletableObserver();
    }
	public void notifyDeletableObserver(){
        ArrayList<GameObject> loot = null;
        game.delete(this,loot); //supprime la bombe
    }
	
}
