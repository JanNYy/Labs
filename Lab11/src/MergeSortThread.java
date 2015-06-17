package courses.labs;

public class MergeSortThread implements Runnable {

    private static int counterID;

    private int threadID;

    private Comparable[] array;
    private int firstIndex;
    private int lastIndex;
    private boolean order;

    private void checkArray(Comparable[] inputArray) {
        if (inputArray == null) throw new IllegalArgumentException("Input array is null");
    }

    public MergeSortThread(Comparable[] inputArray, boolean isDesc) {
        this(inputArray,0,inputArray.length,isDesc);
    }

    private MergeSortThread(Comparable[] inputArray, int firstPos, int lastPos, boolean isDesc) {
        checkArray(inputArray);
        threadID = counterID++;
        array = inputArray;
        firstIndex = firstPos;
        lastIndex = lastPos;
        order = isDesc;
    }

    public Comparable[] getSortedArray() {
        return array;
    }

    @Override
    public void run() {
        if (lastIndex-firstIndex <= 1) return;

        System.out.println("Thread "+threadID+" is sorting sub-array from index "+firstIndex+" to "+lastIndex);
        Thread sortLeftPart = new Thread(new MergeSortThread(array,firstIndex,(lastIndex+firstIndex)/2,order));
        Thread sortRightPart = new Thread(new MergeSortThread(array,(lastIndex+firstIndex)/2,lastIndex,order));
        sortLeftPart.start();
        sortRightPart.start();

        try
        {
            sortLeftPart.join();
            sortRightPart.join();
        }
        catch (InterruptedException exception)
        {
            exception.printStackTrace();
        }
        mergeSort(firstIndex, lastIndex);
    }

    private void mergeSort(int firstPos, int lastPos) {
        int middlePos = (lastIndex+firstIndex)/2;
        int leftPos = firstPos;
        int rightPos = middlePos;

        while ( (leftPos < rightPos) && (rightPos < lastPos) )
        {
            if ((array[leftPos].compareTo(array[rightPos]) > 0) ^ order)
            {
                Comparable tmpElement = array[rightPos];
                System.arraycopy(array, leftPos, array, leftPos+1, rightPos-leftPos);
                array[leftPos] = tmpElement;
                rightPos += 1;
            }
            leftPos += 1;
        }
    }

}


