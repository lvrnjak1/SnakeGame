package snake.controllers;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import snake.model.Game;
import snake.model.Snake;
import snake.model.SnakePart;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        drawBoard(graphicsContext);
    }

    private void drawBoard(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(0,0,width * pixel,heigth * pixel);

        Snake snake = game.getSnake();
        for(SnakePart part : snake.getSnake()){
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(part.getX() * pixel, part.getY() * pixel, pixel, pixel);
        }

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(game.getFood().getX() * pixel, game.getFood().getY() * pixel, pixel, pixel);
    }

    private void play(GraphicsContext graphicsContext) {
        if(game.isGameOver()){
            //add a label for game over
            return;
        }


    }
}
