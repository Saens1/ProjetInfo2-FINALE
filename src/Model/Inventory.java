package Model;

import java.util.ArrayList;

public abstract class Inventory extends GameObject implements Collectable,Deletable,Activable{
	private Game game;
	protected boolean canWeCrush;             // permet de savoir si on peut ramasser et detruire l'objet

	public Inventory(int X, int Y, int color, Game game) {
		super(X, Y, color, 0, 0);
		this.game = game;
	}
	//delete un item
	protected void crush(){
		if (canWeCrush) {
			notifyDeletableObserver();
		}
    }

    @Override
    public void notifyDeletableObserver(){
        ArrayList<GameObject> loot = null;
        game.delete(this,loot); 
    }
    
    public boolean getCanWeCrush() {
    	return canWeCrush;
    }
}
