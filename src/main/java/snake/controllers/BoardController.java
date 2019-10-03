package snake.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import snake.model.Direction;
import snake.model.Game;
import snake.model.Snake;
import snake.model.SnakePart;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable, Runnable {
    public Canvas canvas;
    public Button pauseButton; //when it is clicked change text to resume and vice versa
    public Button restartButton;
    public Button endGameButton;
    public Label score;
    public BorderPane pane;

    private Game game;

    private static final int width = 40;
    private static final int heigth = 40;
    private static final int pixel = 10;
    private static final int TICKER_INTERVAL = 5;

    GraphicsContext graphicsContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        graphicsContext = canvas.getGraphicsContext2D();
        drawBoard(graphicsContext);
        canvas.setOnKeyPressed(this::handleKeyInput);
        pauseButton.setOnAction(this::pauseOrResume);
        restartButton.setOnAction(this::restart);
        endGameButton.setOnAction(this::endGame);
        canvas.requestFocus();
        new Thread(this).start();
    }

    private void handleKeyInput(KeyEvent event) {
        switch (event.getCharacter()){
            case "w":{
                //up
                game.getSnake().setDirection(Direction.UP);
                break;
            }
            case "a":{
                //left
                game.getSnake().setDirection(Direction.LEFT);
                break;
            }
            case "s":{
                //right
                game.getSnake().setDirection(Direction.RIGHT);
                break;
            }
            case "d":{
                //down
                game.getSnake().setDirection(Direction.DOWN);
                break;
            }
        }
    }


    private void pauseOrResume(ActionEvent actionEvent) {
        if(game.isPaused()){
            pauseButton.setText("pause");
            game.setPaused(false);
        }else{
            pauseButton.setText("resume");
            game.setPaused(true);
        }
    }

    private void restart(ActionEvent actionEvent) {
    }

    private void endGame(ActionEvent actionEvent) {
    }



    private void drawBoard(GraphicsContext graphicsContext) {
        //do i need to clear it?
        graphicsContext.fillRect(0,0,width * pixel,heigth * pixel);

        Snake snake = game.getSnake();
        for(SnakePart part : snake.getSnake()){
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(part.getX() * pixel, part.getY() * pixel, pixel, pixel);
        }

        graphicsContext.setFill(game.getFood().getColor().getColor());
        graphicsContext.fillRect(game.getFood().getX() * pixel, game.getFood().getY() * pixel, pixel, pixel);
    }

    private void play() {
        if(game.isGameOver()){
            showAlert("Game Over");
            return;
        }

        game.getSnake().move();
        drawBoard(graphicsContext);
        if(game.getSnake().isSelfColided() || game.isSnakeHitEdge()){
            game.setGameOver(true);
            showAlert("Game Over");
            return;
        }

        if(game.getSnake().canEat(game.getFood())){
            game.getSnake().eat(game.getFood());
            game.nextFood();
        }
    }



    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                while (!game.isGameOver()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Platform.runLater(() -> canvas.requestFocus());
            play();
            try {
                Thread.sleep(TICKER_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String message) {
    }
}
