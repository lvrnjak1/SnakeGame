package snake.model;

import java.util.Random;

public class Food {
    private int x;
    private int y;
    private Colors color = Colors.BLUE;

    public Food(int x, int y) {
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

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public void nextColor(){
        Random random = new Random();
        int color = random.nextInt(5);

        setColor(Colors.values()[color]);
    }
}
