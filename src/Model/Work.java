package Model;

public class Work extends GameObject implements Needs, Runnable{
	private Game game;
	private boolean active_thread = true;
	private Thread thread;
	private int x;
	private int y;
	
	public Work(int X, int Y, int image,Game game) {
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
				Thread.sleep(100);
				if (game.getPause() == false) {
				if (x == game.getActivePlayer().getPosX() && y == game.getActivePlayer().getPosY()){
					game.getActivePlayer().addMoney(1);					
				}else {
					active_thread = false;
				}
				}
			}
		}
		catch(Exception e) {		
		}
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}

}