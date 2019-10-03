package snake.model;

import java.util.Random;

public class Game {
    private Snake snake;
    private Food food;

    private static final int boardWidth = 40;
    private static final int boardHeight = 40;

    private int score;
    private boolean gameOver;

    public Game() {
        int randX = (new Random()).nextInt(boardHeight);
        int randY = (new Random()).nextInt(boardWidth);

        this.snake = new Snake(boardWidth, boardHeight);
        this.food = new Food(randX, randY);
        this.score = 0;
        this.gameOver = false;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    public static int getBoardHeight() {
        return boardHeight;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isPaused(){
        return isGameOver();
    }

    public boolean isSnakeHitEdge() {
        for(SnakePart snakePart : snake.getSnake()){
            if(snakePart.getX() == -1 || snakePart.getX() == boardWidth || snakePart.getY() == -1 || snakePart.getY() == boardHeight){
                return true;
            }
        }

        return false;
    }

    public void nextFood() {
        food.nextColor();
        while (food.getX() == snake.getHead().getX() && food.getY() == snake.getHead().getY()) {
            food.nextPosition(boardWidth, boardHeight);
        }
    }
}
