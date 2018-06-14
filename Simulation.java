package visualization;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Simulation extends AnimationTimer {

    private Canvas canvas;
    private int scansPerSecond = 2;
    private Color backgroundColor = Color.rgb(0x52, 0x4F, 0x52);
    private Color foregroundColor = Color.rgb(0,185,215);
    private Color highlightColorOne = Color.rgb(243, 114, 89);
    private Color highlightColorTwo = Color.rgb(130,205,185);
    private Color highlightColorThree = Color.rgb(253,245,169);

    private boolean simulationStarted;

    public Simulation(Canvas canvas) {
        this.canvas = canvas;
    }

    public Simulation(Canvas canvas, Color foregroundColor, Color backgroundColor, Color highlightColor) {
        this.canvas = canvas;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.highlightColorOne = highlightColor;
        this.updateCanvas();
    }

    @Override
    public void start() {
        super.start();

        this.simulationStarted = true;
    }

    @Override
    public void stop() {
        super.stop();

        this.simulationStarted = false;
    }

    protected void clearCanvas() {
        GraphicsContext g2d = canvas.getGraphicsContext2D();
        g2d.setFill(this.backgroundColor);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public boolean isSimulationStarted() {
        return simulationStarted;
    }

    protected Canvas getCanvas() {
        return canvas;
    }

    protected Color getForegroundColor() {
        return foregroundColor;
    }

    protected Color getHighlightColorOne() {
        return highlightColorOne;
    }

    protected Color getHighlightColorTwo() {
        return highlightColorTwo;
    }

    protected Color getHighlightColorThree() {
        return highlightColorThree;
    }

    protected int getScansPerSecond() {
        return scansPerSecond;
    }

    void setScansPerSecond(int scansPerSecond) {
        this.scansPerSecond = scansPerSecond;
    }

    public abstract void updateCanvas();
    public abstract void initializeData(int size);
    public abstract void shuffleData();
}

