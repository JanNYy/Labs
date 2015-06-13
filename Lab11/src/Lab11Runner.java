package courses.labs;

public class Lab11Runner {

    static final int threadsNum = 5;

    static final int start = 0;
    static final int end = 20;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("----- Test for Simple numbers -----");
        for (int i = 0; i < threadsNum; i++)
        {
            Thread simpleNumbersThread = new Thread(new FinderSimpleThread(start,end));
            simpleNumbersThread.start();
        }
        Thread.sleep(1000);

        System.out.println();

        System.out.println("----- Test for Sinus sum -----");
        Thread[] threads = new Thread[threadsNum];
        for (int i = 0; i < threadsNum; i++)
        {
            threads[i] = new Thread(new FinderSinThread(10));
            threads[i].start();
        }
        Thread.sleep(1000);
        System.out.println("Final sum is " + FinderSinThread.getSinSum());

        System.out.println();

        System.out.println("----- Test for Merge sort -----");
        Integer[] testArray = new Integer[] {4,1,8,7,15,20,2,3,65,31,6,5,9,19,34};
        MergeSortThread testMergeSortThread = new MergeSortThread(testArray, false);
        Thread sortThread = new Thread(testMergeSortThread);
        sortThread.start();
        sortThread.join();
        //Thread.sleep(1000);
        for (Integer i : testArray)
            System.out.print(i + " ");

    }

}
