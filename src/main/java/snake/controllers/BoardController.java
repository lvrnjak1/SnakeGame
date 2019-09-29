package snake.controllers;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
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

    }
}
