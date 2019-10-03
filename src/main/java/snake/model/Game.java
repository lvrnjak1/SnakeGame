package snake.model;

import java.util.Random;

public class Game {
    private Snake snake;
    private Food food;

    private static final int boardWidth = 40;
    private static final int boardHeight = 40;

    private int score;
    private boolean gameOver;
    private boolean paused;

    public Game() {
        int randX = (new Random()).nextInt(boardHeight);
        int randY = (new Random()).nextInt(boardWidth);

        this.snake = new Snake(boardWidth, boardHeight);
        this.food = new Food(randX, randY);
        this.score = 0;
        this.gameOver = false;
        this.paused = false;
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

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean getPaused(){
        return paused;
    }
}
