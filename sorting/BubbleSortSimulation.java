package visualization.sorting;

import javafx.scene.canvas.Canvas;

public class BubbleSortSimulation extends SortSimulation {

    private int leftActive;
    private int rightActive;
    private int swapsPerformed;

    public BubbleSortSimulation(Canvas canvas) {
        super(canvas);
    }

    @Override
    public boolean updateOneFrame() {
        if (rightActive == this.getList().length) {
            if (swapsPerformed == 0) {
                leftActive++;
                return true;
            }
            else {
                leftActive = 0;
                rightActive = 1;
                swapsPerformed = 0;
            }
        }
        else if (this.getList()[leftActive] > this.getList()[rightActive]) {
            swapEntries(leftActive, rightActive);
        }
        else {
            leftActive++;
            rightActive++;
        }

        return false;
    }

    @Override
    void swapEntries(int indexOne, int indexTwo) {
        super.swapEntries(indexOne, indexTwo);

        swapsPerformed++;
        leftActive++;
        rightActive++;
    }

    @Override
    public void initializeData(int size) {
        super.initializeData(size);

        leftActive = 0;
        rightActive = 1;
        swapsPerformed = 0;
    }

    @Override
    public void updateCanvas() {
        super.updateCanvas();

        if (leftActive < this.getList().length)
            this.drawListItem(leftActive, this.getHighlightColorOne());

        if (rightActive < this.getList().length)
            this.drawListItem(rightActive, this.getHighlightColorTwo());
    }
}
