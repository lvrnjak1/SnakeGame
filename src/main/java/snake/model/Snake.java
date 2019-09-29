package snake.model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakePart> snake = new ArrayList<>();

    public Snake() {
        //default snake with three parts
        addPart(0,0);
        addPart(0,1);
        addPart(0,2);
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
}
