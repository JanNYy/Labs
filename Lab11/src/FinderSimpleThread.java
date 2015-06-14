package courses.labs;

public class FinderSimpleThread implements Runnable {

    private static int counterID;

    private int threadID;

    private int from, to;

    private void checkArg(int arg) {
        if (arg < 0) throw new IllegalArgumentException("Argument is less than 0");
    }

    public FinderSimpleThread(int numFrom, int numTo) {
        checkArg(numFrom);
        checkArg(numTo);

        if (numFrom > numTo)
        {
            int numTmp = numFrom;
            numFrom = numTo;
            numTo = numTmp;
        }

        threadID = counterID++;
        from = numFrom;
        to = numTo;
    }

    public int getID() {
        return threadID;
    }

    @Override
    public void run() {
        System.out.println("Thread "+threadID+" is running");
        printSimple();
    }

    private void printSimple() {
        for (int i = from; i <= to; i++)
        {
            if (isSimple(i))
            {
                System.out.println("Thread "+threadID+" found "+i);
            }
        }
    }

    public boolean isSimple(int number) {
        if (number < 2) return false;

        if (number == 2) return true;

        for (int i = 2; i <= number/2; i++)
        {
            if (number%i == 0)
                return false;
        }
        return true;
    }

}
