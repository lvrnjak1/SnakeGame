package snake.model;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Movable{
    private List<SnakePart> snake = new ArrayList<>();
    private int speed;
    private Direction direction;

    public Snake(int width, int height) {
        //default snake with three parts
        addPart(width/2 + 2,height/2);
        addPart(width/2 + 1,height/2);
        addPart(width/2,height/2);
        this.speed = 5;
        this.direction = Direction.RIGHT;
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public synchronized boolean move() {
        for(int i = snake.size() - 1; i > 0; i--){
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
        getHead().move(getDirection());

        return !isSelfColided();
    }

    public boolean canEat(Food food) {
        for(SnakePart snakePart : snake){
            if(snakePart.getX() == food.getX() && snakePart.getY() == food.getY()) return true;
        }
        
        return false;
    }

    public void eat(Food food) {
        addPart(food.getX(), food.getY());
    }

    public boolean isSelfColided() {
        for(int i = 1; i < snake.size(); i++){
            if(snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()){
                return true;
            }
        }

        return false;
    }

    public SnakePart getHead() {
        return snake.get(0);
    }
}
