package visualization.sorting;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import visualization.Simulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class InsertionSortSimulation extends Simulation {

    private Integer[] list;
    private int activeIndex;
    private int smallestIndex = 0;
    private int sortedIndex = 0;
    private long lastUpdate = 0;

    public InsertionSortSimulation(Canvas canvas) {
        super(canvas);
    }

    public InsertionSortSimulation(Canvas canvas, Color foregroundColor, Color backgroundColor, Color highlightColor) {
        super(canvas, foregroundColor, backgroundColor, highlightColor);
    }

    @Override
    public void updateCanvas() {
        GraphicsContext g2d = this.getCanvas().getGraphicsContext2D();

        this.clearCanvas();

        if (this.list == null)
            return;

        int padding;
        if (list.length >= 100)
            padding = 0;
        else
            padding = 2;

        int unitWidth = (int)(this.getCanvas().getWidth() / list.length);
        int unitHeight  = (int)(this.getCanvas().getHeight() / Collections.max(Arrays.asList(list)));

        int x;
        int y;
        int boxHeight;
        for ( int i = 0; i < list.length; i++) {
            x = i * unitWidth + padding;
            y = (int) this.getCanvas().getHeight() + padding - list[i] * unitHeight;
            boxHeight = unitHeight * list[i] - (2 * padding);

            if (i == activeIndex) {
                g2d.setFill(this.getHighlightColorOne());
                g2d.fillRect(x, y, unitWidth - (2 * padding), boxHeight);
                g2d.setFill(this.getForegroundColor());
            }
            else if (i == sortedIndex) {
                g2d.setFill(this.getHighlightColorTwo());
                g2d.fillRect(x, y, unitWidth - (2 * padding), boxHeight);
                g2d.setFill(this.getForegroundColor());
            }
            else if (i == smallestIndex) {
                g2d.setFill(this.getHighlightColorThree());
                g2d.fillRect(x, y, unitWidth - (2 * padding), boxHeight);
                g2d.setFill(this.getForegroundColor());
            }
            else {
                g2d.fillRect(x, y, unitWidth - (2 * padding), boxHeight);
            }
        }
    }

    public void initializeData(int size) {
        this.list = new Integer[size];

        for (int i = 0; i < size; i++)
            this.list[i] = i+1;

        this.lastUpdate = 0;
        this.activeIndex = 0;
        this.smallestIndex = 0;
        this.sortedIndex = 0;
    }

    @Override
    public void shuffleData() {
        this.activeIndex = 0;
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

            for (int i = 0; i < numberOfUpdates; i++) {
                activeIndex++;
                if (activeIndex != list.length && smallestIndex != list.length) {
                    updateSmallestIndex();
                } else if (smallestIndex != list.length){
                    swapEntries();
                    activeIndex = sortedIndex;
                    smallestIndex = sortedIndex;
                }
            }

            this.updateCanvas();

            if (sortedIndex == list.length)
                this.stop();
        }
    }

    private void updateSmallestIndex() {
        if (list[activeIndex] < list[smallestIndex])
            smallestIndex = activeIndex;
    }

    private void swapEntries() {
        int temp = list[smallestIndex];
        list[smallestIndex] = list[sortedIndex];
        list[sortedIndex] = temp;

        sortedIndex++;
    }
}

