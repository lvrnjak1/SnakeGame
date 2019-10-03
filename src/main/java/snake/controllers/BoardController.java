package snake.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
    private static final int TICKER_INTERVAL = 350;

    private GraphicsContext graphicsContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        graphicsContext = canvas.getGraphicsContext2D();
        drawBoard(graphicsContext);
        canvas.setOnKeyTyped(this::handleKeyInput);
        pauseButton.setOnAction(this::pauseOrResume);
        restartButton.setOnAction(this::restart);
        endGameButton.setOnAction(this::endGame);
        canvas.requestFocus();
        new Thread(this).start();
    }

    private void handleKeyInput(KeyEvent event) {
        switch (event.getCharacter()){
            case "w":{
                game.getSnake().setDirection(Direction.UP);
                break;
            }
            case "a":{
                game.getSnake().setDirection(Direction.LEFT);
                break;
            }
            case "s":{
                game.getSnake().setDirection(Direction.DOWN);
                break;
            }
            case "d":{
                game.getSnake().setDirection(Direction.RIGHT);
                break;
            }
        }

        drawBoard(graphicsContext);
    }


    private synchronized void pauseOrResume(ActionEvent actionEvent) {
        if(game.isPaused()){
            pauseButton.setText("pause");
            game.setGameOver(false);
        }else{
            pauseButton.setText("resume");
            game.setGameOver(true);
        }

        notifyAll();
    }

    private synchronized void restart(ActionEvent actionEvent) {
        game = new Game();
        notifyAll();
    }

    private synchronized void endGame(ActionEvent actionEvent) {
        game.setGameOver(true);
        showGameOverMessage();
        notifyAll();
    }



    private void drawBoard(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,width * pixel,heigth * pixel);

        Snake snake = game.getSnake();
        for(SnakePart part : snake.getSnake()){
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(part.getX() * pixel, part.getY() * pixel, pixel, pixel);
        }

        graphicsContext.setFill(game.getFood().getColor().getColor());
        graphicsContext.fillRect(game.getFood().getX() * pixel, game.getFood().getY() * pixel, pixel, pixel);

        Platform.runLater(() -> score.setText(String.valueOf(game.getScore())));
    }

    private void play() {
        if(!game.getSnake().move() || game.isSnakeHitEdge()){
            game.setGameOver(true);
            showGameOverMessage();
            return;
        }

        if(game.getSnake().canEat(game.getFood())){
            game.getSnake().eat(game.getFood());
            game.increaseScore();
            game.nextFood();
        }


        drawBoard(graphicsContext);
    }



    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                while (game.isGameOver()) {
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

    private void showGameOverMessage() {
        String message = "Oh no, Game Over!";
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,width * pixel,heigth * pixel);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        graphicsContext.fillText(message, (width * pixel) / 2., (heigth * pixel) / 2.);
    }
}
