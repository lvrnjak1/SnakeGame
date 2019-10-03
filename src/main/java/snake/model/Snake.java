package snake.model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakePart> snake = new ArrayList<>();
    private int speed;

    public Snake(int width, int height) {
        //default snake with three parts
        addPart(width/2,height/2);
        addPart(width/2,height/2);
        addPart(width/2,height/2);
        this.speed = 5;
    }

    public List<SnakePart> getSnake() {
        return snake;
    }

    public SnakePart getPart(int i){
        return snake.get(i);
    }

    public void addPart(int x, int y){
        snake.add(new SnakePart(x, y));
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNumberOfParts(){
        return snake.size();
    }
}
