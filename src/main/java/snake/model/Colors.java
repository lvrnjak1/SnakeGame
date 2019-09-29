package snake.model;

import java.awt.*;

public enum Colors {
    BLUE(Color.blue), GREEN(Color.green), PINK(Color.pink), ORANGE(Color.orange), YELLOW(Color.yellow);

    private Color color;

    Colors(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
