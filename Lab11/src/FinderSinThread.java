package courses.labs;

public class FinderSinThread implements Runnable{

    private static int counterID;

    private int threadID;

    private static double sinSum;
    private volatile static int argumentCounter;
    private int border;

    private void checkArg(int arg) {
        if (arg <= 0) throw new IllegalArgumentException("Argument is less than 0");
    }

    public FinderSinThread(int sinRange) {
        checkArg(sinRange);
        threadID = counterID++;
        border = sinRange;
        if (argumentCounter == 0)
            argumentCounter = -sinRange-1;
    }

    public int getID() {
        return threadID;
    }

    public static double getSinSum() {
        return Math.round(sinSum);
    }

    @Override
    public void run() {
        System.out.println("Thread "+threadID+" is running");
        double threadSum = 0.0;
        int threadArgument;
        while (argumentCounter < border)
        {
            threadArgument = ++argumentCounter;
            System.out.println("Thread " + threadID + " has " + threadArgument);
            threadSum += Math.sin(threadArgument);
        }
        sinSum += threadSum;
    }

}
