package snake.model;

public class SnakePart{
    private int x;
    private int y;

    public SnakePart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void movePart(int deltaX, int deltaY){
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }

    public boolean inCollision(int x, int y){
        return getX() == x && getY() == y;
    }

    public void move(Direction direction) {
        movePart(direction.getDeltaX(), direction.getDeltaY());
    }
}
