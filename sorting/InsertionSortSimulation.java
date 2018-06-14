package visualization.sorting;

import javafx.scene.canvas.Canvas;

public class InsertionSortSimulation extends SortSimulation {

    private int unsortedIndex;
    private int currentIndex;

    public InsertionSortSimulation(Canvas canvas) {
        super(canvas);
    }

    @Override
    public boolean updateOneFrame() {
        if (unsortedIndex == this.getList().length + 1)
            return true;

        if (currentIndex != 0) {
            if (this.getList()[currentIndex - 1] > this.getList()[currentIndex])
                swapEntries(currentIndex - 1, currentIndex);
            else {
                unsortedIndex++;
                currentIndex = unsortedIndex - 1;
            }
        }
        else {
            unsortedIndex++;
            currentIndex = unsortedIndex - 1;
        }

        return false;
    }

    @Override
    void swapEntries(int indexOne, int indexTwo) {
        super.swapEntries(indexOne, indexTwo);

        currentIndex--;
    }

    @Override
    public void initializeData(int size) {
        super.initializeData(size);

        this.unsortedIndex = 1;
        this.currentIndex = 1;
    }

    @Override
    public void updateCanvas() {
        super.updateCanvas();

        if (unsortedIndex < this.getList().length)
            drawListItem(unsortedIndex, this.getHighlightColorOne());

        if (currentIndex != this.getList().length)
            drawListItem(currentIndex, this.getHighlightColorTwo());
    }
}
