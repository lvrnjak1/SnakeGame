package snake.model;

import java.util.Random;

public class Game {
    private Snake snake;
    private Food food;

    private static final int boardWidth = 38;
    private static final int boardHeight = 38;
    private static final int squareSize = 10;

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

    public static int getSquareSize() {
        return squareSize;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
