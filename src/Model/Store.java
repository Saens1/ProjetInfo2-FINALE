package Model;

import View.Shop;

public class Store extends BlockUnbreakable implements Activable {
    private Game game;
	public Store(int X, int Y, int Image, Game game) {
		super(X, Y, 1);
		this.game = game;
		
	}
	
	//lance le store qui permet d'acheter des items lorsqu'on clique sur un comptoir
	public void activate() {
		Shop shop = new Shop(game);
        shop.setVisible(true);
        }

}