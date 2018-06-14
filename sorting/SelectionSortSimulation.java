package visualization.sorting;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class SelectionSortSimulation extends SortSimulation {

    private int activeIndex;
    private int smallestIndex = 0;
    private int sortedIndex = 0;

    public SelectionSortSimulation(Canvas canvas) {
        super(canvas);
    }

    public SelectionSortSimulation(Canvas canvas, Color foregroundColor, Color backgroundColor, Color highlightColor) {
        super(canvas, foregroundColor, backgroundColor, highlightColor);
    }

    @Override
    public boolean updateOneFrame() {
        activeIndex++;
        if (activeAndSmallestIndicesInRange())
            updateSmallestIndex();
        else if (smallestInRange())
            this.swapEntries(smallestIndex, sortedIndex);

        return (sortedIndex == this.getList().length);
    }

    private boolean smallestInRange() {
        return (smallestIndex != this.getList().length);
    }

    private boolean activeAndSmallestIndicesInRange() {
        return (activeIndex != this.getList().length && smallestIndex != this.getList().length);
    }

    private void updateSmallestIndex() {
        if (this.getList()[activeIndex] < this.getList()[smallestIndex])
            smallestIndex = activeIndex;
    }

    @Override
    void swapEntries(int indexOne, int indexTwo) {
        super.swapEntries(indexOne, indexTwo);

        sortedIndex++;
        activeIndex = sortedIndex;
        smallestIndex = sortedIndex;
    }

    @Override
    public void initializeData(int size) {
        super.initializeData(size);

        this.activeIndex = 0;
        this.smallestIndex = 0;
        this.sortedIndex = 0;
    }

    @Override
    public void updateCanvas() {
        super.updateCanvas();

        if (activeIndex < this.getList().length)
            this.drawListItem(activeIndex, this.getHighlightColorOne());

        if (sortedIndex < this.getList().length)
            this.drawListItem(sortedIndex, this.getHighlightColorTwo());

        if (smallestIndex < this.getList().length)
            this.drawListItem(smallestIndex, this.getHighlightColorThree());
    }
}
