package Model;

public class Bed extends GameObject implements Needs, Runnable{
	private Game game;
	private boolean active_thread = true;
	private Thread thread;
	private int x;
	private int y;
	
	public Bed(int X, int Y, int image,Game game) {
		super(X, Y ,1, 0, 0);
		this.game = game;
	}

	//lance le thread lorsqu'on clique sur un lit en étant à proximité
	public void fillTheNeed() {
		this.thread = new Thread(this);
		thread.start();
		active_thread = true; 
		x = game.getActivePlayer().getPosX();
		y = game.getActivePlayer().getPosY();	
	}
	
	
	// Ce thread augmente l'energie du player et s'arrête si le player se déplace
	public void run() {	
		try{
			while (active_thread == true) {
				Thread.sleep(50);
				if (game.getPause() == false) {
					if (game.getActivePlayer().getEnergy() < 1 && x == game.getActivePlayer().getPosX() && y == game.getActivePlayer().getPosY()){
						game.getActivePlayer().addEnergy(0.5);
					}else {
						active_thread = false;
					}
					game.notifyView();
				}
			}
		}
		catch(Exception e) {
			System.out.println("Exception Bed");
		}
	
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

}
