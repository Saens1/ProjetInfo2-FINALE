package Model;

public abstract class Perso extends Movable{
	private double life = 100;

	public Perso(int x, int y, int image, int stepX, int stepY, Game game) {
		super(x, y, image, stepX, stepY, game);
		// TODO Auto-generated constructor stub
	}
	
	public double getLife() {
	    return life/100.0;
	}
	    
	public void addLife(double life) {
	    this.life += life;
	}
	    
	public void setLife(double life) {
	    this.life = life;
	}
}
