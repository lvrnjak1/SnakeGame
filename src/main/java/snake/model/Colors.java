package snake.model;

import javafx.scene.paint.Color;

public enum Colors {
    BLUE(Color.BLUE), GREEN(Color.GREEN), PINK(Color.PINK), ORANGE(Color.ORANGE), YELLOW(Color.YELLOW);

    private javafx.scene.paint.Paint color;

    Colors(javafx.scene.paint.Paint color){
        this.color = color;
    }

    public javafx.scene.paint.Paint getColor() {
        return color;
    }
}
