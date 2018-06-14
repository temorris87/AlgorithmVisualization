package visualization.sorting;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import visualization.Simulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public abstract class SortSimulation extends Simulation {

    private Integer[] list;
    private long lastUpdate = 0;
    private int unitWidth;
    private int unitHeight;
    private int padding;

    SortSimulation(Canvas canvas) {
        super(canvas);
    }

    Integer[] getList() {
        return list;
    }

    @Override
    public void shuffleData() {
        int totalPasses = 7;

        Random rand = new Random();

        int randIndex;
        int temp;
        for (int passNumber = 0; passNumber < totalPasses; passNumber++) {
            for (int i = 0; i < list.length; i++) {
                randIndex = rand.nextInt(list.length);

                temp = list[i];
                list[i] = list[randIndex];
                list[randIndex] = temp;
            }
        }
    }

    @Override
    public void handle(long now) {
        int framesPerSecond = 60;

        if ((now - lastUpdate) > ((10 - this.getScansPerSecond()) * 1000000000L / list.length)) {
            lastUpdate = now;

            int numberOfUpdates;

            if (list.length <= framesPerSecond)
                numberOfUpdates = 1;
            else
                numberOfUpdates = this.getScansPerSecond() * list.length / framesPerSecond + 1;

            boolean moreFramesRemaining;
            for (int i = 0; i < numberOfUpdates; i++) {
                moreFramesRemaining = updateOneFrame();
                if (moreFramesRemaining) {
                    this.updateCanvas();
                    this.stop();
                }
            }

            this.updateCanvas();
        }
    }

    public abstract boolean updateOneFrame();

    public void initializeData(int size) {
        this.list = new Integer[size];

        for (int i = 0; i < size; i++)
            this.getList()[i] = i+1;

        this.unitWidth = (int)(this.getCanvas().getWidth() / list.length);
        this.unitHeight  = (int)(this.getCanvas().getHeight() / Collections.max(Arrays.asList(list)));

        if (this.list.length >= 100)
            this.padding = 0;
        else
            this.padding = 2;
    }

    void drawListItem(int index, Color color) {
        GraphicsContext g2d = this.getCanvas().getGraphicsContext2D();

        int x = index * this.unitWidth + this.padding;
        int y = (int) this.getCanvas().getHeight() + padding - this.list[index] * unitHeight;
        int boxHeight = this.unitHeight * this.list[index] - (2 * this.padding);
        int boxWidth = this.unitWidth - (2 * padding);

        g2d.setFill(color);
        g2d.fillRect(x, y, boxWidth, boxHeight);
    }

    @Override
    public void updateCanvas() {
        this.clearCanvas();

        if (this.list == null)
            return;

        this.drawListOnCanvas();
    }

    private void drawListOnCanvas() {
        for ( int i = 0; i < list.length; i++) {
            this.drawListItem(i, this.getForegroundColor());
        }
    }

    void swapEntries(int indexOne, int indexTwo) {
        int temp = this.getList()[indexOne];
        this.getList()[indexOne] = this.getList()[indexTwo];
        this.getList()[indexTwo] = temp;
    }
}
