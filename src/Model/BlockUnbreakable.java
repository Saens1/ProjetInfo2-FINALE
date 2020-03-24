package Model;

public class BlockUnbreakable extends Block {

    public BlockUnbreakable(int X, int Y,int image) {
        super(X, Y, image);
    }

    @Override
    public boolean isObstacle() {
        return true;
    }
}
