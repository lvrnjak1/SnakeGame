package snake.model;

public enum Direction {
    UP(-1, 0), LEFT(0,-1), RIGHT(0, 1), DOWN(1,0);

    private int deltaX;
    private int deltaY;

    Direction(int deltaX, int deltaY){
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX(){
        return deltaX;
    }

    public int getDeltaY(){
        return deltaY;
    }
}
