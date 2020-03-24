package Model;

public class WC extends GameObject implements Needs, Runnable{
	private Game game;
	private boolean active_thread = true;
	private Thread thread;
	private int x;
	private int y;
	
	public WC(int X, int Y, int image,Game game) {
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
	
	public void run() {	
		try{
			while (active_thread == true) {
				Thread.sleep(50);
				if (game.getPause() == false) {
				if (game.getActivePlayer().getHygiene() <100 && x == game.getActivePlayer().getPosX() && y == game.getActivePlayer().getPosY()){
					game.getActivePlayer().addHygiene(0.5);
				}else {
					active_thread = false;
				}
				game.notifyView();
			}
			}
		}
		catch(Exception e) {
			System.out.println("Exception in WC");
		}
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

}