package snake.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    public Canvas canvas;
    public Button buttonUp;
    public Button buttonRight;
    public Button buttonLeft;
    public Button buttonDown;
    public Label score;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        new AnimationTimer(){
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if(lastTick == 0){
                    lastTick = now;
                    play(graphicsContext);
                    return;
                }

                /*if(now - lastTick > 1000000000 / speed){
                    lastTick = now;
                    play(graphicsContext);
                }*/
            }
        }.start();
    }

    private void play(GraphicsContext graphicsContext) {

    }
}
