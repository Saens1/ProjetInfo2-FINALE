package Model;

import java.util.ArrayList;

public class BlockBreakable extends Block implements Deletable, Activable {
    private Game game;
    
    public BlockBreakable(int X, int Y, int image, Game game) {
        super(X, Y, image);
        this.game = game;
       
    }
    protected void crush(){
        notifyDeletableObserver();
    }

    @Override
    public void notifyDeletableObserver(){
        ArrayList<GameObject> loot = null;
        game.delete(this,loot); 
    }
    
    //detruit le bloc
    public void activate(){
         crush();
    }

    public boolean isObstacle() {
        return true;
    }
    
}
