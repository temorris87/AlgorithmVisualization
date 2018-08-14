package visualization.sorting;

import javafx.scene.canvas.Canvas;

import java.util.Stack;

public class MergeSortSimulation extends SortSimulation {

    private Stack<MergeInterval> unsortedIntervals;
    private Stack<MergeInterval> sortedIntervals;
    private boolean mergingCompleted;
    private boolean swapOccurred;

    public MergeSortSimulation(Canvas canvas) {
        super(canvas);

        this.unsortedIntervals = new Stack<>();
        this.sortedIntervals = new Stack<>();
    }

    @Override
    public boolean updateOneFrame() {
        while (! this.swapOccurred) {
            mergeSort();
        }
        
        this.swapOccurred = false;

        if (mergingCompleted)
            return true;
        else
            return false;
    }

    private void mergeSort() {
        if (unsortedIntervals.isEmpty() && sortedIntervals.isEmpty()) {
            mergingCompleted = true;
            return;
        }
        if (sortedIntervals.size() != 2) {
            if (unsortedIntervals.peek().getRange() > 1) {
                MergeInterval oldInterval = unsortedIntervals.pop();
                int middle = oldInterval.getMiddleOfInterval();
                int leftInterval = oldInterval.getX();

                oldInterval.setX(middle + 1);
                unsortedIntervals.push(oldInterval);
                unsortedIntervals.push(new MergeInterval(leftInterval, middle));
            } else {
                MergeInterval toBeSortedInterval = unsortedIntervals.pop();
                sortListInterval(toBeSortedInterval);
                sortedIntervals.push(toBeSortedInterval);
            }
        }
        else {
            merge();
        }
    }
    
    private void merge() {
        MergeInterval rightInterval = sortedIntervals.pop();
        MergeInterval leftInterval = sortedIntervals.pop();
        
        Integer[] list = this.getList();

        if (list[leftInterval.getCurrentIndex()] > list[rightInterval.getCurrentIndex()]) {
            int rightValue = list[rightInterval.getCurrentIndex()];
            
            for (int i = rightInterval.getCurrentIndex(); i > leftInterval.getCurrentIndex(); i--)
                this.swapEntries(i, i-1);
            
            list[leftInterval.getCurrentIndex()] = rightValue;
            
            rightInterval.incrementCurrentIndex();
        }
        
        leftInterval.incrementCurrentIndex();
        
        if (leftInterval.getCurrentIndex() == leftInterval.getY() ||
                rightInterval.getCurrentIndex() == rightInterval.getY()) {
            sortedIntervals.push(new MergeInterval(leftInterval.getX(), rightInterval.getY()));
            System.out.println(sortedIntervals.peek());
        }
        else {
            sortedIntervals.push(leftInterval);
            sortedIntervals.push(rightInterval);
        }
    }
    
    private void sortListInterval(MergeInterval interval) {
        interval.setSorted();
        
        if (interval.getRange() == 0)
            return;
        
        if (this.getList()[interval.getX()] > this.getList()[interval.getY()]) {
            this.swapEntries(interval.getX(), interval.getY());
        }
    }

    @Override
    void swapEntries(int indexOne, int indexTwo) {
        super.swapEntries(indexOne, indexTwo);
        
        this.swapOccurred = true;
    }

    @Override
    public void initializeData(int size) {
        super.initializeData(size);

        int middle = (size - 1) / 2;
        unsortedIntervals.push(new MergeInterval(middle + 1, size - 1));
        unsortedIntervals.push(new MergeInterval(0, middle));

        this.mergingCompleted = false;
        this.swapOccurred = false;
    }

    @Override
    public void updateCanvas() {
        super.updateCanvas();
    }
}

class MergeInterval {

    int x;
    int y;
    int currentIndex;
    boolean sorted;



    MergeInterval(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentIndex = x;
        this.sorted = false;
    }

    void incrementCurrentIndex() {
        this.currentIndex++;
    }

    int getMiddleOfInterval() {
        return ((this.y + this.x) / 2);
    }

    int getRange() {
        return (this.y - this.x);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    int getCurrentIndex() {
        return currentIndex;
    }

    void setSorted() {
        this.sorted = true;
    }

    @Override
    public String toString() {
        return ("[" + this.x + ", " + this.y + "]");
    }
}
