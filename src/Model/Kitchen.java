package Model;

public class Kitchen extends GameObject implements Needs, Runnable{
	private Game game;
	private boolean active_thread = true;
	private Thread thread;
	private int x;
	private int y;
	
	public Kitchen(int X, int Y, int image,Game game) {
		super(X, Y ,1, 0, 0);
		this.game = game;
	}

	public void fillTheNeed() {
		this.thread = new Thread(this);
		thread.start();
		active_thread = true; 
		x = game.getActivePlayer().getPosX();
		y = game.getActivePlayer().getPosY();	
	}
	
	public void run() {	//augmente la faim
		try{
			while (active_thread == true) {
				Thread.sleep(50);
				if (game.getPause() == false) {
					if (game.getActivePlayer().getHunger() <100 && x == game.getActivePlayer().getPosX() && y == game.getActivePlayer().getPosY()){
						game.getActivePlayer().addHunger(0.5);
					}else {
						active_thread = false;
					}
				}
			}
		}
		catch(Exception e) {	
			System.out.println("Exception in Kitchen");
		}
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

}