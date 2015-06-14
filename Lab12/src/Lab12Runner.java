package courses.labs;

public class Lab12Runner {

    public static void main(String[] args) {
        CircularBuffer testBuffer = new CircularBuffer(5);
        Integer[] testData1 = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        Integer[] testData2 = new Integer[]{11,12,13,14,15,16,17,18,19,20};
        Producer testProducer1 = new Producer(testBuffer,testData1);
        Producer testProducer2 = new Producer(testBuffer,testData2);
        Consumer testConsumer1 = new Consumer(testBuffer,10);
        Consumer testConsumer2 = new Consumer(testBuffer,10);
        Thread prod1 = new Thread(testProducer1);
        Thread prod2 = new Thread(testProducer2);
        Thread cons1 = new Thread(testConsumer1);
        Thread cons2 = new Thread(testConsumer2);

        prod1.start();
        prod2.start();
        cons1.start();
        cons2.start();


    }
}
