package visualization;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Collections;

public class Simulation {
    private Integer active;
    private Canvas canvas;
    private Integer list[];
    private Color backgroundColor = Color.rgb(0x52, 0x4F, 0x52);
    private Color foregroundColor = Color.rgb(130, 205, 185);
    private Color highlightColor = Color.rgb(243,114,89);

    public Simulation(Canvas canvas) {
        this.canvas = canvas;
        this.updateCanvas();
    }

    public Simulation(Canvas canvas, Color foregroundColor, Color backgroundColor, Color highlightColor) {
        this(canvas);
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.highlightColor = highlightColor;
        this.updateCanvas();
    }

    void updateCanvas() {
        GraphicsContext g2d = canvas.getGraphicsContext2D();

        this.clearCanvas(g2d);

        if (this.list == null)
            return;

        int padding;
        if (list.length >= 100)
            padding = 0;
        else
            padding = 2;

        this.active = 15;
        int unit_width = (int)(canvas.getWidth() / list.length);
        int unit_height  = (int)(canvas.getHeight() / Collections.max(Arrays.asList(list)));

        int x;
        int y;
        int box_height;
        for ( int i = 0; i < list.length; i++) {
            x = i * unit_width + padding;
            y = (int) canvas.getHeight() + padding - list[i] * unit_height;
            box_height = unit_height * list[i] - (2 * padding);

            if (i == active) {
                g2d.setFill(this.highlightColor);
                g2d.fillRect(x, y, unit_width - (2 * padding), box_height);
                g2d.setFill(this.foregroundColor);
            }
            else {
                g2d.fillRect(x, y, unit_width - (2 * padding), box_height);
            }
        }
    }

    private void clearCanvas(GraphicsContext g2d) {
        g2d.setFill(this.backgroundColor);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setFill(this.foregroundColor);
    }

    void updateList(int size) {
        this.list = new Integer[size];

        for (int i = 0; i < size; i++)
            this.list[i] = i+1;
    }
}
