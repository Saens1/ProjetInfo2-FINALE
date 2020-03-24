package Model;

public abstract class GameObject {
    private int posX;
    private int posY;
    private int stepX;
    private int stepY;
    private int image;

    public GameObject(int X, int Y, int image,int stepX,int stepY) {
    	this.stepX = stepX;
    	this.stepY = stepY;
        this.posX = X;
        this.posY = Y;
        this.image = image;
    }
    
	public int getPosX() {
        return this.posX;
    }
    public int getPosY() {
        return this.posY;
    }
    public int getImage() {
        return this.image;
    }
    public int getStepX() {
        return this.stepX;
    }
    public int getStepY() {
        return this.stepY;
    }
    
    
	public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public void setStepX(int stepX) {
        this.stepX = stepX;
    }
    public void setStepY(int stepY) {
        this.stepY = stepY;
    }
    
    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();
}
